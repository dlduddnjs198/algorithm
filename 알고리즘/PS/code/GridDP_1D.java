import java.util.*;


// 그리드 DP - 2번 유형: 공간 최적화(1차원 배열)

// 시간복잡도 : O(m*n), 공간복잡도 : O(n)
// m은 행, n은 열이다.
public class GridDP_1D {

    // ✅ 테스트용 메인 함수
    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int result = minPathSum_1D(grid);
        System.out.println("최소 경로 합: " + result); // 출력: 7
    }

    public static int minPathSum_1D(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];  // 🔹 현재 행에서의 최소 누적합만 저장 (이전 행은 덮어씀)

        // 🔸 첫 행 초기화: 오직 오른쪽으로만 이동 가능
        dp[0] = grid[0][0];  // 시작 위치
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];  // 왼쪽에서만 올 수 있음
        }

        // 🔸 두 번째 행부터는 위쪽과 왼쪽을 비교해서 최소 경로 구함
        for (int i = 1; i < m; i++) {
            // 첫 열은 위쪽에서만 올 수 있음 → dp[0] 갱신
            dp[0] += grid[i][0];

            for (int j = 1; j < n; j++) {
                // 🔹 dp[j] = 위쪽에서 오는 값
                // 🔹 dp[j - 1] = 왼쪽에서 오는 값
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }

        // 🔹 마지막 위치까지의 최소 경로 합이 저장되어 있음
        return dp[n - 1];
    }
}
