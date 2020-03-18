import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader in =
                null;
        String fileName = "了不起的盖茨比英文.txt";
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(System.getProperty("user.dir")+"/"+fileName)), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Map<String, Integer> map = new TreeMap<>();
        String line;
        try{
            while ((line = in.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String w : words) {
                    if(w.matches("[\\w-]+")){
                        map.merge(w, 1, (o1, o2) -> o1 + o2);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
