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
- 리스트 (ArrayList, LinkedList)
- 스택 (Stack)
- 큐 (Queue)
- 덱 (Deque)

---

## 2. 비선형 자료구조

- [트리 (Tree)]([그래프&트리.md](%EA%B7%B8%EB%9E%98%ED%94%84%26%ED%8A%B8%EB%A6%AC.md))
- [그래프 (Graph)]([그래프&트리.md](%EA%B7%B8%EB%9E%98%ED%94%84%26%ED%8A%B8%EB%A6%AC.md))

---

## 3. 해시 기반 자료구조

- HashMap / HashSet
- LinkedHashMap
- TreeMap (정렬 기반 탐색 가능)

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
