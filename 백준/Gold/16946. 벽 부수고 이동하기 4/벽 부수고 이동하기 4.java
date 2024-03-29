import java.io.*;
import java.util.*;

public class Main {

     static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int arr[][];
    static Point ans[][];
    static int[][]dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static boolean visited[][];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //입력
        visited = new boolean[N][M];
        ans = new Point[N][M];
        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            
            for(int j=0; j<M; j++) {
                arr[i][j] = str.charAt(j) -'0';
                ans[i][j] = new Point(0,0);
            }
        }

        int num = 2;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                
                if(arr[i][j] == 0 && !visited[i][j]) {
                    bfs(i,j, num++);
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                
                if(arr[i][j] == 1) check(i,j);
                else sb.append(0);
            }
            
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    public static void check(int x, int y) {
        HashSet<Integer> dict = new HashSet<>();
        
        int cnt = 1;
        for(int i=0; i<4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            
            if(nx < 0 || ny <0 || nx >=N || ny >= M) continue;
            
            if(arr[nx][ny] == 0 && !dict.contains(ans[nx][ny].y)) {
                cnt += ans[nx][ny].x;
                dict.add(ans[nx][ny].y);
            }
        }

        sb.append(cnt % 10);
    }
    
    public static void bfs(int x, int y, int num) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));
        
        Queue<Point> temp = new LinkedList<>();
        temp.add(new Point(x,y));

        int cnt = 1;
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int i=0;i<4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];

                if(nx < 0 || ny <0 || nx >=N || ny >=M ) continue;

                if(!visited[nx][ny] && arr[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                    temp.add(new Point(nx,ny));
                    cnt++;
                }
            }
        }


        while(!temp.isEmpty()) {
            Point cur = temp.poll();
            ans[cur.x][cur.y].x = cnt;	// 개수	
            ans[cur.x][cur.y].y = num;	// 묶음 번호
        }

    }

}