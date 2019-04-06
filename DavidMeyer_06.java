/**
 * DavidMeyer_6_01
 *    @author: David Meyer
 *    Program development environment (macOS High Sierra, MacBook pro, IntelliJ)
 *
 *    Raggiamuffin - Word used to describe someone wearing dirty scruffy
 *    clothing, often used when describing children and animals.
 *
 *     “It takes no more time to see the good side of life than to see the bad.”
 *      —Jimmy Buffett (1946 - current)
 *
 *    @Version 1
 *    Due: November 5, 2018
 *    Main(DavidMeyer_03) uses the classes object classes Student and GradeItem
 *     to run tests to create a list of information that is contained within
 *     a .txt file.  Reads the information, puts students together and then
 *     creates a new .txt file and puts all information into that file.
 */

import java.io.*;
import java.util.Random;

public class DavidMeyer_06 {
    private static int[] randomInput = new int[100];
    private static String INPUT_FILENAME = "hw6input.txt";
    private static String lineText = null;


    private static PrintWriter pw2;

    static {
        try {
            pw2 = new PrintWriter ("hw6.output01.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Output of file 1 failed. " + e);
        }
    }

    private static PrintWriter pw3;

    static {
        try {
            pw3 = new PrintWriter ("hw6.output02.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Output of file 2 failed. " + e);
        }
    }




    public static void main(String[] args) {

        //Create new random file using randomNumberGenerator method.
        try {
            randomNumberGenerator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(INPUT_FILENAME);

        try {
            //Scanner scFile = new Scanner(file);//Scan file
            BufferedReader lineReader = new BufferedReader(new FileReader(INPUT_FILENAME));

            // While file has next line, read and send to array.
            int j =0;
            while ((lineText = lineReader.readLine())!= null) {//Read each line
                randomInput[j] = Integer.parseInt(lineText);
                j++;

            }

            //Print original numbers order to console.
            for(int i=0; i < randomInput.length; i++){ //print to make sure
                // that the above works.
                System.err.println(randomInput[i]);
            }

            //Shell sort and print out.
            int[] finalInt = shellSort(randomInput);  //put shell sorted into
            // another array to print.
            for(int i=0; i < finalInt.length; i++) {
                pw2.println(finalInt[i]);
            }
            pw2.close();


            //Quick sort and print out.
            int[] finalQuic = quickSort(randomInput);
            for(int i=0; i < finalQuic.length; i++){
                pw3.println(finalQuic[i]);
            }
            pw3.close();


            //scFile.close();
            lineReader.close();
        } catch (IOException e) {

            System.out.println("File Read Error: " + e);
        }

    }

    //****************************************************************

    /**
     * Given method created by Author: Dr. Salim Lakhani
     *    Modified by: Mr. David Kramer both from MSU Denver.  Creates a file
     *    with 100 random numbers.
     * @throws FileNotFoundException
     */

    public static void randomNumberGenerator() throws FileNotFoundException {
        final int NUM_NUMBERS = 100;     // # of random #s to generate
        final int MAX_NUMBER  = 10000;   // Maximum number to generate, + 1
        Random randomNumbers = new Random();
        File file = new File ("hw6input.txt");
        PrintWriter pw = new PrintWriter (file);
        //pw exception handled by exception method.

        for (int i = 0; i < NUM_NUMBERS; i++) {
            pw.println (randomNumbers.nextInt(MAX_NUMBER));
        }
        pw.close();
    }

    //****************************************************************

    /**
     * Shell sort algorithm.
     *
     * @param shellSort
     * @return array
     */

    public static int[] shellSort(int[ ] shellSort )
    {
        int inner, outer;
        int temp;
        int pivot=1;

        while(pivot <= shellSort.length / 3){
            pivot = pivot * 3 + 1;
        }
        while(pivot > 0){
            for(outer = pivot; outer < shellSort.length; outer++){
                temp = shellSort[outer];
                inner = outer;
                while(inner > pivot-1 && shellSort[inner - pivot] >= temp){
                    shellSort[inner] = shellSort[inner - pivot];
                    inner -= pivot;
                }
                shellSort[inner] = temp;
            }
            pivot = (pivot-1)/3;
        }
        return shellSort;
    }

    /**
     * Divides array from pivot, left less than pivot.  Right more than pivot.
     *
     *  @param sorting to be partitioned
     * @param left bottom of array
     * @param right top of array
     * @return     the partition index.
     */

    //****************************************************************

    public static int partition(int[] sorting, int left, int right){

        int pivot = sorting[left];

        while(left <= right){
            //Bottom up, which number is greater than pivot.
            while(sorting[left] < pivot){
                left++;
            }

            //Top down, which number less than pivot.
            while(sorting[right] > pivot){
                right--;
            }

            // Swap the values with general swapping method set.
            if(left <= right){
                int temp = sorting[left];
                sorting[left] = sorting[right];
                sorting[right] = temp;

                left++;
                right--;
            }
        }
        return left;
    }

    //****************************************************************

    /**
     * Recursivly do quicksort logic.
     * @param sorting   input array
     * @param first     start index of array
     * @param last      end index of array
     */

    public static int[] recursiveQuickSort(int[] sorting, int first,
                                          int last){
        int index = partition(sorting, first, last);

        //Continuously call quicksort with left part of the partitioned
        if(first < index-1){
            recursiveQuickSort(sorting, first, last-1);
        }

        //Continuously call for right side
        if(last > index){
            recursiveQuickSort(sorting, index, last);
        }

        //Finally nothing else to sort
        return sorting;
    }

    //****************************************************************

    /**
     * Uses previous methods to quicksort the array sorting.
     * @param sorting
     */

    public static int[] quickSort(int[] sorting){
        recursiveQuickSort(sorting,0,sorting.length-1);
        return sorting;
    }
}
