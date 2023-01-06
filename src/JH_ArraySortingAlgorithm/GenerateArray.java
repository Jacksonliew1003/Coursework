package JH_ArraySortingAlgorithm;

public class GenerateArray {

    public int[] getRandomNumberArray (int elementNumberInArray) {
        int[] array = new int[elementNumberInArray];

        for (int i = 0 ; i < array.length; i++) {
            array[i] = getRandomNumber(0, elementNumberInArray);
        }

        return array;
    }

    public int[] getDescendingArray (int elementNumberInArray) {
        int[] array = new int[elementNumberInArray];

        for (int i = 0 ; i < array.length; i++) {
            array[i] = elementNumberInArray - i;
        }

        return array;
    }

    public int getRandomNumber (int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    private static final GenerateArray instance;
    private GenerateArray() {}
    static {instance = new GenerateArray();}
    public static GenerateArray getInstance() {
        return instance;
    }
}
