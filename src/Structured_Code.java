import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Structured_Code {

    public static void main(String[] args) {

        String fileSource = "Text1.txt, Text2.txt";
        System.out.println("=== File loaded successfully! ===\n");

        long startTime = System.nanoTime();

        compressFolder(fileSource);


        long endTime = System.nanoTime();
        System.out.printf("Processing Time: %f ms\n", (float)(endTime - startTime) / 1000000);

    }

    public static void compressFolder(String zipSourceInput) {
        try {
            FileOutputStream fos = new FileOutputStream("Compressed_Folder.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            System.out.println("The zip file has been created!\n");

            File compressZip = new File(zipSourceInput);
            FileInputStream fis = new FileInputStream(compressZip);
            ZipEntry ze = new ZipEntry(compressZip.getName());
            zos.putNextEntry(ze);
            byte[] bytes = new byte[1024];

            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0,length);
            }

            fis.close();
            zos.close();
            fos.close();
            System.out.println("Compress Data Done!");
        } catch (IOException ioe) {
            System.err.println("Compression file creation error!");
        }


    }

}

