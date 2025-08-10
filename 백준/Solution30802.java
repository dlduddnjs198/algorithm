import java.io.*;
import java.util.*;

// 30802 웰컴 키트
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] tSize = new int[6]; // 각 티셔츠 사이즈별 참가자 신청 수
        int N = Integer.parseInt(st.nextToken()); // 참가자 수
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<6;i++){
            tSize[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()); // 티셔츠 묶음 수
        int P = Integer.parseInt(st.nextToken()); // 펜 묶음 수

        int tCnt = 0;
        int pCnt = 0;
        int p = 0;
        for(int i=0;i<6;i++){
            tCnt += tSize[i]%T==0 ? tSize[i]/T : tSize[i]/T+1;
        }

        pCnt = N/P;
        p = N%P;

        bw.write(tCnt + "\n" + pCnt + " " + p);
        bw.flush();
        bw.close();
        br.close();
    }
}
