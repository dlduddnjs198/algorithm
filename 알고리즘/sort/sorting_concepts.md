# 정렬 응용 및 필수 개념

> 정렬 알고리즘 자체보다 더 자주 출제되는 **정렬 개념 응용 요소들**입니다.  
> 코딩테스트 실전, 면접, 라이브 코딩 등에서 거의 반드시 마주하게 되는 내용입니다.

---

## 1. Stable Sort / Unstable Sort

> 같은 값을 가진 원소들의 **기존 순서가 유지되는가**?

- ✅ **Stable (안정 정렬)**:  
  값이 같을 때, **기존 입력 순서가 그대로 유지됨**
  - 예: Merge Sort, Insertion Sort, Bubble Sort, Counting Sort

- ❌ **Unstable (불안정 정렬)**:  
  값이 같아도 위치가 바뀔 수 있음
  - 예: Quick Sort, Heap Sort, Selection Sort

### 📌 왜 중요한가?

- **객체 배열을 정렬할 때**,  
  첫 번째 키로 정렬한 뒤, 두 번째 키로 정렬할 경우 **순서 보존이 필수**
- 자바의 `Collections.sort()`는 TimSort 기반 → ✅ 안정 정렬

```java
List<Person> list = ...
// 나이로 정렬 (오름차순)
Collections.sort(list, Comparator.comparingInt(p -> p.age));
// 같은 나이 내에서는 기존 순서가 유지됨
````

---

## 2. Custom Comparator (사용자 정의 정렬 기준)

> 기본이 아닌, **문제에 맞는 정렬 기준**을 직접 정의

* Java: `Comparator.comparing()`, `Comparator` 인터페이스 사용
* Python: `key=` 파라미터 + lambda
* C++: `sort(v.begin(), v.end(), [](a,b){ return ...; })`

### 📌 대표 상황

* 두 번째 값을 기준으로 정렬, 값이 같으면 첫 번째 값 기준
* 문자열 길이 기준 + 사전 순 정렬
* 배열을 특정 인덱스 기준으로 정렬

```java
// 예: (score, name)에서 score 내림차순, 같으면 name 오름차순
Arrays.sort(arr, (a, b) -> {
    if (a.score == b.score) return a.name.compareTo(b.name);
    return b.score - a.score;
});
```

---

## 3. Partial Sort / Top-K 정렬

> 전체 정렬이 아니라 **일부(K개)만 빠르게 구하는 정렬 방식**

* 전체 정렬: `O(N log N)`
* Top-K만 필요: **`O(N log K)`** 가능

### 📌 핵심: `PriorityQueue` (Heap)

* 최대 힙: 상위 K개 유지
* 최소 힙: 하위 K개 유지

```java
// Top K largest numbers
PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int num : arr) {
    pq.offer(num);
    if (pq.size() > K) pq.poll(); // K개 초과 시 제거
}
```

### ✅ 대표 문제

* K번째 수 (BOJ 11004)
* Top K Frequent Elements (LeetCode)
* K Closest Points to Origin

---

## 4. Online Sort

> 입력이 **실시간으로 주어지는 상황**에서 정렬을 유지

* 가장 간단한 방식: **삽입 정렬**
* 매 입력마다 현재까지의 정렬 상태를 유지

### 💡 진짜 실용적인 방법은?
| 목적        | 자료구조                    | 설명                    |
| --------- | ----------------------- | --------------------- |
| 중위수 유지    | 두 개의 힙 (최소 힙 + 최대 힙)    | 중간값을 빠르게 찾고 정렬 유지 가능  |
| 상위 K개 유지  | `PriorityQueue`         | 정렬 대신 K개만 유지하며 빠르게 대응 |
| 실시간 전체 정렬 | `TreeSet`, `SortedList` | 항상 정렬 상태 유지됨          |

```
// Java에서 실시간 정렬 상태 유지
TreeSet<Integer> ts = new TreeSet<>();
ts.add(x); // 자동으로 정렬됨
```

### 📌 특징

* 실시간 입력 처리
* 데이터 흐름에 따라 변화하는 순위, 랭킹 등에 적합
* `TreeSet`, `PriorityQueue`로도 응용 가능

```java
// TreeSet을 이용해 삽입하면서 정렬 유지
TreeSet<Integer> ts = new TreeSet<>();
ts.add(x); // 삽입 시 자동 정렬
```

---

## 5. External Sort (외부 정렬)

> **데이터 크기가 너무 커서 메모리에 한 번에 올릴 수 없는 경우**
> → 디스크 단위로 나눠 처리

**💡 기본 아이디어 (2단계)<br>**
**Step 1️⃣: 작은 단위로 나눠서 정렬**
100GB를 10GB씩 끊어
- 각 블록(Chunk)을 메모리에 올려서 정렬 후 디스크에 저장
- 결과: 정렬된 파일 조각들이 생김 → run1.txt, run2.txt, ...

**Step 2️⃣: 정렬된 조각들을 병합**
- 이걸 **K-way merge (k개 병합)**라고 해
- 각 파일에서 가장 작은 값만 peek해서 비교 → 작은 값을 결과로 출력
- 보통 **MinHeap (우선순위 큐)**을 사용

### 📌 실무 적용

* DBMS 내부 정렬 처리 (예: 인덱스 정렬, 정렬 조인, ORDER BY)
* Hadoop/Spark MapReduce 외부 정렬
* 대규모 로그 처리 (10억 라인 이상), merge join

---

## ✅ 핵심 요약

| 개념명               | 설명                                | 실전 적용 예시                   |
| ----------------- | --------------------------------- | -------------------------- |
| Stable Sort       | 같은 값의 기존 순서 유지                    | 객체 정렬, 다단계 정렬              |
| Custom Comparator | 정렬 기준을 직접 정의                      | 정렬 기준이 복합적인 문제, 사전 순 등     |
| Partial Sort      | Top K만 정렬 (`O(N log K)`)          | K번째 수, Top K 빈도 요소, 실시간 순위 |
| Online Sort       | 실시간 정렬 상태 유지 (삽입정렬, TreeSet 기반 등) | 스트리밍, 실시간 데이터 분석, 랭킹       |
| External Sort     | 외부 저장 장치를 활용한 정렬                  | 대용량 로그 분석, DB 정렬 연산        |

---

## 💬 마무리 한 줄 요약

> 실전 코딩 테스트에서 정렬은 단순한 오름차순/내림차순 문제가 아니다.
> **정렬 기준, 범위, 순서 보존, 실시간성, 메모리 제약**까지 고려한 설계가 중요한 핵심 도구다.

```

---

필요하다면 이 문서에 각 항목마다 실제 **코드 예제 md** 혹은 `code/SortUtils.java` 형태로 나눠 구현도 가능해.  
다음으로 이어서 할까? 아니면 이걸 summary 문서에 링크하는 작업부터 할까?
```
