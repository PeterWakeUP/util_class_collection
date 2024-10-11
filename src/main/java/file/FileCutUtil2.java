package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileCutUtil2 {

    public static int SplitFile(File sourcefile, String targetPath, int singleSize) throws Exception {

        int totalFileNum = 0;
        int blockSzie = 1024 * 1024 * 1024; //1GB

        FileInputStream fileInputStream = new FileInputStream(sourcefile);

        int length;
        byte[] buffer = new byte[blockSzie];
        while ((length = fileInputStream.read(buffer)) != -1) {
            // 处理数据
            int fileNum;//定义变量fileNum表示拆分后文件数量
            if (0 == length % singleSize) {
                fileNum = length / singleSize;//源文件大小能整除单位大小，则直接等于文件数量
            } else {
                fileNum = length / singleSize + 1;//不能整除则多一个文件放剩余的文件内容
            }
            for (int a = 0; a < fileNum; a++) {
                String fileName = sourcefile.getName() + (totalFileNum + a + 1);//为拆分后文件命名
                File file1 = new File(targetPath);
                file1.mkdirs();
                File eachFile = new File(targetPath, fileName);//创建对象对指定文件操作

                byte[] eachCount;//创建数组
                if (a != fileNum - 1){
                    //非最后一个文件时，前面的文件，按对应的单位大小复制到数组当中，fileNum是多少个，eachCoutent数组内容就有多少个元素
                    eachCount = Arrays.copyOfRange(buffer, singleSize * a, singleSize * (a + 1));
                }else{
                    //当为最后一个文件，将源文件内容剩余的内容复制到该数组去
                    eachCount = Arrays.copyOfRange(buffer, singleSize * a, length);
                }
                try (FileOutputStream fileOutputStream = new FileOutputStream(eachFile);) {
                    fileOutputStream.write(eachCount);//用字节流将eachCoutent数组的内容写入到每个文件当中
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            totalFileNum += fileNum;
        }

        return totalFileNum;
    }


    public static void main(String[] args) throws Exception {
        SplitFile(new File("D:/111.mp4"), "D:\\111", 1024 * 1024 * 2);
    }
}
