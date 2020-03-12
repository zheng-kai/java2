import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();
        for (String arg : args) {
            map.merge(arg, 1, (integer, integer2) -> integer + integer2);
            //如果key中不存在arg则value为1,否则为原value值加1
        }
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
