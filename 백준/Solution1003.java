import java.io.*;
import java.util.*;

// 1003 피보나치 함수
public class Main {

    static int oneCnt=0;
    static int zeroCnt=0;
    static int[] zeroDp;
    static int[] oneDp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        zeroDp = new int[41];
        oneDp = new int[41];

        Arrays.fill(zeroDp, -1);

        zeroDp[0]=1;
        oneDp[0]=0;
        zeroDp[1]=0;
        oneDp[1]=1;

        for(int t=0;t<T;t++){
            oneCnt=0;
            zeroCnt=0;
            int N = Integer.parseInt(br.readLine());

            countFibo(N);

            System.out.println(zeroDp[N] + " " + oneDp[N]);
        }



    }

    private static void countFibo(int N){
        if(zeroDp[N]!=-1 || N==0 || N==1) return;

        countFibo(N-1);
        countFibo(N-2);

        zeroDp[N]=zeroDp[N-1] + zeroDp[N-2];
        oneDp[N]=oneDp[N-1] + oneDp[N-2];
    }

}
// DP
// 시간복잡도
// 전처리: O(N) = O(40)
// 각 테스트케이스 출력: O(1)
// 총 시간복잡도: O(T + 40)