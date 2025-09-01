import java.io.*;
import java.util.*;

public class Main {

    // ✅ 유클리드 호제법을 이용한 GCD (최대공약수) 함수
    // GCD(a, b) = GCD(b, a % b) -> abbabab(아빠밥)
    // b가 0이 될 때까지 나머지를 재귀적으로 계산
    static int gcd(int a, int b) {
        if (b == 0) return a; // 종료 조건: b가 0이면 a가 최대공약수
        return gcd(b, a % b); // 재귀 호출
    }

    // ✅ LCM (최소공배수) 함수
    // 최소공배수는 두 수의 곱을 최대공약수로 나눈 값
    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
        // a와 b의 곱을 GCD로 나누면 최소공배수가 됨
        // 단, a * b는 오버플로우 위험이 있으니 실제 문제에선 long 타입 쓰는 게 안전
    }

    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("두 수 입력 (a b): ");
        StringTokenizer st = new StringTokenizer(br.readLine()); // 입력을 공백 기준으로 나눔

        int a = Integer.parseInt(st.nextToken()); // 첫 번째 수
        int b = Integer.parseInt(st.nextToken()); // 두 번째 수

        // 최대공약수 출력
        System.out.println("✅ GCD : " + gcd(a, b));

        // 최소공배수 출력
        System.out.println("✅ LCM : " + lcm(a, b));
    }
}
