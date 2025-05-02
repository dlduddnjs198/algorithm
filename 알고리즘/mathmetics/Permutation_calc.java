import java.io.*;
import java.util.*;

// 순열(개수구하기)
// 시간복잡도 : O(r) -> r번의 반복을 돌기 때문
public class Main {

    static int n, r;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        bw.write(permutationCalc(n, r)+"");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int permutationCalc(int n, int r){
        int result = 1;
        for(int i=n;i>n-r;i++){
            result *= i;
        }
        return result;
    }
}