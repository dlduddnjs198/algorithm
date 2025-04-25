import java.io.*;
import java.util.*;

//OX퀴즈
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int prev;
        int answer;

        for(int i=0;i<T;i++){
            String s = br.readLine();
            answer = 0;
            prev = 0;
            for(int j=0;j<s.length();j++){
                char c = s.charAt(j);
                if(c=='O') answer += ++prev;
                else prev=0;
            }
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}