import java.io.*;

// 10951 A + B - 4
public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 빠른 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] nums = line.split(" ");
            int A = Integer.parseInt(nums[0]);
            int B = Integer.parseInt(nums[1]);
            sb.append(A + B).append('\n');
        }

        System.out.print(sb); // 결과 한 번에 출력
    }
}
