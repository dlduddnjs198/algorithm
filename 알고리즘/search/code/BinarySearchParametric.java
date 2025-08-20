import java.util.*;

// 📌 문제 배경 (예시: 떡 자르기 문제)
// 한 손님이 떡집에 와서 떡을 일정 길이 이상으로 잘라 달라고 요청했다.
// 떡은 길이가 서로 다른 여러 개가 있고, 절단기(기계)를 이용해 특정 높이로 잘라낸다.
//
// 🪵 문제 상황
// 떡은 한 줄로 놓여 있으며, 떡마다 길이가 다름
// 절단기는 지면으로부터 H 높이에 위치하고 있음
// 떡의 길이가 절단기 높이 H보다 크면, 그 떡은 H 이후의 길이만 잘려 나가게 됨
// 잘린 떡의 총합이 손님이 요구한 최소 양 M 이상이 되도록 절단기 높이 H를 설정해야 함
// 가능한 H 중 가장 높은 값을 구하시오

// 예를 들어서 N = 4, M = 6
// 떡의 길이 배열 = [19, 15, 10, 17] 이라면 15면 4, 0, 0, 2 = 6해서 만족하니까 15가 정답이라는 뜻이다.
public class BinarySearchParametric {

    static int[] riceCakes = {19, 15, 10, 17}; // 떡의 길이 배열
    static int M = 6; // 최소로 가져가야 할 떡의 총 길이

    public static void main(String[] args) {
        int maxHeight = getMaxCutHeight(riceCakes, M);
        System.out.println("절단기의 최대 높이: " + maxHeight); // 출력: 15
    }

    /**
     * 떡 절단기의 최대 높이를 파라메트릭 서치로 구하는 메서드
     * @param riceCakes: 떡 배열
     * @param target: 가져가야 할 최소 떡 길이
     * @return 절단기 높이의 최댓값
     */
    public static int getMaxCutHeight(int[] riceCakes, int target) {
        int left = 0; // 절단기의 최솟값 (0부터 가능)
        int right = Arrays.stream(riceCakes).max().getAsInt(); // 가장 긴 떡까지 가능
        int result = 0; // 최적의 높이 저장

        while (left <= right) {
            int mid = left + (right - left) / 2; // 현재 절단기 높이 (Overflow 방지)
            long total = getTotalCutLength(riceCakes, mid); // mid 높이로 잘랐을 때 얻는 떡의 총합

            if (total >= target) {
                // 조건을 만족하므로, 더 높이 자를 수 있는지 시도
                result = mid;         // 일단 현재 높이는 가능한 값이므로 저장
                left = mid + 1;       // 더 높은 높이를 탐색
            } else {
                // 떡이 부족함 → 절단기 높이를 낮춰야 함
                right = mid - 1;
            }
        }

        return result;
    }

    /**
     * 주어진 절단기 높이로 자를 때 얻는 떡의 총 길이
     * @param riceCakes: 떡 배열
     * @param cutHeight: 절단기 높이
     * @return 잘라서 얻는 떡의 총합
     */
    private static long getTotalCutLength(int[] riceCakes, int cutHeight) {
        long total = 0;

        for (int len : riceCakes) {
            if (len > cutHeight) {
                total += (len - cutHeight); // 잘라낸 만큼 합산
            }
        }

        return total;
    }
}
