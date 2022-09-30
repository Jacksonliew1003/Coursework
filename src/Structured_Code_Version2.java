import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Structured_Code_Version2 {

    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the zip file you want: ");
        String compressName = input.nextLine();

        List<String> filesSource = new ArrayList<>();
        filesSource.add("src/PDF_Folder/PDF1.pdf");
        filesSource.add("src/PDF_Folder/PDF2.pdf");
        filesSource.add("src/PDF_Folder/PDF3.pdf");
        filesSource.add("src/PDF_Folder/PDF4.pdf");
        filesSource.add("src/PDF_Folder/PDF5.pdf");
        filesSource.add("src/Image_Folder/Image1.png");
        filesSource.add("src/Image_Folder/Image2.png");
        filesSource.add("src/Image_Folder/Image3.png");
        filesSource.add("src/Image_Folder/Image4.png");
        filesSource.add("src/Image_Folder/Image5.png");
        filesSource.add("src/Video_Folder/Video1.mp4");
        filesSource.add("src/Video_Folder/Video2.mp4");
        filesSource.add("src/Video_Folder/Video3.mp4");
        filesSource.add("src/Video_Folder/Video4.mp4");
        filesSource.add("src/Video_Folder/Video5.mp4");

        System.out.println("==================================================\n");

        try{
            compressFolder(filesSource,compressName);
        } catch (FileNotFoundException fnfe) {
            System.err.println("File Loading Error!");
        }

    }

    public static void compressFolder(List<String> filesSource, String compressName) throws IOException{

        long startTime = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream(compressName + ".zip");
        ZipOutputStream zos = new ZipOutputStream(fos);

        System.out.println("The zip file has been created!");

            for (String fileSource : filesSource) {

                File compressZip = new File(fileSource);
                FileInputStream fis = new FileInputStream(compressZip);
                ZipEntry ze = new ZipEntry(compressZip.getName());
                zos.putNextEntry(ze);
                byte[] bytes = new byte[1024];

                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
                System.out.println("Processing: " + fileSource);
                fis.close();
            }
        zos.close();
        fos.close();
        long endTime = System.currentTimeMillis();
        System.out.printf("Processing Time: %f s\n\n", (double)(endTime - startTime) / 1000);
        System.out.println("Compress Data Done!");

    }
}
