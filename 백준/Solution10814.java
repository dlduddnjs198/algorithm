import java.io.*;
import java.util.*;

//나이순 정렬
public class Main {

    static class User{
        int age;
        String name;

        User(int age, String name){
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        User[] users = new User[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            users[i] = new User(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        Arrays.sort(users, (a, b) -> {
            return a.age-b.age;
        });

        for(int i=0;i<N;i++){
            bw.write(users[i].age + " " + users[i].name + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}