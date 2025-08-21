import java.io.*;
import java.util.*;

/**
 * ✅ 시간 복잡도: O(N^2 * 2^N)
 * - N: 도시의 개수 (최대 16 -> 약 100만)
 * - 상태 수: 방문한 도시의 집합 (2^N 가지)
 * - 각 상태마다 마지막으로 방문한 도시 수: N
 *
 * ✅ 문제 정의:
 * - 각 도시 간 이동 비용이 주어졌을 때,
 * - 모든 도시를 정확히 한 번씩 방문하고,
 * - 다시 출발 도시로 돌아오는 최소 비용 경로를 구하라.
 */
public class BitmaskingTSP {

    static final int INF = 1_000_000_000;
    static int N; // 도시 수
    static int[][] W; // 이동 비용 인접 행렬
    // dp[visited][current] : visited 상태에서 current 도시에 있을 때, 남은 도시 다 방문하고 출발지(도시0)으로 돌아가는 최소 비용
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // visited 상태는 0 ~ (1<<N) - 1
        dp = new int[1 << N][N];
        for (int[] row : dp) Arrays.fill(row, -1);

        // 시작 도시: 0번
        System.out.println(tsp(1, 0));
    }

    /**
     * @param visited 비트마스크로 표현된 방문 상태 (예: 1011 = 0,1,3번 도시 방문)
     * @param current 현재 도시 번호
     * @return 현재 상태에서 남은 도시들을 모두 방문하고 시작점으로 돌아가는 최소 비용
     */
    public static int tsp(int visited, int current) {
        // 모든 도시 방문 완료
        if (visited == (1 << N) - 1) {
            // 마지막으로 도착한 도시(current)에서 다시 시작 도시(0번)로 돌아가는 경로가 존재한다면 그 비용을 반환
            return (W[current][0] == 0) ? INF : W[current][0];
        }

        // 메모이제이션: 이미 계산된 상태면 반환(dp에 이미 저장되는 시점에서 최소 보장)
        if (dp[visited][current] != -1) return dp[visited][current];

        int minCost = INF;

        // 다음 도시 후보 탐색
        for (int next = 0; next < N; next++) {
            // 이미 방문했거나, current → next로 가는 경로가 없으면 스킵
            if ((visited & (1 << next)) != 0 || W[current][next] == 0) continue;

            // 다음 도시 방문 상태 갱신
            int nextVisited = visited | (1 << next);

            // 재귀적으로 최소 비용 갱신
            // 여러 경로를 재귀로 탐색하면서 해당 next에 도달하는 최소 비용을 찾는 과정
            // 여기서 tsp(nextVisited, next)는 (current를 지나) next부터 나머지 도시들 돌고 0으로 돌아가는 최소 비용이다.
            // 이것과 현재 위치에서 next까지 가는걸 합치고 minCost로 비교한다.
            int cost = tsp(nextVisited, next) + W[current][next];
            minCost = Math.min(minCost, cost);
        }

        // 메모이제이션 저장 후 반환
        return dp[visited][current] = minCost;
    }
}
