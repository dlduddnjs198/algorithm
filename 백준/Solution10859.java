import java.io.*;
import java.util.*;

//알파벳 찾기
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int[] alpha = new int[26];
        Arrays.fill(alpha, -1);

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(alpha[c-'a']!=-1) continue;
            alpha[c-'a']=i;
        }

        for(int i=0;i<26;i++){
            bw.write(alpha[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}