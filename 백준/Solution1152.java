import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 단어의 개수
public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        System.out.println(st.countTokens());
    }
}
