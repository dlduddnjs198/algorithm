import java.util.*;

/**
 * 📌 KMP(Knuth-Morris-Pratt) 문자열 검색 알고리즘
 *
 * ✅ 주요 개념:
 * - '텍스트'에서 '패턴'을 빠르게 찾기 위한 알고리즘
 * - 브루트포스 방식처럼 매번 처음부터 비교하지 않고, '이전에 일치한 정보를 활용'하여 점프함
 * - 이를 위해 '부분 일치 테이블(pi 배열)'을 미리 만들어 사용
 *
 * ✅ 핵심 동작:
 * 1. 먼저 패턴에서 접두사 == 접미사인 부분 길이를 pi 배열로 구한다
 *    예: pattern = "ababab" → pi = [0, 0, 1, 2, 3, 4]
 * 2. 텍스트에서 비교 중 틀리면, 패턴을 처음부터 다시 비교하지 않고
 *    pi 배열을 참고해 '패턴 인덱스 j'만 점프
 *    → i는 절대 되돌아가지 않음 (i는 항상 한 칸씩 증가)
 *
 * ✅ 시간복잡도:
 * - pi 배열 생성: O(m) (m = 패턴 길이)
 * - 텍스트 비교: O(n) (n = 텍스트 길이)
 * → 전체 시간복잡도: O(n + m)
 *
 * ✅ 예시:
 * text    = "abababxababab"
 * pattern = "ababab"
 * → 결과: [0, 7]
 */
public class Main {

    public static void main(String[] args) {
        String text = "ababababxababab";
        String pattern = "ababab";

        List<Integer> positions = kmpSearch(text, pattern);
        System.out.println("패턴이 등장하는 위치: " + positions);
    }

    /**
     * 주어진 텍스트(text)에서 패턴(pattern)이 등장하는 모든 시작 인덱스를 반환합니다.
     * 예: text = "ababcababcac", pattern = "ababc"
     * → [0, 5] 반환
     */
    private static List<Integer> kmpSearch(String text, String pattern){
        List<Integer> result = new ArrayList<>();
        int[] pi = buildPi(pattern); // 부분 일치 테이블 만들기(전처리)

        int j = 0; // 현재 패턴 매칭 위치

        // i는 반복문대로 하나하나 올라가고(임의로 조정되지 않는다.), 조건에 따라 패턴 인덱스 j만 pi배열에 따라 점프한다.
        for(int i=0;i<text.length();i++){
            // 🔁 일치가 끊기면 pi 배열을 사용해 점프
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1]; // 이전 접두사/접미사 길이로 점프
            }

            // 문자 일치
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;

                // 패턴 전체가 매칭된 경우
                if (j == pattern.length()) {
                    result.add(i - pattern.length() + 1); // 시작 위치 저장
                    j = pi[j - 1]; // 다음 매칭 위해 jump
                }
            }
        }


    }

    /**
     * 부분 일치 테이블(pi 배열)을 생성합니다.
     * pi[i]는 P[0..i]의 접두사 == 접미사 중 가장 긴 길이를 나타냅니다.
     */
    private static int[] buildPi(String pattern){
        int m = pattern.length();
        int[] pi = new int[m];
        int j = 0; // 현재까지 일치한 접두사/접미사의 길이
        for (int i = 1; i < m; i++) {
            // 패턴의 앞에서부터 j개의 문자가 일치해오고 있다고 가정

            // 📌 일치가 끊기면, 현재 j까지의 일치를 포기하고
            // 더 짧은 접두사/접미사로 점프해서 이어갈 수 있는지 확인
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1]; // 접두사 길이 줄이기
            }

            // 문자 일치
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                pi[i] = j; // 지금까지 일치한 최대 접두사/접미사 길이 저장
            }
        }

        return pi;
    }
}