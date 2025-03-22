import java.io.*;
import java.util.*;

//팰린드롬수
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int left;
        int right;

        while(true){
            String s = br.readLine();
            int[] nums = new int[s.length()];
            boolean isPal = true;
            for(int i=0;i<nums.length;i++){
                nums[i] = s.charAt(i) - '0';
            }
            if(nums[0]==0) break;
            left = 0;
            right = nums.length-1;
            int N = (int)Math.ceil(nums.length/2);
            for(int i=0;i<N;i++){
                if(nums[left]!=nums[right]){
                    isPal = false;
                    break;
                }
                left++;
                right--;
            }
            if(isPal) bw.write("yes\n");
            else bw.write("no\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
