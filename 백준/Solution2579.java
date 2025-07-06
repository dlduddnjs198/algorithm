import java.io.*;
import java.util.*;

// 2579 계단 오르기
public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 계단 수

        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N];

        // 초기값 설정
        if (N >= 1) dp[0] = score[0];
        if (N >= 2) dp[1] = score[0] + score[1];
        if (N >= 3) dp[2] = Math.max(score[0] + score[2], score[1] + score[2]);

        // 점화식 적용
        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(
                    dp[i - 2] + score[i],
                    dp[i - 3] + score[i - 1] + score[i]
            );
        }

        // 반드시 마지막 계단은 밟아야 함
        System.out.println(dp[N - 1]);
    }
}
// DP
// 시간복잡도
// O(N) (N ≤ 300이므로 매우 빠름)