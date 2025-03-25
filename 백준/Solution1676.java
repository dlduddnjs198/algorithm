import java.io.*;
import java.util.*;

//팩토리얼 0의 개수(수학)
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int zeroCnt = 0;

        // 10의 개수는 결국 거기에 10이 몇번 곱해지는지를 봐야한다.
        // 팩토리얼에서 2는 5에 비해 굉장히 많이 곱해지기 때문에 5의 개수만 세면 되는데 5가 중복으로 되어있는 애들인 중복으로 세어줘야한다.
        // 그래서 5는 5가 한번 곱해진 애들의 수
        // 25는 5가 두번 곱해진 애들의 수 등등 이렇게 된다.
        for (int i = 5; i <= N; i *= 5) {
            zeroCnt += N / i;
        }

        bw.write(zeroCnt + "");
        bw.flush();
        bw.close();
        br.close();
    }
}