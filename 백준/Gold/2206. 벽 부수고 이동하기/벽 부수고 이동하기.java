import java.util.*;
import java.io.*;

//가중치 x, 최단 경로 -> BFS의 부가기능 dist
    // 모든 벽을 뚫어보는 경우 (1_000 * 1_000)^2 시간초과 발생
    // 따라서 벽을 부순 경우, 안 부순 경우를 나누어 최소거리를 갱신
    // 벽을 부숴야 더 빨리 도착한다고 생각하지만 벽을 부수지 않았을 때 더 빨리 도착할 수도 있음으로 3차원 배열 사용

public class Main{
    static int N;   //세로
    static int M;   //가로
    static int[][] arr;
    static int[][][] dist;
    static int[][] dir = { {0,-1},{0,1}, {1,0},{-1,0}};

    public static class Pos{
        int x;
        int y;
        int block;  // 벽을 부웠는지 아닌지 확인

        public Pos(int x, int y, int block){
            this.x = x;
            this.y = y;
            this.block = block;
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // [N, M, 0] : 벽 안 부수고 지나가는 방문노드 경로
        // [N, M, 1] : 벽 하나를 부수고 지나가는 방문노드 경로
        dist = new int [N+1][M+1][2];
        arr = new int[N+1][M+1];

        //arr 입력 받기
        for(int i =1; i<=N; i++){
            String str = br.readLine();
            for(int j =1; j<=M; j++){

                arr[i][j] = str.charAt(j-1) - '0';

            }
        }

        int answer = BFS();
        System.out.println(answer);
    }
    static int BFS(){
        Queue<Pos> queue = new LinkedList<>();

        //시작점 방문
        queue.add( new Pos(1,1,0));
        dist[1][1][0] = 1;

        //큐가 빌 때까지 방문
        while(!queue.isEmpty()){

            Pos pos = queue.poll();
            int x = pos.x;
            int y = pos.y;
            int block = pos.block;  // 벽을 부쉈으면 1, 안 부쉈으면 0

            //도착지점에서 만나면 종료
            if(x == N && y == M){
                return dist[x][y][block];
            }

            for(int i =0; i<4; i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if(nx <=0 || ny <=0 || nx > N || ny >M) continue;

            //벽을 만났는데,
                //이전에 벽을 부순 적 O 면 -> pass
                //이전에 벽을 부순 적 X 면 -> 벽 부수기
                if(arr[nx][ny] == 1 && block == 0){
                    queue.add(new Pos(nx, ny, 1));      // 벽 부수기
                    dist[nx][ny][1] = dist[x][y][0] + 1;      // 벽 안 부순 거리 + 1
                }

            //벽이 아니면, (방문한 적이 없는지 확인하고)
                // 이전에 벽을 부쉈든 -> 방문
                // 벽을 안 부쉈든 -> 방문
                else if (arr[nx][ny] == 0 && dist[nx][ny][block] == 0){

                    queue.add(new Pos(nx, ny, block));
                    dist[nx][ny][block] = dist[x][y][block] + 1;
                }
            }

        }
        return -1;

    }

}