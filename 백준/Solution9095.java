import java.io.*;
import java.util.*;

// 9095 1, 2, 3 더하기
public class Main {
    public static void main(String[] args) throws Exception {
        // 1. 입력 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 2. 테스트 케이스 개수 입력
        int T = Integer.parseInt(br.readLine());

        // 3. dp[i] = 정수 i를 1, 2, 3의 합으로 나타내는 경우의 수
        int[] dp = new int[12]; // 최대 11까지 문제에서 주어짐. dp[0] ~ dp[11]까지 필요

        // 4. 초기값 설정 (base cases)
        dp[1] = 1; // 1 → (1)
        dp[2] = 2; // 2 → (1+1), (2)
        dp[3] = 4; // 3 → (1+1+1), (1+2), (2+1), (3)

        // 5. 점화식을 이용한 Bottom-Up DP 계산
        for (int i = 4; i < 12; i++) {
            // 마지막에 1, 2, 3을 더한 경우로 나누어 계산
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        // 6. 각 테스트 케이스에 대해 결과 출력
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine()); // 입력값 n
            System.out.println(dp[n]); // 미리 계산된 dp[n] 출력
        }
    }
}
// DP(Bottmo up)
// 시간복잡도
// 전처리: O(10)
// 각 테스트 케이스 출력: O(1)
// 총 시간복잡도: O(T) (T ≤ 1000이라 해도 매우 빠름)