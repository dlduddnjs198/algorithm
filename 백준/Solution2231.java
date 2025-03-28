import java.io.*;
import java.util.*;

//분해합
public class Main{

    static int answer;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        answer=0;
        // 이 부분 중요 -> 브루트 포스지만 최대한 줄이는 부분
        int min = Math.max(1, N - (String.valueOf(N).length() * 9)); // M의 최소 탐색 범위 설정

        for(int i=min;i<N;i++){
            if(calc(i)) break;
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean calc(int n){
        int result = n;
        String s = String.valueOf(n);
        for(int i=0;i<s.length();i++){
            result += s.charAt(i)-'0';
        }
        if(result==N) {
            answer = n;
            return true;
        }
        return false;
    }
}