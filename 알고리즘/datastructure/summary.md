# 🧱 자료구조 정리

## 📌 목차

- [1. 선형 자료구조](#1-선형-자료구조)
- [2. 비선형 자료구조](#2-비선형-자료구조)
- [3. 해시 기반 자료구조](#3-해시-기반-자료구조)
- [4. 트리 구조](#4-트리-구조)
- [5. 그래프 구조](#5-그래프-구조)
- [6. 고급 자료구조 및 응용](#6-고급-자료구조-및-응용)
- [🧠 참고 요약](#🧠-참고-요약)

---

## 1. 선형 자료구조

- 배열 (Array)
- [리스트 (ArrayList, LinkedList)](detail/List.md)
- 스택 (Stack)
- 큐 (Queue)
- 덱 (Deque)

---

## 2. 비선형 자료구조

- [트리 (Tree)]([그래프&트리.md](%EA%B7%B8%EB%9E%98%ED%94%84%26%ED%8A%B8%EB%A6%AC.md))
- [그래프 (Graph)]([그래프&트리.md](%EA%B7%B8%EB%9E%98%ED%94%84%26%ED%8A%B8%EB%A6%AC.md))

---

## 3. 해시 기반 자료구조

해시 기반 구조는 **탐색 속도를 높이기 위해 자주 사용되는 선형 컬렉션**입니다.  
기본적으로 평균 O(1)의 탐색/삽입 속도를 가지며, **Set**과 **Map** 계열로 나눌 수 있습니다.

### ✅ Map과 Set의 차이

- **Set**은 “값의 존재 유무”만을 판단하는 자료구조입니다.  
  중복을 허용하지 않으며, 하나의 값만 저장합니다.  
  `add(value)`, `contains(value)` 같은 연산이 핵심입니다.

- **Map**은 “키-값 쌍”을 저장하는 구조입니다.  
  하나의 키에 하나의 값을 대응시켜 저장하며, 키는 중복이 불가능합니다.  
  `put(key, value)`, `get(key)`, `containsKey(key)`가 주된 연산입니다.

### ✅ Hash / Tree / Linked 계열 차이

- **Hash 기반**:  
  내부적으로 해시 함수를 이용하여 빠른 탐색 (평균 O(1))을 지원합니다.  
  순서나 정렬은 보장되지 않습니다. → `HashMap`, `HashSet`

- **Linked 기반**:  
  해시 구조에 **입력 순서를 기억하는 연결 리스트**를 추가하여,  
  **입력 순서 or 접근 순서**대로 순회할 수 있게 만든 구조입니다.  
  → `LinkedHashMap`, `LinkedHashSet`

- **Tree 기반**:  
  이진 탐색 트리(Red-Black Tree)를 사용하여,  
  **자동 정렬**과 **범위 기반 탐색**을 지원합니다.<br>
  삽입/삭제/탐색 모두 O(log n)
  → `TreeMap`, `TreeSet`

### 🔹 Set 계열

- **HashSet**
  - 시간 복잡도: 삽입/삭제/탐색 평균 O(1), 최악 O(n)
  - 중복 없는 집합 구조, 순서 없음
  - 해시 충돌이 없을 때 매우 빠름
- **LinkedHashSet**
  - 시간 복잡도: 삽입/삭제/탐색 평균 O(1)
  - 입력 순서를 유지한 순회 가능
  - 메모리 사용량은 HashSet보다 다소 큼
- **TreeSet**
  - 시간 복잡도: 삽입/삭제/탐색 O(log n)
  - 자동 정렬된 집합
  - `floor`, `ceiling`, `higher`, `lower` 같은 범위 기반 탐색 가능

### 🔹 Map 계열
- **HashMap**
  - 시간 복잡도: 삽입/삭제/탐색 평균 O(1), 최악 O(n)
  - 순서를 보장하지 않음
  - 키 중복 불가, 해시 충돌 시 성능 저하 가능
- **LinkedHashMap**
  - 시간 복잡도: 삽입/삭제/탐색 평균 O(1)
  - 입력 순서 또는 접근 순서 유지
  - `accessOrder=true` 옵션으로 LRU 캐시 구현 가능
- **TreeMap**
  - 시간 복잡도: 삽입/삭제/탐색 O(log n)
  - 키를 자동 정렬 (기본은 오름차순, 커스텀 comparator 지원)
  - `subMap`, `floorKey`, `ceilingKey` 등 범위 탐색 지원

> 📌 TreeMap, TreeSet은 내부적으로 해시가 아닌 **이진 탐색 트리 기반**이므로,  
> 구조적으로는 [트리 구조](#4-트리-구조)에도 포함됩니다. 다만 탐색 목적상 여기서도 함께 다룹니다.

### 🔗 세부 문서

- [Set 구조 정리 (HashSet, TreeSet, LinkedHashSet)](detail/Set.md)
- [Map 구조 정리 (HashMap, TreeMap, LinkedHashMap)](detail/Map.md)

---

## 4. 트리 구조

> 📌 *자료구조이자 탐색 알고리즘의 기반이 되는 구조들*

- 이진 트리 (Binary Tree)
- 이진 탐색 트리 (BST)
- 균형 이진 트리 (AVL, Red-Black Tree)
- 힙 (Heap)
    - 우선순위 큐와 연동
- 트라이 (Trie)
    - 문자열 접두사 처리
- 세그먼트 트리 / 펜윅 트리 (BIT)
    - 구간 탐색용

### 🌳 트리 순회 방식 (Traversal)

- 전위 순회 (Preorder): Root → Left → Right
- 중위 순회 (Inorder): Left → Root → Right
- 후위 순회 (Postorder): Left → Right → Root
- 레벨 순회 (Level-order): BFS 기반

---

## 5. 그래프 구조

- 인접 행렬 (Adjacency Matrix)
- 인접 리스트 (Adjacency List)

---

## 6. 고급 자료구조 및 응용

- [Disjoint Set (유니온 파인드)]([유니온파인드.md](%EC%9C%A0%EB%8B%88%EC%98%A8%ED%8C%8C%EC%9D%B8%EB%93%9C.md))
- [Priority Queue (Heap 기반)]([우선순위큐.md](%EC%9A%B0%EC%84%A0%EC%88%9C%EC%9C%84%ED%81%90.md))
- LRU Cache
- Sliding Window (투 포인터 기반)
- Monotonic Stack / Queue
- Suffix Array / LCP Array

---

## 🧠 참고 요약

| 자료구조 | 특징 |
|----------|------|
| 배열/리스트 | 순차 저장, 접근 빠름 |
| 스택/큐 | 삽입/삭제 구조화, 제약 있음 |
| 트리 | 계층형 데이터, 탐색/삽입 효율적 |
| 그래프 | 복잡한 관계 표현 |
| 해시 | 키 기반 탐색, 평균 O(1) |
| 고급 구조 | 특수 문제/최적화에 사용됨 |
