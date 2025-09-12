import java.util.*;

/**
 * 💡 [LCS (Longest Common Subsequence) 문제 설명]
 *
 * - 두 문자열 A, B가 주어졌을 때, 두 문자열에 **공통적으로 등장하는 부분 수열 중 가장 긴 것의 길이**를 구하는 문제
 * - 부분 "수열"이기 때문에 반드시 연속할 필요는 없지만, 순서는 유지해야 함
 *
 * - 예시: A = "ACDBE", B = "ABCDE" → LCS는 "ACDE"
 *
 * [접근 방식]
 * - Bottom-Up 방식의 Dynamic Programming (DP) 사용
 * - 2차원 DP 배열 사용: dp[i][j]는 A의 앞 i글자, B의 앞 j글자까지 고려했을 때의 LCS 길이
 *
 * [시간복잡도]
 * - O(N * M)  (N = A의 길이, M = B의 길이)
 *
 * [추가 기능]
 * - 역추적(Backtracking)을 통해 실제 LCS 문자열도 복원 가능
 */
public class LCS {

    public static void main(String[] args) {
        String A = "ACDBE";
        String B = "ABCDE";

        System.out.println("✅ 기본 DP 방식:");
        System.out.println("LCS 길이: " + getLcsLength(A, B));
        System.out.println("LCS 문자열: " + getLcsString(A, B));

        System.out.println("\n✅ 공간 최적화 방식:");
        System.out.println("LCS 길이 (최적화): " + getLcsLengthOptimized(A, B));
    }

    /**
     * 📌 두 문자열 A와 B의 LCS(Longest Common Subsequence, 최장 공통 부분 수열) 길이를 구하는 함수
     *
     * - 부분 수열이므로 문자는 연속되지 않아도 되지만, 순서는 유지되어야 함
     * - DP (동적 프로그래밍) 2차원 배열을 사용하여 Bottom-Up 방식으로 해결
     *
     * @param A 첫 번째 문자열
     * @param B 두 번째 문자열
     * @return 두 문자열의 LCS 길이 (가장 긴 공통 부분 수열의 길이)
     *
     * 💡 시간 복잡도: O(N * M)
     *   - N: 문자열 A의 길이
     *   - M: 문자열 B의 길이
     *
     * 💡 공간 복잡도: O(N * M)
     */
    public static int getLcsLength(String A, String B) {
        int N = A.length();  // 문자열 A의 길이
        int M = B.length();  // 문자열 B의 길이

        // 🔹 DP 테이블 생성
        // dp[i][j]는 A의 i번째 글자와 B의 j번째 글자 사이에서 만들 수 있는 LCS의 길이를 저장한다.
        int[][] dp = new int[N + 1][M + 1];  // 0행/0열은 공집합("") 기준, 초기값 0

        // 🔸 테이블 채우기
        // 각 문자쌍 A[i-1], B[j-1]을 비교하며 테이블을 채워나감
        // i는 A의 문자, j는 B의 문자를 추적한다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // ✅ 문자가 같으면 → LCS 길이에 +1 (좌상단에서 +1)
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // ❌ 다르면 → 왼쪽(dp[i][j-1]) 또는 위(dp[i-1][j]) 중 큰 값 선택
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 🔚 전체 문자열 A, B를 기준으로 구한 LCS의 최종 길이 반환
        return dp[N][M];
    }

    /**
     * 📌 기본 방식으로 실제 LCS 문자열을 복원
     */
    public static String getLcsString(String A, String B) {
        int N = A.length();
        int M = B.length();
        int[][] dp = new int[N + 1][M + 1];

        // DP 테이블 채우기 (LCS 길이 계산과 동일)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 🔁 역추적을 통해 LCS 문자열 복원
        StringBuilder sb = new StringBuilder();
        int i = N, j = M; // 🔸 dp 테이블의 오른쪽 아래 (끝점)부터 시작

        while (i > 0 && j > 0) {
            // ✅ 두 문자가 같다면 → 이 문자는 LCS에 포함!
            if (A.charAt(i - 1) == B.charAt(j - 1)) {
                sb.append(A.charAt(i - 1)); // 문자 추가
                i--; // 좌상단으로 이동
                j--;
            }
            // ❌ 다르다면 → 더 큰 값을 가진 쪽(위 or 왼쪽)으로 이동
            else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--; // 위쪽으로 이동 (A 쪽 문자 제외)
            } else {
                j--; // 왼쪽으로 이동 (B 쪽 문자 제외)
            }
        }

        // 🔁 LCS는 뒤에서부터 채워졌으므로, 역순 정렬
        return sb.reverse().toString();
    }

    /**
     * 📌 공간복잡도 O(min(N, M))으로 최적화한 LCS 길이 계산
     *
     * - 일반적인 2차원 DP 배열은 O(N*M)의 공간을 차지함.
     * - 하지만 LCS 계산에서는 한 행(row)만 기억하면 되므로, 1차원 배열 2개만 사용 가능.
     * - 메모리를 줄이기 위해 항상 더 짧은 문자열을 기준으로 배열을 설정함.
     */
    public static int getLcsLengthOptimized(String A, String B) {
        // 📌 항상 A를 더 짧은 문자열로 두기 → dp 배열을 더 작게 만들 수 있음
        if (A.length() > B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }

        int N = A.length(); // A는 더 짧은 문자열
        int M = B.length();

        // 🔸 이전 행과 현재 행을 저장할 1차원 배열 (길이는 N + 1)
        int[] prev = new int[N + 1]; // 이전 행 (B[i-1])
        int[] curr = new int[N + 1]; // 현재 행 (B[i])

        // 📌 외부 루프는 긴 문자열(B), 내부 루프는 짧은 문자열(A)
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                // ✅ 문자가 같으면 → 좌상단에서 +1
                if (B.charAt(i - 1) == A.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                }
                // ❌ 다르면 → 위 또는 왼쪽 중 큰 값 선택
                else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }

            // 🔁 현재 행을 이전 행으로 넘김 (prev ← curr)
            // 배열을 직접 복사하지 않고 참조만 바꿔줌 (swap)
            int[] temp = prev;
            prev = curr;
            curr = temp;
            // 이제 curr은 새로 채울 준비가 된 빈 배열이 됨
        }

        // 최종 결과는 마지막 prev 배열의 마지막 칸에 있음
        return prev[N];
    }
}
