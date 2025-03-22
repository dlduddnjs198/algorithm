import java.io.*;
import java.util.*;

//단어 정렬
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        HashSet<String> set = new HashSet<>();

        for(int i=0;i<N;i++){
            set.add(br.readLine());
        }
        String[] arr = set.toArray(new String[set.size()]);

        Arrays.sort(arr, (a, b) -> {
            if(a.length()==b.length()) return a.compareTo(b);
            return a.length() - b.length();
        });

        for(int i=0;i<arr.length;i++){
            bw.write(arr[i]+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}