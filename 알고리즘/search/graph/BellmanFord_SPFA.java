import java.util.*;
import java.io.*;

// SPFA (Shortest Path Faster Algorithm)
// → 벨만-포드를 큐 기반으로 최적화한 최단 경로 알고리즘
// → 음수 간선 허용, 음의 사이클 탐지 가능
// 시간복잡도: 평균 O(E), 최악 O(VE)

public class Main {

    static class Node{
        int to;
        int cost;
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M; // 정점 수, 간선 수
    static List<List<Node>> adjList; // 인접 리스트(각 정점마다 연결된 노드들)
    static long[] dist; // 최단 거리 저장 배열
    static boolean[] isQueue; // 현재 큐에 있는지 여부(중복 삽입 방지)
    static int[] count; // 정점이 큐에 들어간 횟수
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 정점 수(N), 간선 수(M) 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2. 인접 리스트 초기화
        adjList = new ArrayList<>();
        for(int i=0;i<=N;i++) adjList.add(new ArrayList<>());

        // 3. 간선 입력
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList.get(from).add(new Node(to, cost));
        }

        // 4. 시작 정점 입력
        int start = Integer.parseInt(br.readLine());

        // 5. 거리, 방문 여부, 삽입 횟수 초기화
        dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        isQueue = new boolean[N+1];
        count = new int[N+1];

        // 6. SPFA 실행
        boolean hasNegativeCycle = spfa(start);

        // 7. 결과 출력
        if (hasNegativeCycle) {
            System.out.println("음의 사이클이 존재합니다.");
        } else {
            System.out.println("최단 거리 결과");
            for (int i = 1; i <= N; i++) {
                System.out.println("Node " + i + ": " + (dist[i] == INF ? "INF" : dist[i]));
            }
        }
    }

    // SPFA 알고리즘(Deque)
    private static boolean spfa(int start){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(start);
        isQueue[start] = true;

//        해당 정점이 큐에 들어간 횟수를 1 증가시킴
//        이 횟수가 N 이상이면 → 음의 사이클 의심!
        count[start]++;

        while(!dq.isEmpty()){
            int now = dq.pollFirst();
            isQueue[now] = false;

            // 현재 정점에서 갈 수 있는 모든 인접 정점 확인
            for(Node next : adjList.get(now)){
                int to = next.to;
                int cost = next.cost;

                // 더 짧은 경로가 발견되면 거리 갱신
                if(dist[now] != INF && dist[now] + cost < dist[to]){
                    dist[to] = dist[now] + cost;

                    // 이미 큐에 없다면 큐에 추가
                    if(!isQueue[to]){
                        if (dist[to] < dist[now]) {
                            dq.offerFirst(to); // 더 "작은 거리"면 앞에 넣음 (우선 처리)
                        } else {
                            dq.offerLast(to); // 아니면 그냥 뒤에 넣음
                        }
                        isQueue[to] = true;
                        count[to]++;

                        // 해당 정점이 N번 이상 큐에 들어갔다면 음의 사이클 존재 가능성 있음
                        // 어떤 정점이 V번 이상 큐에 들어간다 = 무한으로 줄어들고 있다 → 음의 사이클
                        if(count[to] >= N){
                            return true; // 음의 사이클 있음
                        }
                    }
                }
            }
        }
        return false;
    }
}