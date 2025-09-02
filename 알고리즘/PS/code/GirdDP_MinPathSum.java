import java.util.*;


// 그리드 DP - 1번 유형: 최소 경로 합 (Minimum Path Sum)
public class GridDP_MinPathSum {

    /**
     * 📌 2차원 DP를 이용한 최소 경로 합 계산
     * - 시간 복잡도: O(m * n)  
     * - 공간 복잡도: O(m * n)
     * 
     * m은 행, n은 열
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length;        // 행 수
        int n = grid[0].length;     // 열 수

        // 📌 DP 테이블 선언
        // dp[i][j] = (0,0)에서 (i,j)까지 이동할 때 최소 경로 합
        int[][] dp = new int[m][n];

        // 📍 (0,0)은 출발지 → 그대로 복사
        dp[0][0] = grid[0][0];

        // 📍 첫 번째 행은 왼쪽에서만 올 수 있음 → 누적합 계산
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 📍 첫 번째 열은 위에서만 올 수 있음 → 누적합 계산
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 📍 나머지 칸들은 왼쪽 or 위쪽에서 오는 두 가지 경우 중 최소 선택
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 왼쪽에서 오는 경로와 위에서 오는 경로 중 최소값 선택
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // 📍 도착지 (m-1,n-1)까지의 최소 경로 합 반환
        return dp[m - 1][n - 1];
    }

    // ✅ 테스트용 메인 함수
    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int result = minPathSum(grid);
        System.out.println("최소 경로 합: " + result); // 출력: 7
    }
}
