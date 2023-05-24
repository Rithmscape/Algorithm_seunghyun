import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] solutions = new long[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int next = Integer.parseInt(stt.nextToken());
			solutions[i] = next;
		}
		
		Arrays.sort(solutions);
		
		long[] res = new long[3];
		
		long diff = Long.MAX_VALUE;

		// 가장 왼쪽부터 시작해서 가장 오른쪽 끝까지
		for(int i = 0; i < N; i++) {
			int left = i+1; // 가장 왼쪽을 제외하고 그 다음부터 순회
			int right = N-1;
			
			while(left < right) {
				long sum = solutions[i] + solutions[left] + solutions[right];
				
				long cur_diff = Math.abs(sum);
				
				if(cur_diff < diff) {
					diff = cur_diff;
					res[0] = solutions[i];
					res[1] = solutions[left];
					res[2] = solutions[right];
				}
				
				if(sum > 0) { // 차이가 0보다 크다. 오른쪽 원소를 한 칸 앞으로
					right--;
				} else { // 차이가 0보다 작다. 왼쪽 원소를 한 칸 앞으로
					left++;
				}
			}
		}
		System.out.println(res[0] + " " + res[1] + " " + res[2]);
	}
}