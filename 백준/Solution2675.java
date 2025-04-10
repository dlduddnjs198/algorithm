import java.io.*;
import java.util.*;

//문자열 반복
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            String S = st.nextToken();
            StringBuilder sb = new StringBuilder();

            for(int j=0;j<S.length();j++){
                for(int k=0;k<R;k++) sb.append(S.charAt(j));
            }

            bw.write(sb + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}