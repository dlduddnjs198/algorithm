# 🧺 Set 자료구조 정리 (HashSet, LinkedHashSet, TreeSet)

`Set`은 **중복되지 않는 값들의 집합**을 저장하는 자료구조입니다.  
리스트(List)와 달리 같은 값을 여러 번 넣을 수 없으며, 순서나 정렬 여부는 구현체에 따라 다릅니다.

Java에서는 대표적으로 다음 3가지 Set 구현체가 많이 사용됩니다:

- `HashSet`
- `LinkedHashSet`
- `TreeSet`

---

## 📌 Set 공통 특징

- **중복된 요소를 허용하지 않음**
- 요소의 **존재 여부 확인**이 주요 용도
### 자주 사용되는 함수
- **set.add(3);** → 같은 값이 들어오면 무시된다.
- **set.remove(3);** → 값을 제거한다. 없으면 아무일도 일어나지 않는다.
- **set.removeIf(x -> x % 2 == 0);** → 조건에 맞는 요소를 모두 제거(짝수만 제거)
- **set.contains(5);** → 값 5가 있으면 true(HashSet 기준 O(1))
- **set.size();** → 현재 저장된 값의 개수 반환
- **set.isEmpty();** → 요소가 하나도 없으면 true
- **set.clear();** → set이 완전히 비워짐
- **a.addAll(b);** → 합집합. 현재 Set(a)에 다른 컬렉션의 모든 요소 추가, 중복은 무시됨, 
- **a.retainAll(b);** → 교집합. a, b에 공통적으로 있는것만 남김
- **a.removeAll(b);** → 현재 Set(a)에서 다른 컬렉션(b)에 있는 모든 요소 제거
### 자주 사용되는 함수(TreeSet 전용)
- **set.ceiling(5);** → 주어진 값(5) 이상 중 가장 작은 값을 반환, null 반환 가능, O(log n)
- **set.floor(5);** → 주어진 값(5) 이하 중 가장 큰 값을 반환, null 반환 가능, O(log n)
- **set.higher(5);** set.lower(5); → 주어진 값(5) 초과 중 최소, 미만 중 최대값 반환, O(log n)
- **set.first();** → 가장 작은 값 반환, O(1)
- **set.last();** → 가장 큰 값 반환, O(1)
- **set.pollFirst();** → 가장 작은 값을 꺼내고 제거, O(log n)
- **set.pollLast();** → 가장 큰 값을 꺼내고 제거, O(log n)
- **set.subSet(3, 7);** → 3 이상 7 미만의 범위를 갖는 view 반환, O(log n)
- **set.headSet(5);** → 5 미만의 요소 view 반환, O(log n)
- **set.tailSet(5);** → 5 이상 요소의 view 반환, O(log n)


---

## 1️⃣ HashSet

### ✅ 개요
- 가장 기본적인 Set 구현체
- 내부적으로 **해시 테이블**을 사용하여 데이터를 저장
- 요소의 순서나 정렬은 **보장되지 않음**

### 🛠️ 시간 복잡도
| 연산 | 평균 시간 | 최악 시간 |
|------|-----------|-----------|
| 추가(add) | O(1) | O(n) |
| 삭제(remove) | O(1) | O(n) |
| 검색(contains) | O(1) | O(n) |

※ 최악의 경우는 **해시 충돌**이 많이 발생했을 때

### 📎 사용 예시
- 단순 중복 제거
- 빠른 존재 여부 판단
- 예: "이전에 등장한 숫자인가?" 체크

### 💡 예제 코드
```java
Set<String> set = new HashSet<>();
set.add("apple");
set.add("banana");
set.add("apple"); // 중복, 추가되지 않음

System.out.println(set.contains("banana")); // true
System.out.println(set); // 순서는 보장되지 않음
````

---

## 2️⃣ LinkedHashSet

### ✅ 개요

* `HashSet`의 기능에 **입력 순서 유지 기능**을 추가한 구현체
* 내부적으로 해시 테이블 + **이중 연결 리스트** 사용
* 순서를 기억하고 출력 시도입된 순서대로 보여줌

### 🛠️ 시간 복잡도

| 연산       | 시간 복잡도  |
| -------- | ------- |
| 추가/삭제/탐색 | 평균 O(1) |

※ 연결 리스트로 인해 **메모리 사용량은 더 큼**

### 📎 사용 예시

* 중복 제거하면서도 **입력 순서를 유지**해야 할 때
* 예: 입력된 순서대로 Set을 순회해야 하는 경우

### 💡 예제 코드

```java
Set<String> set = new LinkedHashSet<>();
set.add("apple");
set.add("banana");
set.add("apple"); // 중복 제거

System.out.println(set); // [apple, banana] → 입력 순서 유지
```

---

## 3️⃣ TreeSet

### ✅ 개요

* 요소들을 **자동으로 정렬**하는 Set
* 내부적으로 **이진 탐색 트리 (Red-Black Tree)** 를 사용
* 기본 정렬은 오름차순이며, 커스텀 정렬도 가능
* TreeSet에서만 사용할 수 있는 부분 Set을 만드는 함수는 반환값이 본래 TreeSet과 연결되어있다.

### 🛠️ 시간 복잡도

| 연산       | 시간 복잡도   |
| -------- | -------- |
| 추가/삭제/탐색 | O(log n) |

### 📎 사용 예시

* 정렬된 상태에서 중복 없는 값을 저장하고자 할 때
* 범위 탐색 (예: x 이상인 가장 작은 값)도 가능

### 💡 예제 코드

```java
Set<Integer> set = new TreeSet<>();
set.add(5);
set.add(2);
set.add(8);

System.out.println(set); // [2, 5, 8] → 오름차순 자동 정렬
System.out.println(((TreeSet<Integer>) set).ceiling(6)); // 8
```

---

## 🧠 정리 비교

| 자료구조            | 내부 구조       | 순서 보장   | 자동 정렬      | 탐색 속도    | 특이점      |
| --------------- | ----------- | ------- | ---------- | -------- | -------- |
| `HashSet`       | 해시 테이블      | ❌ 순서 없음 | ❌ 없음       | O(1) 평균  | 가장 빠름    |
| `LinkedHashSet` | 해시 + 연결 리스트 | ✅ 입력 순서 | ❌ 없음       | O(1) 평균  | 순서 보존    |
| `TreeSet`       | 이진 탐색 트리    | ✅ 정렬 순서 | ✅ 오름차순(기본) | O(log n) | 범위 탐색 가능 |

---

## 🧾 결론

* **중복 제거만 필요하고 순서/정렬이 중요하지 않다면 → `HashSet`**
* **입력 순서를 유지하며 중복 제거하고 싶다면 → `LinkedHashSet`**
* **자동 정렬된 상태에서 중복 없이 데이터를 관리하고 싶다면 → `TreeSet`**

---

> 💡 각 Set의 특성을 잘 이해하고 상황에 맞게 선택하는 것이 중요합니다.
> 예를 들어, "정렬 + 중복 제거"가 필요하면 `TreeSet`이 유리하고, "단순 중복 제거 + 빠른 탐색"이 필요하면 `HashSet`이 적합합니다.


