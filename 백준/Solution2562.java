import java.io.*;
import java.util.*;

//최댓값
public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int order = -1;
        int num = -1;

        for(int i=0;i<9;i++){
            int n = Integer.parseInt(br.readLine());
            if(num < n){
                num = n;
                order = i+1;
            }
        }

        bw.write(num + "\n" + order);
        bw.flush();
        bw.close();
        br.close();
    }
}