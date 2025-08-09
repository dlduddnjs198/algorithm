import java.io.*;
import java.util.*;

// 28702 FizzBuzz
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();
        String third = br.readLine();

        int num = 0;

        // 첫 번째 값이 숫자인 경우
        if (isNumber(first)) {
            num = Integer.parseInt(first);
        }
        // 두 번째 값이 숫자인 경우
        else if (isNumber(second)) {
            num = Integer.parseInt(second) - 1;
        }
        // 세 번째 값이 숫자인 경우
        else if (isNumber(third)) {
            num = Integer.parseInt(third) - 2;
        }

        // 다음 값 구하기
        int nextNum = num + 3;
        String result = getFizzBuzz(nextNum);

        System.out.println(result);
    }

    // 숫자인지 판별하는 함수
    private static boolean isNumber(String s) {
        return s.chars().allMatch(Character::isDigit);
    }

    // FizzBuzz 규칙에 따라 문자열 변환
    private static String getFizzBuzz(int n) {
        if (n % 3 == 0 && n % 5 == 0) return "FizzBuzz";
        if (n % 3 == 0) return "Fizz";
        if (n % 5 == 0) return "Buzz";
        return String.valueOf(n);
    }
}