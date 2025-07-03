import java.io.*;
import java.util.*;

// 1764 듣보잡
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람 수
        int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람 수

        Map<String, Boolean> map = new HashMap<>();

        for(int i=0;i<N;i++){
            String str = br.readLine();

            map.put(str, true);
        }

        int num = 0;
        List<String> list = new ArrayList<>();

        for(int i=0;i<M;i++){
            String str = br.readLine();

            if(map.containsKey(str)) {
                list.add(str);
                num++;
            }
        }

        Collections.sort(list);

        System.out.println(num);
        for(String s : list){
            System.out.println(s);
        }
    }

}

// map보다는 set 쓰는게 더 나을듯