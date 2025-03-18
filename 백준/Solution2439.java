import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 별 찍기 - 2
public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            StringBuilder sb = new StringBuilder();
            for (int j = N-i-1; j > 0; j--) {
                sb.append(" ");
            }
            for (int j = 0; j <= i; j++) {
                sb.append("*");
            }
            System.out.println(sb.toString());
        }
    }

}
