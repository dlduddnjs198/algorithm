// PrimeFactorization1.java
import java.io.*;

public class PrimeFactorization1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("정수 입력: ");
        int n = Integer.parseInt(br.readLine());

        System.out.print("소인수분해 결과: ");
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }
        if (n > 1) System.out.print(n); // 마지막 남은 소수 (자기 자신)
    }
}