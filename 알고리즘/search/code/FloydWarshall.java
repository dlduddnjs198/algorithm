import java.util.*;
import java.io.*;

// 플로이드-워셜 알고리즘 (Floyd-Warshall)
// → 모든 정점 쌍 간의 최단 거리 구하기
// → 시간복잡도 O(N³)
// → 음수 간선 허용, 음의 사이클 감지 가능

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int[][] dist; // 거리 배열
    static int N, M; // 정점 수, 간선 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 정점 수 N, 간선 수 M 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2. 거리 배열 초기화
        dist = new int[N+1][N+1];
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
            dist[from][to] = Math.min(cost, dist[from][to]);
        }

        // 4. 플로이드 워셜(3중 for문)
        for(int k=1;k<=N;k++){ // 중간 정점
            for(int i=1;i<=N;i++){ // 시작 정점
                for(int j=1;j<=N;j++){ // 끝 정점
                    // i -> k -> j 경로가 기존보다 더 짧으면 갱신
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
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

        // (선택) 음의 사이클 판별
        boolean hasNegativeCycle = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i][i] < 0) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("\n⚠️ 음의 사이클이 존재합니다.");
        }
    }

}