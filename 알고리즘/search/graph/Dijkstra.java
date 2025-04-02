import java.io.*;
import java.util.*;

// 시간 복잡도는 O(E log V)
public class Main {

    static class Edge {
        int to, weight;

    }

    static class Node implements Comparable<Node> {
        int to, weight; // 도착 정점, 가중치

        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node n){
            return Integer.compare(this.weight, n.weight);
        }
    }

    static List<List<Node>> adjList;
    static int[] dist;
    static int E, V;

    public static void main(String[] args) throws Exception {
        // 입력 속도를 높이기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 수 V, 간선 수 E, 시작 정점 start
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        // 인접 리스트 그래프 초기화 (1번 정점부터 시작)
        adjList = new ArrayList<>();
        for(int i=0;i<=V;i++) adjList.add(new ArrayList<>());

        // 간선 입력 (u → v, 가중치 w)
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList.get(from).add(new Node(to, weight)); // 방향그래프면 하나만 하면 되겠지
            adjList.get(to).add(new Node(from, weight)); // 방향그래프면 하나만 하면 되겠지
        }

        // 다익스트라 실행
        dijkstra(1);
    }

    private static void dijkstra(int start){
        int[] dist = new int[V+1]; // 최단 거리 배열

        Arrays.fill(dist, Integer.MAX_VALUE); // 초기값은 무한으로 초기화
        dist[start] = 0; // 시작정점(자기자신)은 0으로 초기화

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int now = cur.to;

            // ⭐ 이미 더 짧은 거리로 처리된 정점이면 무시 (중복 제거 핵심!)
            if (dist[now] < cur.weight) continue;

            // 현재 정점과 연결된 모든 인접 정점 확인
            for(Node node : adjList.get(now)){
                int next = node.to;
                int newCost = dist[now] + node.weight;

                // 더 짧은 경로가 발견되면 갱신
                if(newCost < dist[next]){
                    dist[next] = newCost;
                    pq.offer(new Node(next, newCost)); // 새로운 거리로 큐에 삽입 (O(log V))
                }
            }

        }
    }

}
