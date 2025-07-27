import java.io.*;
import java.util.*;

// 11723 집합
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine()); // 연산의 수
        StringBuilder sb = new StringBuilder();
        int all = (1 << 20) - 1;
        int set = 0;

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int x;

            switch(cmd){
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    set = set | (1 << (x - 1));
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    set = set & ~(1 << (x - 1));
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    if((set & (1 << (x - 1))) != 0) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    set = set ^ (1 << (x - 1));
                    break;
                case "all":
                    set = all;
                    break;
                case "empty":
                    set = 0;
                    break;
            }
        }

        System.out.println(sb);
    }

}

// set || bitmask로 풀 수 있음
// 막 그렇게 많이 차이나진 않네?