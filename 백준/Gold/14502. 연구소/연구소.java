import java.util.*;
import java.io.*;

/* 빈 영역을 배열로 받았을 때 */

//그래프와 DFS, BFS
    //1. 벽을 세우기 -> 빈 영역 empty 중에서 3개 뽑기(완전탐색 DFS)
    //2. 바이러스 상하좌우로 퍼뜨리기 -> BFS
    //3. 안전 영역 구하기 -> 반복문 돌면서 최댓값 갱신
class Main{

    static int N;   //지도의 세로
    static int M;   //지도의 가로
    static int empty;   //지도의 빈공간
    static int answer;  //정답
    static boolean [][]visit;
    static int [][]dir = {{1,0}, {-1,0}, {0,1}, {0,-1}}; //상하좌우 탐색을 위한 임의의 배열

    static int [][] arr;    // 연구소의 지도 배열
    static int [][] blank;  // 빈 영역을 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        visit = new boolean[N+1][M+1];
        arr = new int[N+1][M+1];
        blank = new int[N * M +1][2];

        //지도 입력받기 + 빈 벽의 위치만 따로 모으기
        for(int i =1; i<=N; i++){
            for(int j =1; j<=M; j++){

                arr[i][j] = sc.nextInt();

                if(arr[i][j] ==0){
                    empty++;
                    blank[empty][0] = i;
                    blank[empty][1] = j;
                }
            }
        }

        //빈 벽 중에서 3개 뽑아 벽 세우기
        DFS(1,0);

        //최대 안전영역 출력
        System.out.println(answer);
    }

    //1. 벽 세우기 -  idx 번째 빈칸에 벽을 세울지 말지를 결정
        // 세운 벽의 수 selected_cnt
    static void DFS(int idx, int selected_cnt){

    /* 종료 조건 */
        //3개의 벽을 모두 다 세운 경우 -> 종료
        if(selected_cnt ==3){

            //바이러스 퍼뜨리기
            BFS();

            //재귀 종료
            return;
        }

        //더 이상 세울 수 있는 벽이 없는 경우 -> 종료
            // blank의 크기는 사실 [N * M +1]이 아닌 empty 만큼이다.
            // 따라서 벽을 세우는 위치인 blank[idx][]의 idx는 empty 크기를 넘을 수 없다.
        if(idx > empty) return;

    /* 반복 조건 */
        //빈 공간에 벽을 세울 때
        arr[blank[idx][0]][blank[idx][1]] =1;
        DFS(idx+1, selected_cnt+1);

        //빈 공간에 벽을 안 세울 때
        arr[blank[idx][0]][blank[idx][1]] = 0;
        DFS(idx+1, selected_cnt);

    }

    //2. 바이러스 퍼뜨리기
    static void BFS() {
        Queue<Integer> queue = new LinkedList<>();

        //초기화
        ///Arrays.fill은 1차원 배열에서만 사용 가능
        for (boolean[] val : visit) {
            Arrays.fill(val, false);
        }

        //시작점 큐에 넣기
        //시작점이 여러 개 일때 -> 모든 바이러스 위치를 큐에 넣어준다
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

                //visit 초기화
                //visit[i][j] = false;

                if (arr[i][j] == 2) {
                    queue.add(i); //x 좌표
                    queue.add(j); //y 좌표

                    visit[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();

            //상하좌우 퍼뜨리기
            for(int i=0; i<4; i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                //좌표를 넘지 않으면 안됨
                if (nx <= 0 || ny <= 0 || nx > N || ny > M) continue;

                //빈 벽만 됨
                if (arr[nx][ny] != 0) continue;

                //방문 했던 곳이면 안됨
                if (visit[nx][ny]) continue;

                //해당 조건에 모두 해당하지 않으면
                visit[nx][ny] = true;
                queue.add(nx);
                queue.add(ny);
            }
        }

        //안전 영역의 넓이 계산
        safetyZone();
    }

    static int safetyZone(){
        int cnt =0;

        for(int i =1; i<=N; i++){
            for(int j =1; j<=M; j++){

                //방문했다의 의미는 바이러스가 퍼졌다는 의미이다. 따라서 0이면서 방문하지 않은 곳 확인
                if(arr[i][j] == 0 && !visit[i][j]) cnt++;
            }
        }

        //최대 안전영역 찾기
        answer = Math.max(answer, cnt);
    }

}