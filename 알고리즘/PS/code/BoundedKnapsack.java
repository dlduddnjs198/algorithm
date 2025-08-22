import java.util.*;

/*
🎒 Bounded Knapsack Problem (유한 개수의 0/1 배낭 문제)

📌 문제 정의:
- N개의 물건이 있고, 각 물건은 다음 정보를 가짐:
  → 무게(weight), 가치(value), 최대 사용 개수(count)
- 최대 무게 W까지 담을 수 있는 가방이 있을 때,
  물건을 개수 제한 내에서 담아 얻을 수 있는 최대 가치를 구하는 문제

📌 핵심 로직(이진 분할):
- 이 방식의 핵심 로직은 이진 분할(Binary split)이다.
예를 들어서, 20이라고 하면 1 + 2 + 4 + 8 + 5(나머지 수) 이런식으로 2의 거듭제곱 + 나머지 수 꼴로 모든 정수를 표현할 수 있다.
이런 식으로 물건을 2의 거듭제곱 + 나머지 수의 묶음으로 만들어서 0/1 knapsack 로직을 적용하는 것이다.
이것이 가능한 이유는 1~20까지 1,2,4,8,5의 조합으로 만들어질 수 있기 때문이다.(한번씩만 쓸 수 있어도)
이를 통해 속도와 중복을 제거하면서도 0/1 냅색 로직을 적용할 수 있다는 이점을 가질 수 있다.

📌 핵심 조건:
- 각 물건은 최대 count[i]번까지 담을 수 있음 (0 ~ count[i]번)
- 무게 제한 존재
- 물건은 부분적으로 쪼갤 수 없음 (정수 단위로만 담을 수 있음)

📌 알고리즘:
- Dynamic Programming (DP)
- Bottom-Up 방식 (반복문 기반)
- 아이템을 최대 개수만큼 "쪼개서 풀기" (1개씩 처리)
  → 물건 하나를 count만큼 복제하여 0/1 Knapsack처럼 다룸 (비효율)
  → 또는 Binary Optimization (이진법 분할)로 최적화 가능

📌 시간 복잡도:
- 일반 방식: O(N * W * count[i]) → 비효율
- 이진 분할 최적화: O(N * W * log(count[i])) → 이 코드에서 사용

*/

public class BoundedKnapsack {
    public static void main(String[] args) {
        int N = 3;               // 물건 수
        int W = 10;              // 가방 최대 무게
        int[] weights = {0, 3, 4, 2};    // 각 물건의 무게 (0번 인덱스는 더미)
        int[] values  = {0, 6, 7, 4};    // 각 물건의 가치
        int[] counts  = {0, 3, 1, 5};    // 각 물건의 최대 개수

        int maxValue = boundedKnapsack(N, W, weights, values, counts);
        System.out.println("최대 가치: " + maxValue);
    }

    /**
     * 유한 개수 제한이 있는 배낭 문제 풀이 함수
     */
    public static int boundedKnapsack(int N, int W, int[] weights, int[] values, int[] counts) {
        // 1차원 DP 배열: dp[j] = 무게 j일 때의 최대 가치
        int[] dp = new int[W + 1];

        // 각 물건 i에 대해 반복
        for (int i = 1; i <= N; i++) {
            int weight = weights[i];
            int value = values[i];
            int count = counts[i];

            // 💡 이진 분할 방식으로 개수를 쪼개서 여러 개의 물건으로 변환
            // 예: count=5 → 1개, 2개, 2개(나머지 수) → 총합 5개 표현 가능
            for (int k = 1; count > 0; k <<= 1) {
                int qty = Math.min(k, count); // 더 이상 남은 수량이 적으면 남은 수량(나머지 수)만큼만 사용
                int totalWeight = weight * qty;
                int totalValue = value * qty;

                // 0/1 Knapsack 방식으로 뒤에서부터 업데이트 (중복 방지)
                for (int j = W; j >= totalWeight; j--) {
                    dp[j] = Math.max(dp[j], dp[j - totalWeight] + totalValue);
                }

                count -= qty; // 사용한 개수만큼 차감
            }
        }

        return dp[W]; // 전체 무게 W일 때 최대 가치 반환
    }
}
