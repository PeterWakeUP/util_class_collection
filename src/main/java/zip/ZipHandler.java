package zip;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipHandler {

    public static void main(String[] args) throws IOException {
        ZipHandler zipHandler = new ZipHandler();
        zipHandler.decompress("D:\\BaiduNetdiskDownload\\123.zip", "D:\\BaiduNetdiskDownload");
    }

    private ZipFile zipFile;

    private String destPath;

    public void decompress(String srcPath, String destPath) throws IOException {

        long start = System.currentTimeMillis();

        this.destPath = destPath;
        this.zipFile = new ZipFile(srcPath, Charset.forName("GBK"));

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();

            if (zipEntry.isDirectory()) {
                continue;
            }

            threadPool.execute(new FileWritingTask(zipEntry));
        }

        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zipFile.close();

        long end = System.currentTimeMillis();
        System.out.println("解压完成，所用时间为：" + (end - start) + "ms");

    }

    private class FileWritingTask implements Runnable {

        private ZipEntry zipEntry;

        FileWritingTask(ZipEntry zipEntry) {
            this.zipEntry = zipEntry;
        }

        public void run() {
            File file = new File(destPath + File.separator + zipEntry.getName());
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            try {
                InputStream inputStream = zipFile.getInputStream(this.zipEntry);
                OutputStream outputStream = new FileOutputStream(file);
                int read;
                byte[] bytes = new byte[inputStream.available()];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

