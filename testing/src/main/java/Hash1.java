import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hash1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input1 = in.nextLine();
        String input2 = in.nextLine();



        Map<String, Integer> count = new HashMap<>();
        for (String x : input2.split("")) {
            int nowCount = count.getOrDefault(x, 1);
            count.put(x, nowCount + 1);
        }

        int max = 0;
        for (String key : count.keySet()) {
            max = Math.max(max, count.get(key));
        }

        System.out.println(max);
        return;
    }
}
