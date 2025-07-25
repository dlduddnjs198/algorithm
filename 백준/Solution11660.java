import java.io.*;
import java.util.*;

// 11660 구간 합 구하기 5
public class Main {
    public static void main(String[] args) throws Exception {
        // 1. 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 2. 첫 줄: 표의 크기 N, 질의 수 M
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); // 출력 버퍼

        int N = Integer.parseInt(st.nextToken()); // N×N 배열
        int M = Integer.parseInt(st.nextToken()); // 질의 개수

        // 3. 원본 배열과 누적합 배열 선언 (1-indexed)
        int[][] arr = new int[N + 1][N + 1]; // 입력값 저장용
        int[][] dp = new int[N + 1][N + 1];  // 누적합 저장용

        // 4. 배열 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 5. 누적합 초기값 설정 (첫 행/열은 따로 처리)
        dp[1][1] = arr[1][1];
        for (int i = 2; i <= N; i++) {
            dp[i][1] = dp[i - 1][1] + arr[i][1]; // 첫 열 누적합
            dp[1][i] = dp[1][i - 1] + arr[1][i]; // 첫 행 누적합
        }

        // 6. 나머지 누적합 계산 (2차원 prefix sum)
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= N; j++) {
                dp[i][j] = dp[i - 1][j]        // 위쪽 영역 합
                        + dp[i][j - 1]        // 왼쪽 영역 합
                        - dp[i - 1][j - 1]    // 중복된 왼쪽 위 영역 제거
                        + arr[i][j];          // 현재 셀 값 더함
            }
        }

        // 7. M개의 질의 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 질의 좌표 (x1, y1) ~ (x2, y2)
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 누적합 공식을 이용한 구간 합 계산
            int sum = dp[x2][y2]
                    - dp[x1 - 1][y2]
                    - dp[x2][y1 - 1]
                    + dp[x1 - 1][y1 - 1];

            sb.append(sum).append("\n");
        }

        // 8. 출력
        System.out.println(sb.toString());
    }
}
// DP(누적합)
// 시간복잡도
// 누적합 배열 생성	O(N²)
// 쿼리 처리 (M개)	O(M)
// 전체	O(N² + M) → ✅ 매우 빠름