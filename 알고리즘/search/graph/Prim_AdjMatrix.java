import java.io.*;
import java.util.*;

// ✅ 시간복잡도:
// 정점 수 V일 때 -> O(V²)
// V <= 1000일때만 사용(그냥 안쓰는게 낫다)
public class Main {
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

        // 인접 행렬 초기화
        int[][] adjMatrix = new int[V+1][V+1];

        System.out.println("간선 입력 (from to weight):");
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjMatrix[from][to] = weight; // 방향이 있으면 값이 다르겠지
            adjMatrix[to][from] = weight;
        }

        // Prim 알고리즘에 필요한 배열들
        boolean[] visited = new boolean[V+1]; // MST 포함여부
        int[] minEdge = new int[V+1]; // 해당 정점에 연결된 최소 간선 비용

        // 초기화: 모든 간선 비용 무한대로 설정
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[1] = 0; // 시작 정점 선택

        int result = 0; // MST 총 비용

        for(int i=1;i<=V;i++){
            int min = Integer.MAX_VALUE; // 현재 단계에서 가장 작은 간선 비용
            int u = -1; // 최소 간선 비용을 가지는 정점 (MST에 새로 추가될 정점)

            // 1. MST에 포함되지 않은 정점들 중에서, 가장 적은 간선 비용(minEdge[v])을 가진 정점 u 선택
            // ** 정점을 선택한다 정점!
            for(int v=1;v<=V;v++){
                if(!visited[v] && minEdge[v] < min){
                    min = minEdge[v];
                    u = v;
                }
            }

            visited[u] = true;            // 정점 u를 MST에 포함시킴
            result += minEdge[u];         // 정점 u까지의 최소 간선 비용을 결과에 누적
            System.out.println("선택된 정점: " + u + ", 비용: " + minEdge[u]);

            // 2. 새로 추가된 정점 u를 통해 연결할 수 있는 다른 정점들에 대해,
            //    그 경로가 기존보다 더 짧다면 minEdge 값을 업데이트
            // ** 그 정점 기준 가장 최소 간선들을 update해나간다!
            for(int v=1;v<=V;v++){
                // 정점 v가 MST에 포함되지 않았고, u에서 v로 가는 간선이 존재하며,
                // u를 통해 v로 가는 비용이 현재 저장된 최소비용보다 작다면 갱신한다!
                if(!visited[v] && adjMatrix[u][v] != 0 && adjMatrix[u][v] < minEdge[v]){
                    minEdge[v] = adjMatrix[u][v]; // 최소 비용 갱신
                }
            }
        }

        System.out.println("✅ MST 전체 비용: " + result);
    }
}
