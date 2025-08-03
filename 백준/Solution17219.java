import java.io.*;
import java.util.*;

// 17219 비밀번호 찾기
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 사이트 수
        int M = Integer.parseInt(st.nextToken()); // 찾으려는 사이트 수

        Map<String, String> map = new HashMap<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String password = st.nextToken();

            map.put(site, password);
        }

        for(int i=0;i<M;i++){
            sb.append(map.get(br.readLine())).append("\n");
        }

        System.out.println(sb.toString());

    }

}

// 입력 처리: O(N)
// 비밀번호 조회: O(1) × M → O(M)
// 전체 시간복잡도: O(N + M)