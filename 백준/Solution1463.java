import java.io.*;
import java.util.*;

// 1463 1로 만들기
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp[i] : i를 1로 만들기 위한 최소 연산 횟수
        int[] dp = new int[N + 1];

        // 모든 값을 일단 최댓값으로 초기화
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 시작점 설정
        dp[N] = 0; // N은 현재 상태이므로 연산 횟수 0

        // N에서 1로 가는 역방향 초기값 설정
        if (N % 3 == 0) dp[N / 3] = 1;       // N → N/3
        if (N % 2 == 0) dp[N / 2] = 1;       // N → N/2
        dp[N - 1] = 1;                       // N → N-1

        // 역방향으로 DP 테이블 갱신
        for (int i = N - 1; i > 0; i--) {
            // i → i/3 가능한 경우
            if (i % 3 == 0) {
                dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
            }
            // i → i/2 가능한 경우
            if (i % 2 == 0) {
                dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
            }
            // i → i-1은 항상 가능
            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
        }

        // 1로 만드는 최소 연산 횟수 출력
        System.out.println(dp[1]);
    }
}
// DP
// 시간복잡도
// O(N)
// 입력값 N이 최대 1,000,000이므로 이 방법으로도 충분히 통과됩니다.