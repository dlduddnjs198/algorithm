import java.io.*;
import java.util.*;

//평균
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        double max=0;
        double[] scores = new double[N];
        double sum=0;

        for(int i=0;i<N;i++){
            scores[i] = Double.parseDouble(st.nextToken());
            max=Math.max(max, scores[i]);
        }

        for(int i=0;i<N;i++){
            scores[i] = scores[i]/max*100;
            sum+=scores[i];
        }

        bw.write(sum/N+"");
        bw.flush();
        bw.close();
        br.close();
    }
}