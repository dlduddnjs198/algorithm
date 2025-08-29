import java.io.*;
import java.util.*;

// 팩토리얼
public class Main {

    static long[] dp = new long[101];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        bw.write(factorial1(n) + "");
        bw.write(factorial2(n) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    // 반복 팩토리얼 (O(n))
    private static long factorial1(int n){
        long result = 1L;
        for(int i=1;i<=n;i++){
            result *= i;
        }
        return result;
    }

    // 재귀 팩토리얼 (O(n))
    private static long factorial2(int n){
        if(n <= 1) return 1;
        return n * factorial2(n-1);
    }

    // DP(메모이제이션) (O(n))
    private static long factorial3(int n){
        if(n <= 1) return 1;
        if(dp[n] != 0) return dp[n];
        return dp[n] = n * factorial3(n-1);
    }
}