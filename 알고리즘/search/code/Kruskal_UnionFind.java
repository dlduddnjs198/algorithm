import java.io.*;
import java.util.*;

// 시간복잡도
// 간선 정렬: O(E log E) + union & find: 최대 O(E log V) ~ O(EV) = O(E log E)
public class Main {

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        // 가중치 정렬(오름차순)
        public int compareTo(Edge edge) {
            return Integer.compare(this.weight, edge.weight);
        }
    }

    static int[] parent;

    public static void main(String[] args) throws Exception {
                /*
         예시 입력:
         6 9
         1 2 3
         1 3 5
         2 3 1
         2 4 4
         3 4 6
         3 5 8
         4 5 7
         4 6 2
         5 6 9
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        System.out.print("정점 수와 간선 수 입력: ");
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        Edge[] edges = new Edge[E];

        System.out.println("각 간선 정보 입력 (from to weight):");
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        // 1. 간선 가중치 기준으로 정렬(정렬했으니 그리디로 인해서 반복문 돌려서 그때그때 찾으면 된다.)
        Arrays.sort(edges);

        // 2. 유니온 파인드 초기화
        parent = new int[V + 1]; // 정점 번호가 1부터 시작한다고 가정
        for(int i=1;i<=V;i++) parent[i]=i;

        // 3. MST 구성
        int mstCost = 0; // 지금까지 선택한 간선들의 총 비용을 누적하는 변수
        int edgeCount = 0; // 현재까지 MST에 포함된 간선 개수를 세는 변수

        for(Edge e : edges){
            if(union(e.from, e.to)){
                mstCost += e.weight;
                edgeCount++;
                System.out.println("선택된 간선: " + e.from + " - " + e.to + " (" + e.weight + ")");
                if(edgeCount == V - 1) break; // MST 완성
            }
        }
    }

    // union 연산 (두 집합 합치기)
    private static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false; // 이미 같은 집합 (사이클 발생)
        parent[rootA] = rootB;
        return true;
    }

    // find 연산 (부모 찾기)
    private static int find(int a){
        if(parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }
}
