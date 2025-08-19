public class BinarySearch {
    static int[] arr = {1, 3, 5, 7, 8, 10, 20, 35, 99, 100};

    public static void main(String[] args) {
        System.out.println("1. 순환 호출을 이용한 이진 탐색");
        System.out.println(binarySearch1(5, 0, arr.length-1)); // 2

        System.out.println("\n2. 반복을 이용한 이진 탐색");
        System.out.println(binarySearch2(20, 0, arr.length-1)); // 6

    }

    // 재귀적 탐색
    static int binarySearch1(int key, int low, int high) {
        int mid;

        if(low <= high) {
            mid = (low + high) / 2;

            if(key == arr[mid]) { // 탐색 성공
                return mid;
            } else if(key < arr[mid]) {
                return binarySearch1(key ,low, mid-1); // 왼쪽 부분 탐색
            } else {
                return binarySearch1(key, mid+1, high); // 오른쪽 부분 탐색
            }
        }

        return -1; // 탐색 실패
    }

    // 반복적 탐색
    private static int binarySearch2(int key, int left, int right){
        int mid;

        while(left <= right){
            mid = left + (right - left) / 2; // 정수 오버플로우 방지

            if(key==arr[mid]) return mid;
            else if(key<arr[mid]) right = mid-1;
            else left = mid+1;
        }

        return -1;
    }

}