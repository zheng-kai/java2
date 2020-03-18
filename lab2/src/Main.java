import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String fileName = "了不起的盖茨比英文.txt";
        try {
            countWord(System.getProperty("user.dir"), fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countWord(String path, String inputFileName) throws IOException {
        countWord(path, inputFileName, "output.txt");
    }

    public static void countWord(String path, String inputFileName, String outputFileName) throws IOException {

        File inputFile = new File(path + "/" + inputFileName);
        System.out.println(inputFile.canRead());
        if(!inputFile.canRead()){
            throw new FileNotFoundException("无法访问该文件");
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8));
        Map<String, Integer> map = new TreeMap<>();
        String line;
        while ((line = in.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (String w : words) {
                if (w.matches("[\\w-]+")) {
                    map.merge(w, 1, (o1, o2) -> o1 + o2);
                }
            }
        }
        in.close();

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputFileName))));
        for (Map.Entry<String, Integer> entry : list) {
            writer.write(entry.getKey() + " " + entry.getValue());
            writer.newLine();
        }
        writer.flush();
        writer.close();

    }
}
