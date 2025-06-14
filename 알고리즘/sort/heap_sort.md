# 힙 정렬 - Heap Sort

> 📌 한 줄 요약: 힙(우선순위 큐)을 이용해 가장 큰 값을 차례대로 꺼내 정렬하는, 공간 효율 좋은 비교 기반 정렬입니다.

---

## 1. 개념 설명
![heap_img1.gif](../../images/heap_img1.gif)

- **정렬 방식**: 비교 기반 (Comparison Sort)
- **패러다임**: 선택 기반 + 완전이진트리 기반
- **안정성**: ❌ 불안정 정렬 (같은 값의 순서가 바뀔 수 있음)
- **내부 정렬 여부**: ✅ 내부 정렬 – 추가 메모리 없이 제자리 정렬 가능

- **작동 원리**:
    - 배열을 **최대 힙(Max Heap)** 구조로 변환  
      → 부모 노드 ≥ 자식 노드
    - 힙의 루트(가장 큰 값)를 꺼내서 맨 뒤에 넣고,  
      힙을 다시 재정렬 (heapify)
    - 이 과정을 **끝까지 반복**하면 오름차순 정렬이 완성됨

- **정렬 구간 구조**:
    - 배열 전체가 **점점 정렬된 영역과 힙 영역**으로 나뉨  
      예: `[힙 영역][정렬된 영역]`

- **특징 요약**:
    - ✅ 항상 O(n log n) 보장
    - ✅ 추가 배열 없이 정렬 가능 (제자리 정렬)
    - ❌ 안정 정렬이 아님 (값이 같아도 순서 뒤바뀔 수 있음)
    - ✅ 삽입/삭제 성능도 좋아서 우선순위 큐 구현에도 활용
    - ✳️ 정렬 외에도 다양한 알고리즘(다익스트라 등)에서 기초 도구로 쓰임

---

### 🔹 Heapify란?

> 특정 노드 기준으로 **부모-자식 관계를 재조정하여 힙의 조건을 유지**하는 작업

```text
예: [1, 5, 4, 2, 8] → 8이 자식인데 루트보다 크므로
→ 8을 루트로 올리고 heapify 수행 → [8, 5, 4, 2, 1]

트리 형태:

        8
       / \
      5   4
     / \
    2   1
```

## 2. 시간 및 공간 복잡도

| 구분            | 복잡도    |
|-----------------|-----------|
| 최선            | O(n log n)|
| 평균            | O(n log n)|
| 최악            | O(n log n)|
| 공간 복잡도     | O(1)      |
| 안정 정렬 여부   | ❌ Unstable |

---

## 3. 작동 예시

```text
입력: [5, 1, 4, 2, 8]

Step 1: 배열을 최대 힙으로 만든다
→ [8, 5, 4, 2, 1]

Step 2: 루트(8) ↔ 마지막 값(1) 교환
→ [1, 5, 4, 2, 8]
→ heapify → [5, 2, 4, 1, 8]

Step 3: 루트(5) ↔ 끝-1(1) 교환
→ [1, 2, 4, 5, 8]
→ heapify → [4, 2, 1, 5, 8]

... 반복

최종 결과: [1, 2, 4, 5, 8]
````

---

## 4. Java 코드 구현

- [기본 구현(힙 정렬)](code/HeapSort.java) – 최대 힙을 직접 구현하여 배열 내부 정렬
- [우선순위 큐 이용 구현](code/HeapSortPriorityQueue.java) – 자바 기본 우선순위 큐(PriorityQueue) 활용

---

## 5. 언제 사용되는가?

* **추가 메모리를 아끼고 싶을 때**
* **정렬과 동시에 최대값/최소값을 반복적으로 꺼내야 할 때**
* **우선순위가 높은 항목부터 처리해야 하는 알고리즘 (ex. 다익스트라, Prim 등)**
* **O(n log n) 보장을 원하면서 제자리 정렬이 필요한 상황**
* **❌ 정렬 안정성이 중요한 경우에는 부적합**

---

## 6. 출제 예시 문제

| 플랫폼      | 문제명                 | 링크                                                                     |
| -------- | ------------------- | ---------------------------------------------------------------------- |

---

## 📌 핵심 요약

| 항목       | 내용                            |
| -------- | ----------------------------- |
| 정렬 방식    | 비교 기반                         |
| 평균 시간복잡도 | O(n log n)                    |
| 안정성      | ❌ Unstable                    |
| 사용 추천도   | ⭐⭐⭐⭐☆                         |
| 실전 사용 여부 | 자주 사용 (특히 우선순위 큐, 알고리즘 기반 정렬) |

---

## ✅ 정렬 정리 요약표

| 정렬 알고리즘      | 평균 시간복잡도     | 공간 복잡도  | 안정성 | 언제 쓰는가                                | 실전 활용 (내장 등)         |
|------------------|------------------|------------|--------|------------------------------------------|--------------------------|
| **버블 정렬**        | O(n²)            | O(1)       | ✅     | 학습용, 구현 연습용                        | 거의 안 씀                  |
| **선택 정렬**        | O(n²)            | O(1)       | ❌     | 학습용, 교환 횟수 최소화가 중요할 때             | 거의 안 씀                  |
| **삽입 정렬**        | O(n²)            | O(1)       | ✅     | 데이터가 거의 정렬된 경우, 소규모 배열         | Timsort 내부 구성요소         |
| **퀵 정렬**         | O(n log n)       | O(log n)   | ❌     | 평균적으로 매우 빠른 정렬, 실전 대용량 정렬에 적합   | C/C++ 기본 정렬 (`std::sort`) |
| **병합 정렬**        | O(n log n)       | O(n)       | ✅     | 안정성이 필요한 정렬, LinkedList, 외부 정렬 등 | Java의 `Collections.sort()` |
| **힙 정렬**         | O(n log n)       | O(1)       | ❌     | 메모리를 적게 쓰면서도 빠른 정렬이 필요할 때     | 우선순위 큐 구현에 사용됨       |
| **카운팅 정렬**      | O(n + k)         | O(n + k)   | ✅     | 값의 범위가 작고 정수일 때 (예: 시험 점수 정렬)   | 알고리즘 문제, 특수 상황        |
| **기수 정렬**        | O(n × 자릿수)     | O(n + k)   | ✅     | 문자열, 긴 정수 등 자릿수 기반 정렬             | 전화번호, 학번 정렬 등          |
| **버킷 정렬**        | O(n + k)         | O(n + k)   | ✅     | 값이 균등하게 분포될 때 (예: 소수, 실수 정렬)     | 특수 상황에서 빠름             |
| **Timsort**       | O(n log n)       | O(n)       | ✅     | 대부분의 실전 정렬, 데이터가 거의 정렬됐을 때   | Python `sort()`, Java 정렬 등  |
| **Introsort**     | O(n log n)       | O(1)       | ❌     | 최악 상황 대비까지 고려한 빠른 하이브리드 정렬    | C++ `std::sort()` 내부         |

---