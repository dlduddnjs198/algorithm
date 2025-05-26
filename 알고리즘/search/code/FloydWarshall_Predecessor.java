import java.io.*;
import java.util.*;

// 📌 플로이드-워셜 알고리즘 + 경로 복원
// - 모든 정점 쌍의 최단 거리 계산
// - 실제 경로까지 복원 가능하게 via 배열 사용
// - 음수 간선 허용 (단, 음의 사이클은 감지만 가능)
//
// ✅ 시간복잡도: O(N³) (3중 for문)
// ✅ 공간복잡도: O(N²) (거리배열 + 경로추적배열)
//
// ⚠️ N이 500 이상이면 시간 초과 가능성이 있으므로 제한 필요
// 🎯 실제 경로를 복원할 수 있어 실전 문제(경로 출력 포함)에 매우 유용

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int[][] dist; // 거리 배열
    static int[][] via; // via[i][j]는 i -> j로 갈 때, i 다음에 가야 할 정점
    static int N, M; // 정점 수, 간선 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 정점 수 N, 간선 수 M 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2. 거리 배열 초기화
        dist = new int[N+1][N+1];
        via = new int[N + 1][N + 1];

        for(int i=1;i<=N;i++){
            Arrays.fill(dist[i], INF); // 처음엔 모두 도달 불가능
            dist[i][i] = 0; // 자기 자신까지 거리는 0
        }

        // 3. 간선 입력
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 동일한 from -> to 간선이 들어올 경우 더 짧은 것만 유지
            if (cost < dist[from][to]) {
                dist[from][to] = cost;
                via[from][to] = to; // 직접 연결된 경우: 다음 정점은 도착지 자체
            }
        }

        // 4. 플로이드 워셜(3중 for문)
        for(int k=1;k<=N;k++){ // 중간 정점
            for(int i=1;i<=N;i++){ // 시작 정점
                for(int j=1;j<=N;j++){ // 끝 정점
                    // i -> k -> j 경로가 기존보다 더 짧으면 갱신
                    if(dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]){
                        // 거리 갱신
                        dist[i][j] = dist[i][k] + dist[k][j];

                        // 경로 갱신: i → j 경로는 이제 i → k → j → ...
                        // 따라서 i에서 시작할 때는 i → k의 경로를 따라가야 함
                        via[i][j] = via[i][k];
                    }
                }
            }
        }

        // 5. 결과 출력
        System.out.println("=== 최단 거리 결과 ===");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

        // 6. 경로 복원 결과 출력
        System.out.println("\n=== 경로 복원 출력 ===");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 자기 자신 또는 갈 수 없는 경우는 제외
                if (i == j || dist[i][j] == INF) continue;

                List<Integer> path = getPath(i, j);

                System.out.print("경로 " + i + " → " + j + " (" + dist[i][j] + "): ");
                for (int node : path) System.out.print(node + " ");
                System.out.println();
            }
        }
    }

    /**
     * 경로 복원 함수
     * via[i][j] 정보를 따라가면서 i → j 경로의 실제 경로를 리스트로 반환
     * @param i 시작 정점
     * @param j 도착 정점
     * @return 경로 리스트 (시작 → 중간들 → 도착)
     */
    private static List<Integer> getPath(int i, int j) {
        List<Integer> path = new ArrayList<>();

        if(via[i][j] == 0) return path; // 경로 없음

        int curPath = i;
        path.add(curPath);

        // via를 따라가면서 도착지까지 경로 추적
        while (curPath != j){
            curPath = via[curPath][j];
            path.add(curPath);
        }

        return path;
    }

}