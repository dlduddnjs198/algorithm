# 📚 List 자료구조 정리 (ArrayList, LinkedList)

`List`는 **순서가 있는 데이터의 집합**을 표현하며, 인덱스를 기반으로 데이터를 저장하고 접근하는 자료구조입니다.

Java에서는 다음 두 가지 대표 구현체가 사용됩니다:

* `ArrayList`
* `LinkedList`

---

## 📌 List 공통 특징

* 순서 보장 (인덱스로 접근 가능)
* 중복 요소 허용
* null 값 저장 가능

### **자주 사용되는 함수**
* **list.add(value);** → 리스트 끝에 값 추가
* **list.add(index, value);** → 특정 인덱스에 값 삽입
* **list.get(index);** → 인덱스에 있는 값 반환
* **list.set(index, value);** → 인덱스에 있는 값 변경
* **list.remove(index);** → 해당 인덱스의 요소 제거 (뒤 요소들이 당겨짐, 없으면 예외 발생), O(n)
* **list.remove(Object);** → 해당 객체와 같은 첫 번째 요소 제거(없으면 false 반환). O(n)
* **list.removeIf(x -> 조건);** → 조건에 맞는 항목 일괄 제거(없으면 false 반환), O(n)
* **list.contains(value);** → 특정 값이 존재하는지 확인
* **list.indexOf(value);** → 첫 등장 인덱스 반환 (없으면 -1)
* **list.isEmpty();** → 리스트가 비어있는지 확인
* **list.size();** → 요소 개수 반환
* **list.clear();** → 모든 요소 제거
* **list.subList(from, to);** → 부분 리스트 반환 (from 이상, to 미만)
* **list.stream().filter(...).collect(...);** → 스트림 필터링 및 수집
### **자주 사용되는 함수(Collection, List)**
* **Collections.sort(list);** → 오름차순 정렬
* **Collections.reverse(list);** → 역순 정렬
* **List.of(a, b, c);** → 불변 리스트 생성 (Java 9+)

---

## 1️⃣ ArrayList

### ✅ 개요

* 내부적으로 **배열 기반**으로 구현
* **임의 접근 속도 빠름** (인덱스 기반 → O(1))
* 삽입/삭제는 느림 (중간에 끼워 넣거나 앞에서 제거 시 O(n))
* 크기 초과 시 내부 배열을 **2배로 확장**하며 복사 수행

### 🛠️ 시간 복잡도

| 연산              | 시간 복잡도    |
| --------------- | --------- |
| get/set         | O(1)      |
| add (끝에)        | O(1) (평균) |
| add/remove (중간) | O(n)      |
| contains        | O(n)      |

### 📎 사용 예시

* 순차 데이터 저장/탐색
* **빈도 저장**, 정렬 대상 등
* 대부분의 경우 기본값으로 가장 많이 사용됨

### 💡 예제 코드

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add(1, "X"); // [A, X, B]

System.out.println(list.get(1)); // X
list.remove("A");
System.out.println(list); // [X, B]
```

---

## 2️⃣ LinkedList

### ✅ 개요

* 내부적으로 **이중 연결 리스트**로 구성
* 각 요소는 **이전/다음 노드**를 가리키는 포인터 보유
* 삽입/삭제가 빠름 (특히 앞쪽에서 O(1))
* **임의 접근은 느림** → O(n)

### 🛠️ 시간 복잡도

| 연산              | 시간 복잡도 |
| --------------- | ------ |
| get/set         | O(n)   |
| add/remove (앞쪽) | O(1)   |
| add/remove (중간) | O(n)   |
| contains        | O(n)   |

### 📎 사용 예시

* 큐, 덱 구현 시 유리
* 데이터 삽입/삭제가 빈번한 작업

### 💡 예제 코드

```java
List<Integer> list = new LinkedList<>();
list.add(1);
list.add(2);
list.add(3);
list.remove(0); // 앞쪽 요소 제거
System.out.println(list); // [2, 3]
```

---

## 🔄 ArrayList vs LinkedList

| 항목            | ArrayList   | LinkedList    |
| ------------- | ----------- | ------------- |
| 내부 구조         | 배열          | 이중 연결 리스트     |
| 임의 접근         | ✅ 빠름 (O(1)) | ❌ 느림 (O(n))   |
| 삽입/삭제 (중간/앞쪽) | ❌ 느림 (O(n)) | ✅ 빠름 (O(1))   |
| 메모리 효율성       | ✅ 좋음        | ❌ 낮음 (포인터 공간) |
| 순차 접근         | ✅ 적합        | ✅ 적합          |
| 주요 사용 목적      | 접근/정렬       | 삽입/삭제         |

---

## 📌 기타 관련 메서드 및 팁

* **list.stream().filter().collect()** → 스트림 필터링
* **list.removeIf(x -> x % 2 == 0);** → 조건에 맞는 항목 일괄 제거
* **Collections.sort(list);** → 오름차순 정렬
* **Collections.reverse(list);** → 역순 정렬
* **List.of(...)** → 불변 리스트 생성 (Java 9+)

---

## 🧾 결론

* **대부분의 경우**: `ArrayList` → 빠른 접근, 메모리 효율
* **삽입/삭제 많은 경우**: `LinkedList` → 포인터 기반 수정 유리

> 💡 코딩 테스트에서는 대부분 `ArrayList`를 기본으로 사용하되,
> **큐/덱 구현이나 빈번한 삭제**가 필요한 경우 `LinkedList` 고려
