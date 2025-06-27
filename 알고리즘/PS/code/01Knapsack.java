import java.util.*;

/*
🎒 0/1 Knapsack Problem

📌 문제 정의:
- N개의 물건이 있고, 각 물건은 '무게(weight)'와 '가치(value)'를 갖는다.
- 최대 무게 W까지 담을 수 있는 가방이 있을 때,
  물건을 한 번만(0 또는 1번) 담을 수 있다는 조건 하에 최대 가치를 얻을 수 있는 조합을 구하는 문제

📌 핵심 조건:
- 각 물건은 한 번만 담을 수 있음 (0번 또는 1번 선택)
- 부분 문제는 "i번째 물건까지 고려했을 때, 무게 j 이하로 얻을 수 있는 최대 가치"

📌 사용 알고리즘:
- Dynamic Programming (DP) 방식
- Bottom-Up 방식 (반복문 기반)으로 구현
  → 작은 문제부터 차례대로 계산해서 큰 문제 해결

📌 시간 복잡도:
- 일반 DP: O(N * W)
    → N: 물건 개수, W: 가방의 최대 무게
- 공간 최적화 (1차원 배열): O(W)까지도 가능

📌 활용 예시:
- 제한된 리소스 내에서 최대 효과를 얻는 문제에 적합
  예: 예산 제한 내에서 가장 많은 이익을 얻는 투자 선택,
      제한된 인력으로 가장 높은 효율의 작업 배치 등

⚠️ 주의할 점:
- 가방의 최대 무게가 너무 클 경우(W가 수천~수만 이상), 시간 초과 주의
- 메모리 제한이 있을 경우 → 공간 최적화 고려 (1차원 배열 사용)
- 같은 물건을 여러 번 담을 수 있는 경우는 Unbounded Knapsack임 (다른 알고리즘)

*/
public class 01Knapsack {
    public static void main(String[] args) {
        // 예제 입력: 물건의 개수 N, 가방의 최대 무게 W
        int N = 4; // 물건 4개
        int W = 7; // 최대 무게는 7

        // 각 물건의 무게와 가치 (1-based index를 위해 배열 크기를 N+1로 설정)
        int[] weights = {0, 6, 4, 3, 5};  // 0번 인덱스는 더미 (사용하지 않음)
        int[] values  = {0,13, 8, 6,12};  // weights[i], values[i]는 i번째 물건의 정보

        int result1 = basicKnapsack(N, W, weights, values);
        System.out.println("🧮 기본 DP 방식 최대 가치: " + result1);

        int result2 = optimizedKnapsack(N, W, weights, values);
        System.out.println("🧮 공간 최적화 방식 최대 가치: " + result2);
    }

    /**
     * 🧱 기본 2차원 DP 배열을 사용하는 0/1 Knapsack 풀이
     * @param N: 물건 개수
     * @param W: 가방의 최대 무게
     * @param weights: 각 물건의 무게 배열 (1-based)
     * @param values: 각 물건의 가치 배열 (1-based)
     * @return 최대 가치
     */
    public static int basicKnapsack(int N, int W, int[] weights, int[] values) {
        // dp[i][j] = i번 물건까지 고려, 무게 j일 때 최대 가치
        // 예를 들어서, i=2라면 2번째 물건까지 고려중이라는 뜻
        // j=5라면 최대 5kg까지만 넣을 수 있을 때의 최대 가치라는 뜻
        int[][] dp = new int[N + 1][W + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= W; j++) {
                if (j < weights[i]) {
                    // 현재 물건을 넣을 수 없는 경우 → 이전 결과 그대로 사용
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 현재 물건을 넣지 않음 vs 넣음 중 최대 가치 선택
                    dp[i][j] = Math.max(
                            dp[i - 1][j],                                   // 안 담는 경우
                            dp[i - 1][j - weights[i]] + values[i]          // 담는 경우
                    );
                }
            }
        }

        return dp[N][W]; // N개 물건, 최대 무게 W일 때 최대 가치
    }

    /**
     * 💾 공간 최적화 1차원 배열을 사용하는 0/1 Knapsack 풀이
     * @param N: 물건 개수
     * @param W: 가방의 최대 무게
     * @param weights: 각 물건의 무게 배열 (1-based)
     * @param values: 각 물건의 가치 배열 (1-based)
     * @return 최대 가치
     */
    public static int optimizedKnapsack(int N, int W, int[] weights, int[] values) {
        int[] dp = new int[W + 1]; // dp[j] = 무게 j일 때 최대 가치 (1차원 배열)

        for (int i = 1; i <= N; i++) {
            // 0/1 Knapsack은 같은 물건을 한 번만 담을 수 있기 때문에
            // 뒤에서부터 갱신해야 이전 값이 덮어지지 않음!
            for (int j = W; j >= weights[i]; j--) {
                dp[j] = Math.max(
                        dp[j],                              // 현재 물건을 안 담음
                        dp[j - weights[i]] + values[i]     // 현재 물건을 담음
                );
            }
            // 무게가 weights[i]보다 작은 j는 업데이트 필요 없음 (담을 수 없으므로)
        }

        return dp[W]; // 최대 무게 W일 때의 최대 가치
    }
}