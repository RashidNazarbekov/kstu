import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

    }

    public static int numberOfPairs(int[] numms) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int result = 0;
        for (int i : map.keySet()) {
            if (map.get(i) % 2 == 0) {
                result += (map.get(i) / 2);
            } else {
                result += ((map.get(i) - 1) / 2);
            }
        }
        return result;
    }
}