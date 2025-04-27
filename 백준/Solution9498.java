import java.io.*;
import java.util.*;

// 시험 성적
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String answer;

        if(n >= 90){
            answer = "A";
        }else if(n >= 80){
            answer = "B";
        }else if(n >= 70){
            answer = "C";
        }else if(n >= 60){
            answer = "D";
        }else{
            answer = "F";
        }

        bw.write(answer);
        bw.flush();
        bw.close();
        br.close();
    }
}
