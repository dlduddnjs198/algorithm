import java.io.*;
import java.util.*;

// 2606 바이러스
public class Main {
    public static void main(String[] args) throws Exception {
        // 1. 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 컴퓨터 수 (정점 수)
        int M = Integer.parseInt(br.readLine()); // 직접 연결된 컴퓨터 쌍 수 (간선 수)
        StringTokenizer st;

        int answer = 0; // 감염된 컴퓨터 수 (1번 컴퓨터 제외)

        // 2. 인접 리스트 초기화 (1번부터 N번까지 사용하기 위해 N+1개 생성)
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        // 3. 간선 정보 입력 (양방향 연결)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 컴퓨터 a
            int b = Integer.parseInt(st.nextToken()); // 컴퓨터 b
            adjList.get(a).add(b);
            adjList.get(b).add(a); // 양방향 연결
        }

        // 4. 방문 여부 배열 선언
        boolean[] visited = new boolean[N + 1];

        // 5. BFS 탐색을 위한 큐 선언 (Deque 사용)
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(1); // 시작 컴퓨터: 1번

        // 6. BFS 탐색 시작
        while (!dq.isEmpty()) {
            int num = dq.pollFirst(); // 큐에서 현재 컴퓨터 꺼냄

            if (visited[num]) continue; // 이미 방문한 컴퓨터는 스킵

            visited[num] = true; // 방문 표시
            answer++; // 감염된 컴퓨터 수 증가

            // 현재 컴퓨터와 연결된 컴퓨터들 중 방문하지 않은 컴퓨터 큐에 추가
            for (int v : adjList.get(num)) {
                if (!visited[v]) {
                    dq.offerLast(v);
                }
            }
        }

        // 7. 결과 출력 (1번 컴퓨터는 제외해야 하므로 -1)
        System.out.println(answer - 1);
    }
}
// DP
// 시간복잡도
// 그래프 탐색: O(N + E)
// N: 컴퓨터 수 (≤ 100)
// E: 간선 수 (≤ 100 * 99 / 2)