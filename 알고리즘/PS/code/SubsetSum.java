import java.util.*;

/**
 * 🎯 Subset Sum Problem
 *
 * ✅ 문제 설명:
 * 주어진 정수 배열에서 일부 원소를 골라서, 그 합이 정확히 target이 되는 조합이 존재하는지를 판단하는 문제.
 *
 * ✅ 사용 예:
 * - 배열: {3, 34, 4, 12, 5, 2}
 * - target: 9
 * - 정답: true (예: 4 + 5)
 *
 * ✅ 알고리즘 유형:
 * - 0/1 Knapsack의 특별한 형태 (각 아이템은 한 번씩만 사용 가능)
 * - 가치가 없고, 무게(값)만 의미 있는 상황
 *
 * ✅ 풀이 방식:
 * - dp[i][j] = i번째 원소까지 고려해서, 합이 j가 되는 부분집합이 존재하는지 여부
 * - 이차원 DP를 1차원으로 최적화 가능
 *
 * ✅ 시간복잡도: O(N * S)  (N: 원소 개수, S: target sum)
 * ✅ 공간복잡도: O(S)
 */

public class SubsetSum {
    // 테스트용 main 메서드
    public static void main(String[] args) {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 9;

        if (isSubsetSum(nums, target)) {
            System.out.println("✅ 부분집합이 존재합니다.");
        } else {
            System.out.println("❌ 부분집합이 존재하지 않습니다.");
        }
    }

    public static boolean isSubsetSum(int[] nums, int target) {
        int N = nums.length;

        // dp[j] = 합이 j가 되는 부분집합이 존재하는가?
        boolean[] dp = new boolean[target + 1];

        // 합이 0이 되는 경우는 항상 true (아무것도 선택하지 않으면 됨)
        dp[0] = true;

        // 모든 원소에 대해 반복
        for (int i = 0; i < N; i++) {
            int num = nums[i];

            // 💡 0/1 Knapsack처럼 뒤에서부터 순회
            // 이유: 같은 원소를 중복으로 사용하지 않기 위해
            for (int j = target; j >= num; j--) {
                // 현재 숫자를 포함하거나 포함하지 않거나
                dp[j] = dp[j] || dp[j - num];
            }
        }

        // 목표 합 target을 만들 수 있는지 여부 반환
        return dp[target];
    }
}
