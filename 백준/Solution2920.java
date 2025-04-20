import java.io.*;
import java.util.*;

//음계
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int prev = start;
        String answer;
        switch(start){
            case 1:
                answer = "ascending";
                break;
            case 8:
                answer = "descending";
                break;
            default:
                answer = "mixed";
        }

        if(!answer.equals("mixed")){
            for (int i = 1; i < 8; i++) {
                int num = Integer.parseInt(st.nextToken());
                if(answer.equals("ascending") && prev+1==num){
                    prev=num;
                    continue;
                }
                if(answer.equals("descending") && prev-1==num){
                    prev=num;
                    continue;
                }
                answer="mixed";
                break;
            }
        }

        bw.write(answer);
        bw.flush();
        bw.close();
        br.close();
    }
}