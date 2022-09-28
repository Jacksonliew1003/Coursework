import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Structured_Code_Version3 {

    public static void main(String[] args) throws IOException {

        List<String> filesSource = Arrays.asList("src/Text1.txt", "src/Text2.txt", "src/Text3.txt", "src/Text4.txt", "src/Text5.txt");
        System.out.println("=== File loaded successfully! ===\n");

        try{
            compressFolder(filesSource);
        } catch (FileNotFoundException fnfe) {
            System.err.println("File Loading Error!");
        }

    }

    public static void compressFolder(List<String> filesSource) throws IOException{

        FileOutputStream fos = new FileOutputStream("Compressed_Folder.zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        System.out.println("The zip file has been created!\n");

        for (String fileSource : filesSource) {
            long startTime = System.nanoTime();
            File compressZip = new File(fileSource);
            FileInputStream fis = new FileInputStream(compressZip);
            ZipEntry ze = new ZipEntry(compressZip.getName());
            zos.putNextEntry(ze);
            byte[] bytes = new byte[1024];

            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0,length);
            }

            System.out.println(fileSource);
            long endTime = System.nanoTime();
            System.out.printf("Processing Time: %f ms\n\n", (float)(endTime - startTime) / 1000000);

            fis.close();

        }
        zos.close();
        fos.close();
        System.out.println("Compress Data Done!");

    }

}

