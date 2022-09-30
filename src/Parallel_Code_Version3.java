import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Parallel_Code_Version3 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the zip file you want: ");
        String compressName = input.nextLine();

        File fileSource = new File("src/Folder");
        File[] filesList = fileSource.listFiles();

        int numberOfThread = 2;
        Thread[] threadArray = new Thread[numberOfThread];

        final int filesOnEachThread = filesList.length / numberOfThread;
        final int remainingFiles = filesList.length % numberOfThread;

        for (int temp = 0; temp < numberOfThread; temp++) {
            int thread = temp;

            threadArray[temp] = new Thread(() -> {
                try {
                    long startTime = System.currentTimeMillis();

                    processingThread(compressName, filesList, numberOfThread, thread, filesOnEachThread, remainingFiles);

                    long endTime = System.currentTimeMillis();
                    System.out.printf("Processing Time: %f s\n\n", (double) (endTime - startTime) / 1000);
                    System.out.println("Compress Data Done!");

                } catch (FileNotFoundException fnfe) {
                    System.err.println("File Loading Error!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (Thread t1 : threadArray){
            t1.start();
        }

        for (Thread t2 : threadArray){
            try{
                t2.join();
            }catch (InterruptedException ie){
                ie.getMessage();
            }
        }

    }

    public static void processingThread(String compressName, File[] filesList, int numberOfThread, int thread, int filesOnEachThread, int remainingFiles) throws IOException {
        List<File> fileInput = new ArrayList<>();
        FileOutputStream fos = new FileOutputStream(compressName + ".zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        for (int i = thread * filesOnEachThread; i < (thread+1) * filesOnEachThread; i++){
            fileInput.add(filesList[i]);

            if (thread == numberOfThread -1 && remainingFiles >0) {
                fileInput.addAll(Arrays.asList(filesList).subList(filesList.length - remainingFiles, filesList.length));
            }
        }
        for (File file: fileInput){
            File compressZip = new File(String.valueOf(file));
            FileInputStream fis = new FileInputStream(compressZip);
            ZipEntry ze = new ZipEntry(compressZip.getName());
            zos.putNextEntry(ze);
            byte[] bytes = new byte[1024];

            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            fis.close();
            System.out.println("Processing " + file.getName() + " in thread " + Thread.currentThread().getName());

        }
        zos.close();
        fos.close();
    }
}