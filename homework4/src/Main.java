import java.io.*;
import java.util.Scanner;

public class Main {
    /**
     * @param args args[0] source file , args[1] destination , args[2] outputFileName
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.err.println("需要三个参数 1.源文件 2.目标目录 3.目标文件名");
            System.exit(-1);
        }
        String source = args[0];
        String destinationPath = args[1];
        String outputFileName = args[2];
        File sFile = new File(source);
        if(!sFile.isFile()){
            System.err.println("要求源文件非目录文件");
        }
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sFile));

        File dir = new File(destinationPath);
        File outputFile = new File(destinationPath + File.separatorChar + outputFileName);
        check(dir,outputFile);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
        int data;
        while ((data = inputStream.read()) != -1){
            outputStream.write(data);
        }
        outputStream.flush();
        System.out.println("文件写入完成");
        outputStream.close();
        inputStream.close();
    }
    private static void check(File dir, File outputFile) throws IOException {
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("目标目录不存在，且创建失败");
            } else {
                System.out.println("目标目录创建成功");
            }
        } else {
            if (outputFile.exists()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("新文件已经存在，是否覆盖? [Y/N]");
                while (true){
                    String s = scanner.nextLine();
                    if (s.matches("[Yy]")) {
                        break;
                    } else if (s.matches("[Nn]")) {
                        System.out.println("cancel");
                        System.exit(0);
                    }else {
                        System.err.println("输入格式错误,重新输入[Y/N]");
                    }
                }
            }else {
                if(!outputFile.createNewFile()){
                    System.err.println("文件创建失败");
                }else {
                    System.out.println("文件创建成功");
                }
            }
        }
    }

}
