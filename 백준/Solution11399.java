import java.io.*;
import java.util.*;

// 11399 ATM
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int answer = 0;

        int[] people = new int[N]; // 각 사람 인출시간
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            people[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(people);

        int num = 0;

        for(int i=0;i<N;i++){
            num = num + people[i];
            answer += num;
//            System.out.println(num);
        }

        System.out.println(answer);
    }

}

// 그리디고만