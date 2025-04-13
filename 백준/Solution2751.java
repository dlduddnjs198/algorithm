import java.io.*;
import java.util.*;

//수 정렬하기 2
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashSet<Integer> sets = new HashSet<>();
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            sets.add(Integer.parseInt(br.readLine()));
        }

        List<Integer> list = new ArrayList<>(sets);
        Collections.sort(list);

        for(int i=0;i<list.size();i++){
            bw.write(list.get(i)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}