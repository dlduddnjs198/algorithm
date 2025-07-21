import java.io.*;
import java.util.*;

// 11658 구간 합 구하기 3
public class Main {

    static int N, M;
    static int[][] arr;    // 원본 배열 (값 변경 시 diff 계산용)
    static long[][] bit;   // 2차원 펜윅 트리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력: N x N 표, M개의 연산
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 배열 크기: 1-based indexing 사용 → N+1
        arr = new int[N+1][N+1];
        bit = new long[N+1][N+1];

        // ✅ 초기 배열 입력 및 펜윅 트리 초기화
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                update(i, j, arr[i][j]);  // 초기값을 펜윅 트리에 반영
            }
        }

        // ✅ M개의 연산 수행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());

            if (w == 0) {
                // (x, y) 위치의 값을 c로 바꾸는 연산
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                int diff = c - arr[x][y];     // 변화량 계산
                update(x, y, diff);           // 변화량만큼 트리 업데이트
                arr[x][y] = c;                // 원본 배열도 갱신

            } else {
                // (x1, y1) ~ (x2, y2) 구간 합 구하기
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                // 2차원 구간합 쿼리 공식 (누적합 사각형 4개 활용)
                long sum = query(x2, y2)
                        - query(x1 - 1, y2)
                        - query(x2, y1 - 1)
                        + query(x1 - 1, y1 - 1);
                sb.append(sum).append("\n");
            }
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    // ✅ 2차원 펜윅 트리 업데이트 함수
    // (x, y) 위치에 val만큼 더함
    private static void update(int x, int y, int val) {
        for (int i = x; i <= N; i += (i & -i)) {
            for (int j = y; j <= N; j += (j & -j)) {
                bit[i][j] += val;
            }
        }
    }

    // ✅ (1,1) ~ (x,y) 구간 누적합을 구하는 함수
    private static long query(int x, int y) {
        long sum = 0;
        for (int i = x; i > 0; i -= (i & -i)) {
            for (int j = y; j > 0; j -= (j & -j)) {
                sum += bit[i][j];
            }
        }
        return sum;
    }
}
// 펜윅 트리(2D)
// 시간복잡도
// update(x, y, c)	O(log N * log N)
// query(x, y)	O(log N * log N)
// 공간복잡도 O(N^2) (bit, arr 배열 포함)
// 전체 시간 O(M log^2 N) (최대 100,000 쿼리까지 충분히 처리 가능)