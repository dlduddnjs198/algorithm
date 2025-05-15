import java.io.*;
import java.util.*;

//수 정렬하기 3
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = new int[10001];

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            arr[Integer.parseInt(br.readLine())]++;
        }

        for(int i=1;i<=10000;i++){
            if(arr[i]!=0){
                for(int j=0;j<arr[i];j++){
                    bw.write(i+"\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
