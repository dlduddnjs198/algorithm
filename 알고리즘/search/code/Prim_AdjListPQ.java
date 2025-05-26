import java.io.*;
import java.util.*;

// ✅ 시간복잡도: O(E log V)
// - 우선순위 큐에서 정점 선택: log V
// - 인접 리스트로 연결된 간선 순회: 총 E개
// → 실전에서 가장 빠르고 메모리 효율도 좋음
public class Main {

    // ✅ 간선 정보를 담는 클래스 (도착 정점, 가중치)
    // 우선순위 큐에 넣기 위해 Comparable 구현
    static class Node implements Comparable<Node> {
        int to, weight;

        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Node n){
            return Integer.compare(this.weight, n.weight); // 가중치 기준 오름차순
        }
    }

    // ✅ 인접 리스트: 정점마다 연결된 간선 리스트를 저장
    static List<List<Node>> adjList;

    public static void main(String[] args) throws Exception {
        /*
         ✅ 예시 입력:
         5 7
         1 2 3
         1 3 2
         2 3 1
         2 4 4
         3 4 2
         3 5 5
         4 5 1
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        System.out.print("정점 수와 간선 수 입력: ");
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점 수
        int E = Integer.parseInt(st.nextToken()); // 간선 수

        // ✅ 인접 리스트 초기화 (1번 정점부터 사용)
        adjList = new ArrayList<>();
        for(int i = 0; i <= V; i++) adjList.add(new ArrayList<>());

        System.out.println("간선 입력 (from to weight):");
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향 간선으로 저장
            adjList.get(from).add(new Node(to, weight));
            adjList.get(to).add(new Node(from, weight));
        }

        // ✅ Prim 알고리즘용 자료구조
        boolean[] visited = new boolean[V + 1];     // 정점 방문 여부
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 최소 간선 선택용 우선순위 큐

        int result = 0;     // MST 총 비용
        int cnt = 0;        // 현재까지 선택된 정점 수

        // ✅ 1번 정점부터 시작 (가중치 0, 시작점)
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll(); // 가장 가중치가 낮은 간선 꺼냄

            if (visited[node.to]) continue; // 이미 선택된 정점이면 무시

            visited[node.to] = true;           // 정점을 MST에 포함
            result += node.weight;             // 해당 간선의 가중치 누적
            cnt++;
            System.out.println("선택된 정점: " + node.to + ", 비용: " + node.weight);

            if (cnt == V) break; // 모든 정점이 MST에 포함되면 종료

            // ✅ 현재 정점에서 갈 수 있는 모든 간선 탐색
            for (Node next : adjList.get(node.to)) {
                if (!visited[next.to]) {
                    // 아직 포함되지 않은 정점이라면 우선순위 큐에 추가
                    pq.offer(new Node(next.to, next.weight));
                }
            }
        }

        System.out.println("✅ MST 전체 비용: " + result);
    }
}
