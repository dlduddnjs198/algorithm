import java.io.*;
import java.util.*;
import java.awt.Point;

//괄호(스택이지만 이 문제는 안써도 풀린다고 함)
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        Deque<Character> dq = new ArrayDeque<>();
        boolean check = true;
        for(int i=0;i<T;i++){
            String s = br.readLine();
            for(int j=0;j<s.length();j++){
                char c = s.charAt(j);
                switch(c){
                    case '(':
                        dq.offerFirst(c);
                        break;
                    case ')':
                        Character c2 = dq.pollFirst();
                        if(c2==null || c2!='('){
                            check = false;
                            break;
                        }
                }
            }
            if(check && dq.isEmpty()) bw.write("YES\n");
            else bw.write("NO\n");
            dq.clear();
            check = true;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}