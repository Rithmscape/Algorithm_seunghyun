import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        
        int pick = nums.length/2;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int n : nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
        
        if(pick <= map.size()) return pick;
        else return map.size();
    }
}