import java.util.*;



// 그리드 DP - 4번 유형: 경로 수 저장

// 시간복잡도 : O(m*n), 공간복잡도 : O(n)
// m은 행, n은 열이다.
public class GridDP_PathCount {
    public static int countPaths(int m, int n) {
        // ✅ dp[i][j]는 (0,0)에서 (i,j)까지 도달하는 경로의 수를 의미
        int[][] dp = new int[m][n];

        // 🔹 첫 번째 행 초기화: (0,0) ~ (0,n-1)까지는 오른쪽으로만 이동 가능
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;  // 오른쪽으로만 가는 경로는 1개뿐
        }

        // 🔹 첫 번째 열 초기화: (0,0) ~ (m-1,0)까지는 아래로만 이동 가능
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;  // 아래로만 가는 경로도 1개뿐
        }

        // 🔁 DP 테이블 채우기
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 🔸 왼쪽에서 오는 경우 + 위쪽에서 오는 경우
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // ✅ 도착지점의 경로 수를 반환
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        System.out.println("총 경로 수: " + countPaths(m, n)); // 출력: 6
    }
}