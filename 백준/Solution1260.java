import java.io.*;
import java.util.*;

// 1260 DFS와 BFS
public class Main {

    static List<List<Integer>> adjList;
    static int N, M, V;
    static StringBuilder sb;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        adjList = new ArrayList<>();

        for(int i=0;i<=N;i++){
            adjList.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        for(int i=1;i<=N;i++){
            Collections.sort(adjList.get(i));
        }

        visited[V]=true;
        dfs(V);
        sb.append("\n");
        visited = new boolean[N+1];
        bfs(V);

        System.out.println(sb.toString());
    }

    static void dfs(int start) {
        sb.append(start).append(" ");
        for(int num : adjList.get(start)){
            if(visited[num]) continue;
            visited[num] = true;
            dfs(num);
        }
    }

    static void bfs(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(start);
        visited[start]=true;

        while(!dq.isEmpty()){
            int num = dq.pollFirst();
            sb.append(num).append(" ");
            for(int num2 : adjList.get(num)){
                if(visited[num2]) continue;
                visited[num2]=true;
                dq.offerLast(num2);
            }
        }
    }
}
// DFS, BFS
// 정점 수 N ≤ 1,000, 간선 수 M ≤ 10,000
// DFS, BFS 탐색 시간복잡도: O(N + M)
// 정렬 비용: O(M log D) (D는 각 정점의 평균 차수, 평균적으로 작음)