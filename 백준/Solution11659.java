import java.io.*;
import java.util.*;

// 11659 구간 합 구하기 4
public class Main {
    public static void main(String[] args) throws Exception {
        // 입력을 빠르게 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄: N (숫자 개수), M (쿼리 개수)
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); // 출력 버퍼 (성능 향상)

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 쿼리 수

        int[] arr = new int[N + 1]; // 원본 숫자 배열 (1-indexed)
        int[] dp = new int[N + 1];  // 누적합 배열 (prefix sum), dp[i] = arr[1] + ... + arr[i]

        // 두 번째 줄: N개의 수 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i - 1] + arr[i]; // 누적합 계산: dp[i] = dp[i-1] + arr[i]
        }

        // 다음 M줄: i ~ j 구간 합 쿼리 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 인덱스
            int b = Integer.parseInt(st.nextToken()); // 끝 인덱스

            // 구간 합 = dp[b] - dp[a - 1]
            sb.append(dp[b] - dp[a - 1]).append("\n");
        }

        // 결과 한 번에 출력 (성능 최적화)
        System.out.println(sb.toString());
    }
}
// 누적합
// 시간복잡도
// 누적합 계산	O(N)
// M개의 쿼리 처리	O(M)
// 총합	O(N + M) → 매우 빠름 ✅