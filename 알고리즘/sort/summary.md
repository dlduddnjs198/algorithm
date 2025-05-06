# 📊 정렬 알고리즘 분류

## 📌 목차

- [1. 비교 기반 정렬 (Comparison Sort)](#1-비교-기반-정렬-comparison-sort)
- [2. 비비교 정렬 (Non-Comparison Sort)](#2-비비교-정렬-non-comparison-sort)
- [3. 기타 정렬 응용](#3-기타-정렬-응용)
- [🧠 참고 요약](#🧠-참고-요약)

---

## 1. 비교 기반 정렬 (Comparison Sort)

- **버블 정렬 (Bubble Sort)**
    - O(n²), 인접 요소 교환, 가장 단순함
- **선택 정렬 (Selection Sort)**
    - O(n²), 최소값 선택해 앞으로 이동
- **삽입 정렬 (Insertion Sort)**
    - O(n²), 정렬된 범위에 삽입
- **병합 정렬 (Merge Sort)**
    - O(n log n), 안정 정렬, 분할정복
- **퀵 정렬 (Quick Sort)**
    - O(n log n) 평균, 최악 O(n²), 불안정 정렬
- **힙 정렬 (Heap Sort)**
    - O(n log n), 최대 힙/최소 힙 이용
- **셸 정렬 (Shell Sort)**
    - 삽입 정렬 개선, 간격 기반 비교

---

## 2. 비비교 정렬 (Non-Comparison Sort)

> 비교 없이 정수 기반 키를 이용해 정렬 (O(n))

- **카운팅 정렬 (Counting Sort)**
    - 정수 배열 전용, 데이터 범위 작을 때 효과적
- **기수 정렬 (Radix Sort)**
    - 자릿수 기준으로 여러 번 정렬
- **버킷 정렬 (Bucket Sort)**
    - 구간별로 나눈 뒤 개별 정렬

---

## 3. 기타 정렬 응용

- **Timsort**
    - 자바, 파이썬 내부 정렬 알고리즘
    - 병합 + 삽입 정렬 기반
- **Stable Sort / Unstable Sort**
    - 같은 값일 때 순서 유지 여부
- **Partial Sort / Top-K 정렬**
    - 전체 정렬 대신 상위 K개만 정렬 (ex: PriorityQueue)
- **Custom Comparator 정렬**
    - Comparator를 사용한 사용자 정의 정렬

---

## 🧠 참고 요약

| 정렬 방식 | 평균 시간복잡도 | 안정성 | 특징 |
|-----------|------------------|--------|------|
| 버블 / 선택 / 삽입 | O(n²) | 버블/삽입 ✅ | 쉬움, 비효율 |
| 퀵 정렬 | O(n log n) (평균) | ❌ | 매우 빠름, 불안정 |
| 병합 정렬 | O(n log n) | ✅ | 안정, 추가 메모리 |
| 힙 정렬 | O(n log n) | ❌ | 불안정, 힙 자료구조 |
| 카운팅 정렬 | O(n + k) | ✅ | 정수만 가능 |
| 기수 정렬 | O(n × 자릿수) | ✅ | 범위 큰 정수 가능 |
| 버킷 정렬 | O(n + k) | ✅ | 균등 분포 시 빠름 |

---
