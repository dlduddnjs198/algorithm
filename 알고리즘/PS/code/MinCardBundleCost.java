/**
 * MinCardBundleCost.java
 * ------------------------------------------
 * ✅ 문제 설명:
 * - 서로 다른 카드 묶음 N개가 있다. 각 묶음은 몇 장의 카드가 들어 있다.
 * - 한 번에 두 묶음을 합칠 수 있으며, 이때 비용은 두 묶음 카드 수의 합이다.
 * - 모든 카드를 하나로 합칠 때까지 이 과정을 반복할 때, **총 비용의 최소값**을 구하라.
 *
 * ✅ 핵심 아이디어:
 * - 가장 작은 두 묶음을 계속해서 먼저 합치는 것이 전체 비용을 최소화하는 방법이다.
 * - 따라서 매번 가장 작은 두 값을 빠르게 꺼내야 하므로 **우선순위 큐(MinHeap)** 를 사용한다.
 *
 * ✅ 시간복잡도:
 * - 우선순위 큐에서 N개의 요소를 넣고, N-1번 꺼내고 넣기를 반복 → O(N log N)
 *
 * ✅ 입력 예:
 *   3
 *   10 20 40
 * ✅ 출력 예:
 *   100
 *
 * ✅ 설명:
 *   10+20=30 → 30+40=70 → 총합 30+70 = 100
 */

import java.util.*;

public class MinCardBundleCost {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 묶음 수 입력
        int N = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위 큐 (min-heap)

        // 각 카드 묶음의 장 수 입력 받아 큐에 삽입
        for (int i = 0; i < N; i++) {
            pq.add(sc.nextInt());
        }

        int totalCost = 0; // 최종 비용

        // 카드 묶음이 하나만 남을 때까지 반복
        while (pq.size() > 1) {
            // 가장 적은 두 묶음 꺼냄
            int first = pq.poll(); // 첫 번째 작은 묶음
            int second = pq.poll(); // 두 번째 작은 묶음

            int merged = first + second; // 두 묶음을 합친 비용
            totalCost += merged;         // 누적 비용에 추가

            pq.add(merged); // 합쳐진 묶음을 다시 우선순위 큐에 넣음
        }

        // 결과 출력
        System.out.println(totalCost);
    }
}
