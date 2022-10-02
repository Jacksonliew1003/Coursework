package JH_ArraySortingAlgorithm;

import java.util.Arrays;

public class MainProgram {
    public static void main(String[] args) {
        int MAX_NUMBER = 20;

        int [] unsortedArray = GenerateArray.getInstance().getRandomNumberArray(MAX_NUMBER);
        System.out.println(Arrays.toString(unsortedArray));
    }
}
