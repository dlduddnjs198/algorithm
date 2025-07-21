import java.io.*;
import java.util.*;

// 11407 동전 0
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 동전 종류
        int K = Integer.parseInt(st.nextToken()); // 가치의 합을 K로
        int answer=0;

        int[] money = new int[N];

        for(int i=0;i<N;i++){
            money[i] = Integer.parseInt(br.readLine());
        }

        for(int i=money.length-1;i>=0;i--){
            answer += K/money[i];
            K = K%money[i];
            if(K==0) break;
        }

        System.out.println(answer);
    }

}

// 그리디고만