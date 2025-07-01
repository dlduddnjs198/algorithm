import java.io.*;
import java.util.*;

// 1620 나는야 포켓몬 마스터 이다솜
public class Main {

    static class Pokemon {
        String name;
        int num;

        Pokemon(String name, int num){
            this.name = name;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 포켓몬 개수 N
        int M = Integer.parseInt(st.nextToken()); // 맞춰야하는 문제 개수 M

        Map<Integer, Pokemon> numMap = new HashMap<>();
        Map<String, Pokemon> nameMap = new HashMap<>();

        for(int i=1;i<=N;i++){
            Pokemon pokemon = new Pokemon(br.readLine(), i);
            numMap.put(i, pokemon);
            nameMap.put(pokemon.name, pokemon);
        }

        for(int i=0;i<M;i++){
            String str = br.readLine();
            char c = str.charAt(0);
            if(c >= '0' && c <= '9') sb.append(numMap.get(Integer.parseInt(str)).name).append("\n");
            else sb.append(nameMap.get(str).num).append("\n");
        }

        System.out.println(sb.toString());
    }

}