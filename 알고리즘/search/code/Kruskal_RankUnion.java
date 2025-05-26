import java.io.*;
import java.util.*;

// ✅ 시간복잡도:
// 간선 정렬: O(E log E)
// 유니온 파인드 (경로 압축 + 랭크 기반): O(α(N)) → 거의 상수
// 전체: O(E log E)
public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge e){
            return Integer.compare(this.weight, e.weight);
        }
    }

    static int[] parent;
    static int[] rank;

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
        int V = Integer.parseInt(st.nextToken()); // 정점 수
        int E = Integer.parseInt(st.nextToken()); // 간선 수

        Edge[] edges = new Edge[E];
        System.out.println("각 간선 정보 입력 (from to weight):");
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        // 1. 간선 정렬
        Arrays.sort(edges);

        // 2. 유니온 파인드 초기화
        parent = new int[V+1];
        rank = new int[V+1];
        for(int i=1;i<=V;i++){
            parent[i]=i;
            rank[i]=0;
        }

        // 3. MST 구성
        int costs = 0;
        int edgeCount = 0;

        for(Edge e : edges) {
            if(union(e.from, e.to)){
                costs += e.weight;
                edgeCount++;
                System.out.println("선택된 간선: " + e.from + " - " + e.to + " (" + e.weight + ")");
                if(edgeCount == V-1) break;
            }
        }

        System.out.println("✅ 최소 신장 트리 비용: " + costs);
    }

    private static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false; // 사이클 발생

        // 랭크가 낮은 쪽을 높은 쪽으로 붙이기
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++; // 높이 같으면 하나 증가
        }

        return true;
    }

    private static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
