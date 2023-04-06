import java.util.*;

//투 포인터
class Main{
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        twoPointer();
    }

    static void twoPointer(){
        int left = 1;
        int right =1;
        int sum = 0;
        int cnt = 0;

        while(left <= N){

            if(sum < N){
                sum += right;
                right++;
            }
            else{ //sum >=N
                sum -= left;
                left++;
            }

            if(sum ==N) cnt++;
        }

        System.out.println(cnt);
    }
}