import java.io.*;
import java.util.*;
import java.awt.Point;

//균형잡힌 세상
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            String s = br.readLine();
            if(s.equals(".")) break;
            if(check(s)) bw.write("yes\n");
            else bw.write("no\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
    private static boolean check(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character c2 = null;

            switch (c) {
                case '(':
                case '[':
                    dq.offerFirst(c);
                    break;
                case ')':
                    c2 = dq.pollFirst();
                    if (c2 == null || c2 != '(') return false;
                    break;
                case ']':
                    c2 = dq.pollFirst();
                    if (c2 == null || c2 != '[') return false;
                    break;
            }
        }
        return dq.isEmpty(); // 스택이 비어 있어야 균형 잡힘!
    }
}