import java.util.*;

/**
 * 📘 LIS.java
 *
 * ✅ LIS (Longest Increasing Subsequence, 최장 증가 부분 수열)
 * - 주어진 수열에서 값이 점점 증가하는 가장 긴 부분 수열을 찾는 문제
 *
 * 🔸 구현 방식 1: DP (시간복잡도 O(N^2))
 * 🔸 구현 방식 2: 그리디 + 이진탐색 (시간복잡도 O(N log N))
 */
public class LIS {

    public static void main(String[] args) {
        int[] arr = {10, 20, 10, 30, 20, 50};

        System.out.println("🔹 DP 방식 LIS 길이: " + getLIS_DP(arr));
        List<Integer> lis = getLISWithPath(arr);
        System.out.println("🔹 LIS 수열: " + lis);

        System.out.println("🔹 이진탐색 방식 LIS 길이: " + getLIS_BinarySearch(arr));
    }

    /**
     * ✅ 방법 1: DP를 사용한 LIS 길이 계산 (시간복잡도 O(N^2))
     *
     * - dp[i]는 i번째 원소에서 끝나는 LIS의 최대 길이를 의미함.
     * - 각 원소마다 자신보다 앞에 있는 작은 값들을 찾아서 최대 길이를 갱신.
     *
     * 📌 예시: arr = {10, 20, 10, 30}
     * dp 배열 변천: [1, 2, 1, 3] → 최댓값 3이 LIS 길이
     */
    public static int getLIS_DP(int[] arr) {
        int N = arr.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1); // 최소 길이는 1 (자기 자신)

        // i: 현재 보고 있는 수열의 위치 (끝점), 즉 arr[i]를 마지막 원소로 하는 LIS의 길이를 구하고 있음.
        // j: i보다 앞에 있는 모든 인덱스를 검사함. arr[j] < arr[i]이면 증가 가능.
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // 이전 값이 더 작다면, 현재 값을 이어붙일 수 있음
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 전체 중 가장 긴 증가 부분 수열의 길이를 반환
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * ✅ DP를 이용해 LIS 수열을 추적까지 포함하여 반환
     *
     * - dp[i]: i번째 원소를 마지막으로 하는 LIS의 길이
     * - prev[i]: dp[i]가 어디서 왔는지를 저장 (역추적용)
     */
    public static List<Integer> getLISWithPath(int[] arr) {
        int N = arr.length;
        int[] dp = new int[N];         // LIS 길이 저장용
        int[] prev = new int[N];       // LIS 경로 추적용
        Arrays.fill(dp, 1);            // 최소 LIS 길이는 1 (자기 자신)
        Arrays.fill(prev, -1);         // 이전 인덱스가 없음을 의미

        int maxLen = 1;                // LIS의 최대 길이
        int lastIdx = 0;               // LIS가 끝나는 위치

        // 🔁 DP 테이블 채우기
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // 🔸 arr[j] < arr[i] → 증가 수열이 될 수 있는 조건
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1; // dp 갱신
                    prev[i] = j;       // 어디서 왔는지 기록
                }
            }

            // LIS 길이와 마지막 인덱스 갱신
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIdx = i;
            }
        }

        // 🔁 역추적해서 LIS 수열 복원
        List<Integer> lis = new ArrayList<>();
        while (lastIdx != -1) {
            lis.add(arr[lastIdx]);
            lastIdx = prev[lastIdx]; // 이전 인덱스로 이동
        }

        Collections.reverse(lis); // 거꾸로 복원했기 때문에 뒤집기
        return lis;
    }

    /**
     * ✅ 방법 2: 이진탐색을 활용한 LIS 길이 계산 (시간복잡도 O(N log N))
     *
     * - 실제 LIS를 구하는 것이 아니라, LIS의 "길이"만 구함.
     * - LIS 후보들을 저장한 리스트를 유지하며,
     *   각 숫자에 대해 이진탐색으로 들어갈 위치를 찾아 값을 "대체"함.
     *
     * 📌 핵심 아이디어:
     * - 숫자를 더 작은 값으로 대체해도 LIS 길이는 유지되므로
     *   가능한 작은 값으로 유지하면 이후 더 많은 숫자를 붙일 수 있음.
     *
     * 📌 예시:
     * arr = {10, 20, 10, 30}
     * 진행:
     * - 10 → [10]
     * - 20 → [10, 20]
     * - 10 → [10, 20] (10은 10으로 대체되며 변화 없음)
     * - 30 → [10, 20, 30]
     */
    public static int getLIS_BinarySearch(int[] arr) {
        // LIS 정보를 저장할 리스트
        List<Integer> lis = new ArrayList<>();

        for (int num : arr) {
            // num이 들어갈 적절한 위치를 이진 탐색으로 직접 찾는다
            int idx = lowerBound(lis, num);  // Collections.binarySearch()를 대체함

            if (idx == lis.size()) {
                // num이 lis의 모든 값보다 크면 맨 뒤에 추가 → LIS 길이 증가
                lis.add(num);
            } else {
                // lis[idx]를 num으로 교체 → LIS 길이는 유지되지만 더 좋은 수열 가능성 확보
                lis.set(idx, num);
            }
        }

        // lis 리스트의 길이가 최장 증가 부분 수열의 길이
        return lis.size();
    }

    // 🔍 lowerBound: 리스트 내에서 num 이상의 값이 처음으로 나타나는 위치를 반환
    public static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        // 이진 탐색 수행
        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                // target보다 작으면 왼쪽 구간은 버리고 오른쪽 탐색
                left = mid + 1;
            } else {
                // target 이상이면 그 위치가 정답일 수 있으므로 범위를 좁힘
                right = mid;
            }
        }

        // left는 target이 들어갈 가장 왼쪽 위치 (== 삽입 위치)
        return left;
    }


}
