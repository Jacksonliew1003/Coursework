import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parallel_Code_Version2 {

    public static void main(String[] args) {


        File filePath = new File("src/Video_Folder");
        File filesList[] = filePath.listFiles();

        int numberOfThreads = 5;
        Thread[] threads = new Thread[numberOfThreads];

        final int filesPerThread = filesList.length/numberOfThreads;
        final int remaingFiles = filesList.length%numberOfThreads;

        for (int t = 0; t < numberOfThreads; t++) {
            int thread = t;
            threads[t] = new Thread() {
                @Override
                public void run() {
                    runThread(filesList, numberOfThreads, thread, filesPerThread, remaingFiles);
                }
            };
        }

        for (Thread t1 : threads){
            t1.start();
        }

        for (Thread t2 : threads){
            try{
                t2.join();
            }catch (InterruptedException ie){
                ie.getMessage();
            }
        }

    }

    private static void runThread(File[] filesList, int numberOfThreads, int thread, int filesPerThread, int remainingFiles) {
        List<File> inFiles = new ArrayList<>();
        for (int i = thread*filesPerThread; i < (thread+1)*filesPerThread; i++){
            inFiles.add(filesList[i]);

            if (thread == numberOfThreads -1 && remainingFiles >0) {
                for (int j=filesList.length - remainingFiles; j < filesList.length; j++) {
                    inFiles.add(filesList[j]);
                }
            }
        }
        for (File file: inFiles){
            System.out.println("Processing " + file.getName() + " in thread " + Thread.currentThread().getName());
        }
    }

}