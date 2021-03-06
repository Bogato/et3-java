package ups.utils;

import java.util.Arrays;

/** Array utils */
public class ArrayInt {
    /**
     * Create an array of integers from an array of String.
     * @param strs  Array of String
     * @return an array of integers parsed from <code>strs</code>. If no
     * integer can be parsed, the value at the current index is set to 0.
     */
    public static int[] create(String[] strs) {
        int [] intArray = new int[strs.length];

        for (int i = 0; i < strs.length; ++i) {
            try {
                intArray[i] = Integer.parseInt(strs[i]);
            } catch (NumberFormatException e) {
                // parseInt(String) can raise an exception, let's catch it !
                System.err.println("Non correct integer value: " + strs[i]);
                intArray[i] = 0; // Default value
                // Or we can return null immediately
                // return null;
            }
        }

        return intArray;
    }

    public static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Return the maximum value of <code>array</code>.
     * @param array array of integers.
     * @return maximum value of <code>array</code> when it is not empty.
     * Otherwise, return <code>Integer.MIN_VALUE</code>.
     */
    public static int max(int[] array) {
        int max = Integer.MIN_VALUE;

        for (int current : array)
            max = Math.max(current, max);

        return max;
    }

    /**
     * Swap the minimum value of <code>a1</code> with the maximum value of
     * <code>a2</code>.
     */
    public static boolean changeMinMax(int[] a1, int[] a2) {
        if (a1.length == 0 || a2.length == 0) return false;

        // Get the index of the minimum value
        int minIndex = 0;
        int minA1 = a1[0];
        for (int i = 1; i < a1.length; ++i) {
            if (a1[i] < minA1) {
                minA1 = a1[i];
                minIndex = i;
            }
        }

        // Get the index of the maximum value
        int maxIndex = 0;
        int maxA2 = a2[0];
        for (int i = 1; i < a2.length; ++i) {
            if (a2[i] > maxA2) {
                maxA2 = a2[i];
                maxIndex = i;
            }
        }

        if (maxA2 == minA1) return false; // Equal, do not swap

        a1[minIndex] = maxA2;
        a2[maxIndex] = minA1;

        return true;
    }

    public static int sum(int ... integers) {
        int sum = 0;
        for (int i : integers) sum += i;
        return sum;
    }

    public static void main(String [] args) {
        int [] array = ArrayInt.create(args);
        int [] array2 = { 2, 4, 6, 8 };

        ArrayInt.print(array);
        System.out.println("max: " + ArrayInt.max(array));
        System.out.println("sum: " + ArrayInt.sum(array));

        System.out.println("Change? " + changeMinMax(array, array2));
        print(array);
        print(array2);
    }
}
