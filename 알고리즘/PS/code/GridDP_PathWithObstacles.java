import java.util.*;


// 그리드 DP - 5번 유형: 점프/이동 제한
public class GridDP_PathWithObstacles {
    public static int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 🔹 DP 배열 생성: dp[i][j]는 (0,0) → (i,j)까지의 경로 수를 의미
        int[][] dp = new int[m][n];

        // ✅ 시작점이 장애물이면 경로 자체가 없음
        if (grid[0][0] == 1) return 0;

        // 🔸 시작점 초기화: 장애물이 없다면 경로 수 1
        dp[0][0] = 1;

        // 🔹 첫 번째 행 초기화
        for (int j = 1; j < n; j++) {
            // 이전 칸이 경로가 0이거나 현재 칸이 장애물이면 경로 수는 0
            dp[0][j] = (grid[0][j] == 0 && dp[0][j - 1] == 1) ? 1 : 0;
        }

        // 🔹 첫 번째 열 초기화
        for (int i = 1; i < m; i++) {
            // 이전 칸이 경로가 0이거나 현재 칸이 장애물이면 경로 수는 0
            dp[i][0] = (grid[i][0] == 0 && dp[i - 1][0] == 1) ? 1 : 0;
        }

        // 🔁 DP 테이블 채우기
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 현재 위치에 장애물이 있다면 경로 수는 0
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    // 장애물이 없으면 위 + 왼쪽에서 오는 경로 수를 더함
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // ✅ 도착점까지의 경로 수 반환
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println("경로 수: " + uniquePathsWithObstacles(grid)); // 출력: 2
    }
}
