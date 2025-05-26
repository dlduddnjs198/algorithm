import java.util.*;
import java.io.*;

// 밸만-포드 알고리즘(기본형) -> 단일 시작점 최단경로 + 음의 사이클 탐지
// 시간복잡도 O(VE)
public class Main {
    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M; // 정점 수, 간선 수
    static List<Edge> edges; // 간선 리스트(다익처럼 인접리스트 만들필욘 없음)
    static long[] dist; // 최단 거리 배열
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 정점 수(N), 간선 수(M) 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();

        // 2. 간선 정보 입력(from, to, cost)
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to ,cost));
        }

        // 3. 시작 정점 입력
        int start = Integer.parseInt(br.readLine());

        // 4. 거리 배열 초기화
        dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // 5. 벨만 포드 진행
        boolean hasNegativeCycle = bellmanFord();

        if(hasNegativeCycle){
            System.out.println("음의 사이클이 존재합니다.");
        }else{
            System.out.println("최단 거리 결과");
            for(int i=1;i<=N;i++){
                System.out.println("Node " + i + ": " + (dist[i]==INF ? "INF" : dist[i]));
            }
        }

    }

    // 벨만 포드 알고리즘 수행 함수
    private static boolean bellmanFord(){
        // 1. V-1번 간선 전체 반복(완화)
        for(int i=1;i<=N-1;i++){
            for(Edge e : edges){
                if(dist[e.from] != INF && dist[e.from] + e.cost < dist[e.to]){
                    dist[e.to] = dist[e.from] + e.cost;
                }
            }
        }

        // 2. V번째 반복 : 값이 줄어들면 -> 음의 사이클 존재
        for(Edge e : edges){
            if(dist[e.from] != INF && dist[e.from] + e.cost < dist[e.to]){
                return true; // 음의 사이클 존재
                // 음의 사이클에 영향을 받는 정점만 추려내는 로직은 여기서 return 말고 Set에 해당 정점을 add해주고
                // 나중에 BFS/DFS로 영향을 받는 정점을 추려내고 그 이외를 뽑아내면 된다.(이 경우 인접리스트 필요)
            }
        }

        return false; // 정상
    }

    // DFS로 음의 사이클 영향 전파
//    private static void dfs(int now) {
//        if (visited[now]) return;
//        visited[now] = true;
//
//        for (int next : adjList.get(now)) {
//            dfs(next);
//        }
//    }


}