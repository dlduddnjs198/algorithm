# JDK 25 Java 문자열(String) 성능 향상

### `String::hashCode()`의 상수화 최적화와 그 파급력

Java에서 `String`은 거의 모든 애플리케이션에서 필수적인 데이터 타입이다. 특히 `Map<String, V>`에서 key로 사용될 때가 많고, 이 과정에서 `String::hashCode()`는 핵심 역할을 담당한다.**(HashSet, HashMap 등에서)** 그런데, JDK 25에서는 `String::hashCode()` 호출 자체가 훨씬 빨라졌다. 이유는 단 하나. **JVM이 이 함수를 “상수처럼 다룰 수 있게” 바뀌었기 때문이다.**

### 📌 먼저, hashCode()는 뭘까?

Java의 `hashCode()`는 객체를 빠르게 비교하거나 찾기 위해 **정수값으로 요약**하는 메서드다.

**String s1 = new String(“banana”)와 String s2 = new String(“banana”)의 hashCode는 같은 값을 가진다.**

예를 들어 `HashMap`에서 `"banana"` 키를 찾을 때는 이렇게 된다:

```java
int hash = "banana".hashCode(); // 예: 92837233
map.get(hash);
```

즉, `hashCode()`는 **성능과 직결되는 함수**다.

---

### 💥 기존 문제: hashCode는 항상 빠르지 않았다

기존(JDK 24까지)의 `String` 구조를 보면,

- `String` 객체를 만들면 `hash` 필드는 기본값 `0`으로 초기화됨
- 처음 `hashCode()`를 호출하면 계산 후 저장
- 이후엔 저장된 값을 캐시에서 반환하긴 하지만,

  ➜ **JVM은 이 값이 “변하지 않는다”는 보장을 받지 못해서 최적화가 어려움**


즉, `String`이 불변(immutable)임에도

JVM은 `hashCode()`를 **매번 확인하고 계산해야 했던 것**이다.

---

### 🚀 변화의 핵심: @Stable

JDK 25에서는 `String.hash` 필드에

**JDK 내부 전용 어노테이션 `@Stable`**이 붙었다.

```java
@Stable int hash;
```

이 한 줄이 엄청난 차이를 만들었다.

JVM은 이제 아래와 같이 이해한다:

> 이 필드는 0이 아닌 값이 한번 들어가면 절대 바뀌지 않는다.
>
>
> 그러니 이 값을 **상수처럼 취급**해도 된다!
>

그 결과, `String::hashCode()`는

**한 번만 계산되고, 그 뒤로는 완전히 “접혀서(CF)” 사라진다.**

### ❓ `final` 붙이면 되는 거 아냐?

좋은 접근이다. 하지만 **`final`만으로는 해결되지 않는다.**

| **구분** | **설명** |
| --- | --- |
| `final` | 참조 자체가 바뀌지 않음을 보장 (초기화 후 변경 불가) |
| `@Stable` | 값이 한 번 세팅되면 안 바뀐다는 것을 JIT에게 **힌트**로 제공하여 최적화 유도 |

즉,

- `final int hash = 0;`처럼 선언하면 **초기화 시점 이후엔 절대 못 바꿈**
- 반면 `String.hash`는 **초기엔 0, 이후 최초 계산 후 값 저장** → 변경이 필요함
- 따라서 `final`로는 이 패턴을 구현할 수 없음

`@Stable`은 JIT 컴파일러에게 “이 필드는 사실상 한 번만 설정되니, 상수처럼 최적화해도 돼”라는 **힌트를 주는 역할**이다.

---

### 📈 예시: 실전에서 어떻게 쓰일까?

다음은 `String`을 key로 갖는 불변 Map을 사용하는 예다.

특히, Foreign Function & Memory API를 통해 native 메서드를 호출할 때 사용한다:

```java
static final Map<String, MethodHandle> SYSTEM_CALLS = Map.of(
    "malloc", linker.downcallHandle(mallocSymbol, ...),
    "free", linker.downcallHandle(freeSymbol, ...)
);

long address = SYSTEM_CALLS.get("malloc").invokeExact(16L);
SYSTEM_CALLS.get("free").invokeExact(address);
```

이전에는 `get("malloc")`에서

- `"malloc".hashCode()` 계산
- 내부 배열 인덱스 계산
- MethodHandle 검색

이 일련의 작업이 **매번 수행**됐다.

하지만 이제는?

```java
// JDK 25 이후
// "malloc".hashCode() == -1081483544 (고정)
// → Map 내부에서 바로 인덱스 계산 후 바로 MethodHandle 리턴
```

모든 과정이 **상수처럼 고정**되고, 성능은 무려 **8배 이상 향상**된다.

---

### 📊 벤치마크 결과

| JDK 버전 | 평균 수행 시간 (`hashCode`) |
| --- | --- |
| JDK 24 | 4.632 ns/op |
| JDK 25 | 0.571 ns/op |

> 📌 malloc() 성능이 아닌, String lookup 성능 비교다.
>

---

### ⚠️ 단점도 있다: hashCode가 0이면 안 됨

- `@Stable`은 “0이 아닌 값”일 때만 JVM이 상수로 믿어준다.
- 그런데 문제는 **빈 문자열 `""`의 hashCode는 0**이라는 것!
- 따라서 `""`를 key로 쓴 경우엔 이 최적화가 **적용되지 않는다.**

하지만 다행히도,

- `" "`(space)부터 `"Z"`까지의 문자로 구성된 **1~6글자 문자열**은 hashCode가 0이 아님
- 즉, **실제 코드에서 문제될 가능성은 매우 적다**

---

### 🔭 앞으로는 어떻게 될까?

현재 `@Stable`은 JDK 내부에서만 쓸 수 있는 주석이다.

하지만 개발자도 비슷한 최적화를 받을 수 있도록 JEP 502: Stable Values (Preview)가 준비 중이라고 한다.

이 기능이 도입되면,

- 우리가 만든 클래스나 필드도
- JVM에게 “이건 절대 안 바뀌어요!”라고 알려줄 수 있게 된다.

---

### 📝 결론

- `String`은 가장 흔한 Java 타입 중 하나이며
- `hashCode()`는 Map, Set 등에서 필수적으로 사용됨
- JDK 25에서는 `@Stable` 덕분에 **성능 최적화가 가능**
- **읽기 전용 Map에서 성능이 최대 8배 향상**
- 실제 애플리케이션, 특히 JVM 기반 서버의 전반적인 성능 개선 효과 기대

---

💡 출처 : https://inside.java/2025/05/01/strings-just-got-faster/