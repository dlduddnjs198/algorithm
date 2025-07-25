# 📌 백트래킹 (Backtracking)

백트래킹은 **모든 경우의 수를 탐색하면서도, 불필요한 탐색은 생략(가지치기)하여** 효율적으로 해를 찾는 알고리즘입니다.

---

## ✅ 정의

> 백트래킹은 DFS(깊이 우선 탐색)의 일종으로, **조건을 만족하지 않는 경로는 더 이상 탐색하지 않고 되돌아가는 방법**입니다.

- **상태 공간 트리(state space tree)를** 따라 탐색
- **조건에 어긋나는 분기**를 조기에 차단 → 불필요한 연산 줄임

---

## ✅ 핵심 구성요소

| 구성 요소 | 설명 |
|------------|------|
| `재귀 함수` | 현재 상태를 기준으로 다음 상태를 탐색 |
| `조건 검사` | 유망하지 않으면 탐색 중단 (가지치기) |
| `상태 복구` | 한 단계 돌아올 때 이전 상태로 복원 |

---

## ✅ 일반적인 틀 (Java 스타일 Pseudo Code)

```java
// 백트래킹 구조 틀
void backtrack(상태) {
    if (종료 조건) {
        // 결과 처리
        return;
    }

    for (가능한 선택 : 선택들) {
        if (!조건을 만족) continue; // 가지치기

        // 상태 변경
        ...

        backtrack(다음 상태);

        // 상태 원복
        ...
    }
}
```

---

## ✅ 백트래킹 vs 완전탐색

| 항목 | 완전탐색 | 백트래킹 |
|------|-----------|--------------|
| 탐색 범위 | 가능한 모든 경우 | 조건 만족하는 경우만 |
| 성능 | 느릴 수 있음 | 더 빠름 (가지치기) |
| 구현 | 단순 반복 | 재귀 + 상태 복구 필요 |

---

## ✅ 예시 문제 유형

| 문제 유형 | 설명 |
|------------|------|
| N-Queen | 체스판에 퀸을 서로 공격하지 않게 놓기 |
| 순열 / 조합 생성 | 중복 없는 수열 구성 |
| 부분집합 생성 | 특정 합/조건을 만족하는 부분집합 찾기 |
| 스도쿠 / 미로 문제 | 유효한 해를 찾아가는 퍼즐 |

---

## ✅ 예시 코드 링크

- [N-Queen 문제](./examples/nqueen.java)
- [순열 생성](./examples/permutation.java)
- [부분집합 합](./examples/subset_sum.java)
- [스도쿠 풀이](./examples/sudoku.java)

---

## ✅ 시간복잡도 참고

| 문제 | 시간복잡도 |
|-------|--------------|
| N-Queen (N×N) | O(N!) |
| 순열 | O(N!) |
| 조합 | O(2^N) (부분집합) |

> 실제 탐색 범위는 **가지치기 조건에 따라 훨씬 작아질 수 있음**

---

## ✅ 마무리 요약

- **백트래킹은 브루트포스보다 빠른 탐색 방식**
- **DFS 기반 + 조건 기반 가지치기 + 상태 복원**이 핵심
- 구현 연습이 많을수록 **패턴이 눈에 익음**
- **모든 경우의 수를 탐색하지만, 불필요한 가지는 줄이자**
