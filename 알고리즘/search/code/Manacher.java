import java.util.*;


/**
 * 📘 Manacher 알고리즘 구현 예제
 *
 */
public class Main {

    public static void main(String[] args) {
        String s = "abacdfgdcaba";
        int maxLength = longestPalindromicSubstringLength(s);
        System.out.println("가장 긴 팰린드롬 길이: " + maxLength);
    }

    /**
     * 입력 문자열에 특수문자 '#'을 삽입하여 전처리한다.
     * (모든 팰린드롬 길이를 홀수로 통일시키기 위함)
     *
     * @param s 원본 문자열
     * @return 전처리된 문자 배열
     */
    private static char[] preprocess(String s){
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for(char c : s.toCharArray()){
            sb.append(c);
            sb.append('#');
        }
        return sb.toString().toCharArray();
    }

    /**
     * Manacher 알고리즘을 이용해 가장 긴 팰린드롬 부분 문자열의 길이를 반환한다.
     *
     * @param s 입력 문자열 (ex: "abacdfgdcaba")
     * @return 가장 긴 팰린드롬 부분 문자열의 길이
     */
    private static int longestPalindromicSubstringLength(String s){
        // 1. 전처리: 특수문자 '#'을 삽입해 모든 길이를 홀수로 통일
        // 예: "aba" → "#a#b#a#"
        char[] t = preprocess(s);
        int n = t.length;

        int[] P = new int[n]; // 각 위치에서의 반지름 길이 저장
        int C = 0; // 중심(C)
        int R = 0; // 오른쪽 경계(R)

        // 2. 메인 루프: 각 위치에서 팰린드롬 반지름 계산
        for(int i=0;i<n;i++){
            int mirror = 2 * C - i; // 현재 위치 i의 대칭 위치(mirror)

            // i가 R 안에 들어와 있는 경우, 최소한 P[mirror] 또는 R - i 만큼은 보장
            if(i < R){
                P[i] = Math.min(R - i, P[mirror]);
            }

            // 3. 중심 확장: 가능한 만큼 양쪽 문자 비교해 확장
            while(i + P[i] + 1 < n && i - P[i] - 1 >= 0 &&
                    t[i + P[i] + 1] == t[i - P[i] - 1]) {
                P[i]++;
            }

            // 4. 경계 업데이트: 더 오른쪽까지 확장됐다면 중심과 경계 갱신
            if(i + P[i] > R){
                C = i;
                R = i + P[i];
            }
        }

        // 5. 결과 계산: 가장 긴 반지름 * 2 + 1 - 1 (원래 문자열 기준)
        int maxLen = 0;
        for(int len : P){
            maxLen = Math.max(maxLen, len);
        }

        return maxLen; // 반지름이니까 그냥 리턴하면 된다.
    }

    /**
     * Manacher 알고리즘을 이용해 가장 긴 팰린드롬 부분 문자열을 반환한다.
     *
     * @param s 입력 문자열
     * @return 가장 긴 팰린드롬 문자열
     */
    public static String longestPalindromicSubstring(String s) {
        char[] t = preprocess(s);
        int n = t.length;

        int[] P = new int[n];
        int C = 0, R = 0;

        int maxLen = 0;
        int centerIndex = 0;

        for (int i = 0; i < n; i++) {
            int mirror = 2 * C - i;
            if (i < R) {
                P[i] = Math.min(R - i, P[mirror]);
            }

            while (i + P[i] + 1 < n && i - P[i] - 1 >= 0 &&
                    t[i + P[i] + 1] == t[i - P[i] - 1]) {
                P[i]++;
            }

            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }

            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        // 이렇게 해야 전처리 안된거 기준으로 되는거임. 암튼 그렇다고.
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

}