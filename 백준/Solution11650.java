import java.io.*;
import java.util.*;
import java.awt.Point;

//좌표 정렬하기
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(points, (a,b) -> {
            if(a.x==b.x) return a.y-b.y;
            return a.x-b.x;
        });

        for(int i=0;i<N;i++){
            bw.write(points[i].x + " " + points[i].y + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}