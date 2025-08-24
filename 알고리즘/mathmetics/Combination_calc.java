import java.io.*;
import java.util.*;

// 조합(개수구하기)
public class Main {

    static int n, r;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        dp = new int[n+1][r+1];

        bw.write(combination1(n, r)+"");
        bw.flush();
        bw.close();
        br.close();
    }

    // 조합 재귀 (O(2^n))
    private static int combination1(int n, int r) {
        if(n == r || r == 0)
            return 1;
        else
            return combination(n - 1, r - 1) + combination(n - 1, r);
    }

    // 조합(DP) (O(n*r))
    private static int combination2(int n, int r) {
        if (r == 0 || n == r) return 1;
        if (dp[n][r] != 0) return dp[n][r];
        return dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
    }
}