import java.io.*;
import java.util.*;

// 11726 2×n 타일링
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }

        System.out.println(dp[n]);
    }
}
// DP
// 점화식: dp[n] = dp[n-1] + dp[n-2]
// 시간복잡도: O(n)
// 공간복잡도: O(1) (공간 최적화 적용)