import java.io.*;
import java.util.*;

// 2178 미로 탐색
public class Main {

    static int N, M;
    static int[][] maze;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        visited = new boolean[N][M];

        // 미로 입력
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cx = now[0];
            int cy = now[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                // 범위 밖이거나 벽이거나 이미 방문한 곳이면 패스
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (maze[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                maze[nx][ny] = maze[cx][cy] + 1; // 이전 거리 +1
                q.offer(new int[]{nx, ny});
            }
        }

        return maze[N-1][M-1]; // 도착 지점에 저장된 거리 반환
    }
}
// BFS
// 큐에 각 칸을 최대 한 번만 넣음 → O(N × M)
// 방향 4개 순회 → 상수 시간 연산