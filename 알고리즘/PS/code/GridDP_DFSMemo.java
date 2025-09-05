import java.util.*;


// 그리드 DP - 3번 유형: DFS + 메모이제이션

// 시간복잡도 : O(m*n), 공간복잡도 : O(n)
// m은 행, n은 열이다.
public class GridDP_DFSMemo {

    // 무한대를 나타내는 상수 (아직 방문 안 한 칸 표시용)
    private static final int INF = Integer.MAX_VALUE;

    // ✅ 진입 함수: 전체 grid를 받아서 최소 경로 합을 계산
    public static int minPathSum(int[][] grid) {
        int m = grid.length;       // 행 개수
        int n = grid[0].length;    // 열 개수

        // 메모이제이션용 배열 (각 칸까지의 최소 경로 합을 저장)
        int[][] memo = new int[m][n];

        // 아직 방문하지 않은 칸은 INF로 초기화
        for (int[] row : memo) {
            Arrays.fill(row, INF);
        }

        // (m-1, n-1) 지점까지의 최소 경로 합을 계산해서 반환
        return dfs(grid, m - 1, n - 1, memo);
    }

    // ✅ DFS + 메모이제이션 함수
    // (x, y)까지 도달하는 데 필요한 최소 경로 합을 계산
    // 역방향 사고로 들어간다.
    private static int dfs(int[][] grid, int x, int y, int[][] memo) {
        // 🔸 기저 조건 1: 시작점 (0, 0)에 도달 → 자기 자신의 값이 최소값
        if (x == 0 && y == 0) return grid[0][0];

        // 🔸 기저 조건 2: 이미 계산된 위치 → 중복 계산 방지
        if (memo[x][y] != INF) return memo[x][y];

        int minSum = INF; // 현재 위치까지의 최소 경로 합 초기값 설정

        // 🔼 위에서 내려오는 경우 고려 (경계 조건: x > 0)
        if (x > 0) {
            int fromTop = dfs(grid, x - 1, y, memo);   // 위쪽 칸까지의 최소 경로
            minSum = Math.min(minSum, fromTop);        // 최소값 갱신
        }

        // ◀️ 왼쪽에서 오는 경우 고려 (경계 조건: y > 0)
        if (y > 0) {
            int fromLeft = dfs(grid, x, y - 1, memo);  // 왼쪽 칸까지의 최소 경로
            minSum = Math.min(minSum, fromLeft);       // 최소값 갱신
        }

        // 현재 칸까지의 최소 경로 = 왼쪽/위쪽 중 작은 값 + 현재 칸의 값
        memo[x][y] = minSum + grid[x][y];

        // 계산된 값을 반환
        return memo[x][y];
    }

    // ✔️ 테스트용 main 함수
    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        // 정답: 7 (1→3→1→1→1)
        System.out.println(minPathSum(grid));
    }
}
