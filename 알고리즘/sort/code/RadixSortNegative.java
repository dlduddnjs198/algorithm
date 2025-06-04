import java.util.*;

/**
 * 🔢 LSD 기수 정렬 (음수 포함 버전)
 *
 * ✅ 개요
 * - 가장 낮은 자릿수(1의 자리)부터 시작해 정렬하는 방식
 * - 음수는 별도로 양수화한 뒤 정렬하고, 다시 음수화하며 역순으로 배열
 *
 * ✅ 처리 전략
 * - 양수와 음수를 분리 → 각각 LSD 정렬 수행
 * - 음수는 절댓값 기준으로 정렬 → 정렬 후 역순 정렬 + 다시 음수 부호
 * - 마지막에 음수 + 양수를 다시 합쳐줌
 *
 * ✅ 사용 예
 * - 일반적인 정수 배열에서 범위가 음/양수 모두 섞여있는 경우
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = {170, -45, 75, -90, 802, 24, 2, 66, -1};

        radixSortWithNegative(arr);

        System.out.println(Arrays.toString(arr));
    }

    // 전체 정렬 함수
    public static void radixSortWithNegative(int[] arr) {
        List<Integer> negatives = new ArrayList<>();
        List<Integer> positives = new ArrayList<>();

        // 🔹 1. 음수/양수 분리
        for (int num : arr) {
            if (num < 0) negatives.add(-num); // 음수는 절댓값 저장
            else positives.add(num);
        }

        // 🔹 2. 양수와 음수 각각 LSD 정렬 수행
        int[] posArr = positives.stream().mapToInt(i -> i).toArray();
        int[] negArr = negatives.stream().mapToInt(i -> i).toArray();

        lsdRadixSort(posArr);
        lsdRadixSort(negArr);

        // 🔹 3. 음수는 다시 -붙이고 역순 정렬
        for (int i = 0; i < negArr.length; i++) {
            negArr[i] = -negArr[i];
        }
        reverse(negArr);

        // 🔹 4. 음수 + 양수 붙이기
        int index = 0;
        for (int num : negArr) arr[index++] = num;
        for (int num : posArr) arr[index++] = num;
    }

    // LSD 기수 정렬: 음수는 없는 전제
    private static void lsdRadixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int exp = 1;

        while (max / exp > 0) {
            countingSortByDigit(arr, exp);
            exp *= 10;
        }
    }

    // 자릿수 기준 카운팅 정렬
    private static void countingSortByDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10]; // 0~9

        // 빈도 세기
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        // 누적합
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 뒤에서부터 순회 (안정성 유지)
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[--count[digit]] = arr[i];
        }

        // 복사
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // 배열 역순
    private static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }
}
