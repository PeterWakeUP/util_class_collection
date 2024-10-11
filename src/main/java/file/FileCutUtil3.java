package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCutUtil3 {

    public static int SplitFile(File sourcefile, String targetPath, int singleSize) throws Exception {

        int totalFileNum = 0;

        FileInputStream fileInputStream = new FileInputStream(sourcefile);

        int length;
        byte[] buffer = new byte[singleSize];
        while ((length = fileInputStream.read(buffer)) != -1) {

            String fileName = sourcefile.getName() + (totalFileNum + 1);//为拆分后文件命名
            File file1 = new File(targetPath);
            file1.mkdirs();
            File eachFile = new File(targetPath, fileName);//创建对象对指定文件操作

            try (FileOutputStream fileOutputStream = new FileOutputStream(eachFile);) {
                fileOutputStream.write(buffer, 0, length);//用字节流将eachCoutent数组的内容写入到每个文件当中
            } catch (IOException e) {
                e.printStackTrace();
            }

            totalFileNum++;
        }

        return totalFileNum;
    }


    public static void main(String[] args) throws Exception {
        SplitFile(new File("D:/111.mp4"), "D:\\111", 1024 * 1024 * 2);
    }
}
