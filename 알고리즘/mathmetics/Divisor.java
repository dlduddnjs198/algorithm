import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("정수 입력: ");
        int inputNum = Integer.parseInt(br.readLine());

        System.out.println("\n🔹 방법 1: O(N)");
        System.out.println(getDivisorsV1(inputNum));

        System.out.println("\n🔹 방법 2: O(N/2)");
        System.out.println(getDivisorsV2(inputNum));

        System.out.println("\n🔹 방법 3: O(√N)");
        System.out.println(getDivisorsV3(inputNum));
    }


    // ✅ 방법 1: 1부터 N까지 전부 나눠보는 방식 (O(N))
    private static List<Integer> getDivisorsV1(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) result.add(i); // 나누어 떨어지면 약수
        }
        return result;
    }

    // ✅ 방법 2: 1부터 N/2까지만 탐색 (O(N/2))
    private static List<Integer> getDivisorsV2(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) result.add(i); // 절반까지만 검사해도 모든 약수 가능
        }
        result.add(n); // 자기 자신은 항상 약수
        return result;
    }

    // ✅ 방법 3: 제곱근까지만 탐색, 쌍으로 약수 추가 (O(√N))
    private static List<Integer> getDivisorsV3(int n) {
        Set<Integer> result = new TreeSet<>(); // 정렬된 출력 원할 때 Set 사용
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                result.add(i);           // i는 약수
                result.add(n / i);       // n / i 도 약수
            }
        }
        return new ArrayList<>(result);
    }
}
