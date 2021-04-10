/**
 ** Program Name: MergeSort
 ** Author: Zulhelmi bin Mohamad
 ** Date: April 9th, 2021
 ** Course: CPSC 1150
 ** Compiler:JDK 15.0.1
 **/

import java.util.Arrays;

public class MergeSort{
    public static void main(String[] args) {
        int [] list1 = genArray(20);
        printArray("The array before merge sort: ", list1);
        mergeSort(list1);
        printArray("The array After merge sort: ", list1);
    }
    public static void mergeSort(int [] list){
        if (list.length > 1 ){
            int mid = list.length / 2 ;
            int [] firstHalf = new int[mid];
            copyArray(list, firstHalf, 0, mid);
            mergeSort(firstHalf); //sorts firstHalf in ascending order by recursion

            int [] secondHalf = new int[list.length - mid];
            copyArray(list, secondHalf, mid, list.length);
            mergeSort(secondHalf); //sorts secondHalf in ascending order by recursion
            // merges to lists ie. firstHalf and second half into a sorted list
            merge(firstHalf, secondHalf, list);
        }   //base case
    }

    // generates and returns an array of random numbers of the given size
    public static int[] genArray(int size){
        int [] list = new int [size];
        for (int i = 0; i< size; ++i)
            list[i] = (int)(Math.random() * 100 );
        return list;
    }

    // prints an array followed by the header
    public static void printArray(String header, int [] arr) {
        System.out.println(header);
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " " );
        System.out.println();
    }

    //copies from index start to index end of sourcels to destLs. End is exclusive
    public static void copyArray(int [] sourceLs, int[] destLs, int start, int end){
        for (int i = start; i< end ; i++)
            destLs[i-start] = sourceLs[i];
    }

    public static int[] merge(int[] leftArray, int[] rightArray, int[] mergedArray){

        //Code below appends the second array to the first array
        for (int i = 0, l = 0, r = 0; i < mergedArray.length; i++){
            if (isIndexLast(leftArray, l)){
                mergedArray[i] = rightArray[r];
                r++;
                continue;
            }
            else if (isIndexLast(rightArray, r)){
                mergedArray[i] = leftArray[l];
                l++;
                continue;
            }

            else{
                if (leftArray[l] <= rightArray[r]){
                    mergedArray[i] = leftArray[l];
                    l++;
                }
                else if (leftArray[l] > rightArray[r]){
                    mergedArray[i] = rightArray[r];
                    r++;
                }
            }
        }
        return mergedArray;
    }

    public static boolean isIndexLast (int[] array, int index){
        if (index >= array.length)
            return true;
        else
        return false;    
    }
}