import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Parallel_Code {

    public static void main(String[] args) throws IOException{

        List<String> filesSource = new ArrayList<>();
        filesSource.add("src/Text_Folder/Text1.txt");
        filesSource.add("src/Text_Folder/Text2.txt");
        filesSource.add("src/Text_Folder/Text3.txt");
        filesSource.add("src/Text_Folder/Text4.txt");
        filesSource.add("src/Text_Folder/Text5.txt");
        filesSource.add("src/Image_Folder/Image1.png");
        filesSource.add("src/Image_Folder/Image2.png");
        filesSource.add("src/Image_Folder/Image3.png");
        filesSource.add("src/Image_Folder/Image4.png");
        filesSource.add("src/Image_Folder/Image5.png");
        filesSource.add("src/Video_Folder/Video.mp4");
        System.out.println("=== File loaded successfully! ===\n");

        try{
            compressFolder(filesSource);
        } catch (FileNotFoundException fnfe) {
            System.err.println("File Loading Error!");
        }

    }

    public static void compressFolder(List<String> filesSource) throws IOException{

        System.out.println("=== Parallel Processing ===");
        filesSource.parallelStream().forEach(System.out::println);
        FileOutputStream fos = new FileOutputStream("New_Compressed_Folder.zip");
        ZipOutputStream zos = new ZipOutputStream(fos);

        System.out.println("\n==========================================");

        System.out.println("The zip file has been created!");

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