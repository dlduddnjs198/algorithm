import java.io.*;
import java.util.*;

// ✅ 소인수분해 - 방식 2: 에라토스테네스의 체로 소수 리스트 먼저 만든 뒤 분해
public class PrimeFactorization2 {

    // 🔹 소수 리스트 생성 함수 (에라토스테네스의 체)
    // ▸ 2부터 sqrt(n)까지의 소수들을 미리 구해 놓는다
    // ▸ 시간 복잡도: O(N log log N)
    static List<Integer> getPrimes(int max) {
        boolean[] isNotPrime = new boolean[max + 1]; // 소수가 아닌 수를 true로 표시
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= max; i++) {
            if (!isNotPrime[i]) {
                primes.add(i); // 소수이므로 리스트에 추가

                // i의 배수들은 전부 소수가 아님
                for (int j = i * 2; j <= max; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        return primes;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("정수 입력: ");
        int n = Integer.parseInt(br.readLine()); // 예: 60

        // 🔹 소인수는 √n 이하 중 소수이기만 하면 충분
        List<Integer> primes = getPrimes((int) Math.sqrt(n));

        System.out.print("소인수분해 결과: ");

        // 🔸 소수 리스트를 돌면서 나눠 떨어지는 동안 계속 나눔
        for (int p : primes) {
            while (n % p == 0) {
                System.out.print(p + " "); // 소인수 출력
                n /= p; // 나눈 뒤 갱신
            }
        }

        // 🔸 아직 n > 1이면 그것도 소수 (예: 29 → 마지막에 그대로 남음)
        if (n > 1) {
            System.out.print(n);
        }
    }
}
