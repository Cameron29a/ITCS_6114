import java.util.*;

class project_1 {
    //**************************************************************************
    // Random Array Generator
    //**************************************************************************
    public static int[] randomArray(int p) {
        Random number = new Random();
        int[] arrayrd = new int[p];
        for(int q = 0; q < arrayrd.length; q++)
            arrayrd[q] = number.nextInt();
        
        return arrayrd;
    }
    
    //**************************************************************************
    // Reverse Array
    //**************************************************************************
    static void reverseArray(int arr[]) {
        int temp;
        int left = 0;
        int right = arr.length-1;
           
        while (left < right) {
            temp = arr[left]; 
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        } 
    }   
    
    //**************************************************************************
    // Print Array
    //**************************************************************************
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    //**************************************************************************
    // Print 2D Array
    //**************************************************************************
    static void print2dArray(long arr[][]) {
        int rows = arr.length;
        int columns = arr[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }
    
    //**************************************************************************
    // Average Array
    //**************************************************************************
    static long arrayAvg(long arr[]) {
        long total = 0;
        
        for(int i=0; i < arr.length; i++){
        	total = total + arr[i];
        }
        long avg = total / arr.length;
        
        return avg;
    }
    
    //**************************************************************************
    // Print Averages
    //**************************************************************************
    static void printAvgs(long arr[], int setSizes[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print("Sample size: " + setSizes[i] + ":");
            System.out.println(" " + arr[i] + " s");
        }
        System.out.println();
    }

    //**************************************************************************
    // Insertion Sort
    //**************************************************************************
    static void insertionSort(int arr[]) {
        int n = arr.length;
        
        for (int j = 1; j < n; j++) {
            int key = arr[j];
            int i = j - 1;
                
            while (i > 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i = i - 1;
            }
            arr[i + 1] = key;
        }
    }

    //**************************************************************************
    // Merge Sort
    //************************************************************************** 
    static void mergeSort(int S[]) {
        int p = S.length;
            
        if (p > 1) {
            int q = p/2;
            int S1[] = Arrays.copyOfRange(S, 0, q);
            int S2[] = Arrays.copyOfRange(S, q, p);
            mergeSort(S1);
            mergeSort(S2);
            merge(S, S1, S2);
        }
    }
    
    static int[] merge(int S[], int S1[], int S2[]) {
        int l = S1.length;
        int r = S2.length;
            
        int i = 0, j = 0, k = 0;
        while ((j < l) && (k < r)) {
            if (S1[j] <= S2[k]) {
                S[i] = S1[j];
                i++;
                j++;
            } else {
                S[i] = S2[k];
                i++;
                k++;
            }
        }
        while(j < l)  {
            S[i] = S1[j];
            i++;
            j++;
        }
        while(k < r) {
            S[i] = S2[k];
            i++;
            k++;
        }
        
        return S;
    }
    
    //**************************************************************************
    // Heap Sort
    //************************************************************************** 
    static void heapSort(int A[]){
        int n = A.length;
        
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(A, n, i);
        
        for (int i = n - 1; i > 0; i--) {
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            
            heapify(A, i, 0);
        }
    }

    static void heapify(int A[], int n, int i) {
        int j = i;
        int l = 2 * i; 
        int r = 2 * i + 1;
        
        if (l < n && A[l] > A[j])
            j = l;
        
        if (r < n && A[r] > A[j])
            j = r;
        
        if (j != i) {
            int swap = A[i];
            A[i] = A[j];
            A[j] = swap;
        
            heapify(A, n, j);
        }
    }

    //**************************************************************************
    // Quick Sort
    //************************************************************************** 
    static void quickSort(int[] A, int left, int right) {
        
        if (left < right) {
            int pivot = partition(A, left, right);
            
            quickSort(A, left, pivot - 1);
            quickSort(A, pivot + 1, right);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    static int partition(int[] A, int left, int right) {
        int pivot = A[right];
        int i = (left - 1);
        
        for (int j = left; j <= right - 1; j++) {
            if (A[j] < pivot) {
                i++;
                swap(A, i, j);
            }
        }
        
        swap(A, i + 1, right);
        
        return (i + 1);
    }

    //**************************************************************************
    // Modified Quick Sort
    //**************************************************************************
    static void modQuickSort(int A[], int left, int right) {
        
        if (left < right) {
            int pivot = modPartition(A, left, right);
            
            modQuickSort(A, left, pivot - 1);
            modQuickSort(A, pivot + 1, right);
        }
    }
    
    static int modPartition(int[] A, int left, int right) {
        int size = A.length;
        int pivNum = size/2;
        int pivot = A[pivNum];
        int i = (left - 1);
        
        for (int j = left; j <= right - 1; j++) {
            if (A[j] < pivot) {
                i++;
                swap(A, i, j);
            }
        }
        
        swap(A, i + 1, right);
        
        return (i + 1);
    }
    

    //******************************************************************************************************
    // Driver Code
    //******************************************************************************************************
    public static void main(String args[]) {
        
        int[] setSizes = {100, 2000, 5001, 7500, 10000, 15000};
        int s = setSizes.length;        //The number of data sets sizes that will be tested
        int t = 10;                   //The number of data sets of each data set size that will be tested
        
        long[][] insertionSortResults = new long [s][t];
        long[][] mergeSortResults = new long [s][t];
        long[][] heapSortResults = new long [s][t];
        long[][] quickSortResults = new long [s][t];
        long[][] modQuickSortResults = new long [s][t];
        
        long[] insertionAvg = new long[s];
        long[] mergeAvg = new long[s];
        long[] heapAvg = new long[s];
        long[] quickAvg = new long[s];
        long[] modQuickAvg = new long[s];
        
        for(int j = 0; j < t; j++) {                                //Loop iterates through all sort methods and data set sizes to get average
            for(int i = 0; i < s; i++) {                            //Loop runs all sort methods on the same data set before moving to the next data set
                int[] arrayrd = randomArray(setSizes[i]);           //Creates Random Array the size of the current data set
                
                int[] temp = arrayrd;
                long strttest = System.currentTimeMillis();         //Start Timing the Sort
                insertionSort(temp);                                //Array Sorted Using Insertion Sort
                long endtest = System.currentTimeMillis();          //End Timing the Sort
                insertionSortResults[i][j] = endtest - strttest;    //Calculate Time to Execute
                
                temp = arrayrd;
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                mergeSort(temp);                                    //Array Sorted Using Merge Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                mergeSortResults[i][j] = endtest - strttest;        //Calculate Time to Execute
                
                temp = arrayrd;
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                heapSort(temp);                                     //Array Sorted Using Heap Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                heapSortResults[i][j] = endtest - strttest;         //Calculate Time to Execute
                
                temp = arrayrd;
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                quickSort(temp, 0, temp.length-1);                  //Array Sorted Using Quick Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                quickSortResults[i][j] = endtest - strttest;        //Calculate Time to Execute
                
                temp = arrayrd;
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                modQuickSort(temp, 0, temp.length-1);               //Array Sorted Using Modified Quick Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                modQuickSortResults[i][j] = endtest - strttest;     //Calculate Time to Execute
            }
        }
        
        for(int k = 0; k < s; k++) {
            insertionAvg[k] = arrayAvg(insertionSortResults[k]);
            mergeAvg[k] = arrayAvg(mergeSortResults[k]);
            heapAvg[k] = arrayAvg(heapSortResults[k]);
            quickAvg[k] = arrayAvg(quickSortResults[k]);
            modQuickAvg[k] = arrayAvg(modQuickSortResults[k]);
        }      
        
        System.out.println("Insertion Sort Averages:");
        printAvgs(insertionAvg, setSizes);
        System.out.println("Merge Sort Averages:");
        printAvgs(mergeAvg, setSizes);
        System.out.println("Heap Sort Averages:");
        printAvgs(heapAvg, setSizes);
        System.out.println("Quick Sort Averages:");
        printAvgs(quickAvg, setSizes);
        System.out.println("Modified Quick Sort Averages:");
        printAvgs(modQuickAvg, setSizes);
        
        
        // Special Case 1 - Array Already Sorted
        for(int j = 0; j < t; j++) {                                //Loop iterates through all sort methods and data set sizes to get average
            for(int i = 0; i < s; i++) {                            //Loop runs all sort methods on the same data set before moving to the next data set
                int [] arrSorted = new int[setSizes[i]];            //Create Presorted Array the size of the current data set
                for (int k=0; k < setSizes[i]; ++k)
                    arrSorted[k] = k * (int)Math.floor(Math.random() * 10); //Array increments by a random number
                
                int[] temp = arrSorted;                             //Write test array to temp to keep original order
                long strttest = System.currentTimeMillis();         //Start Timing the Sort
                insertionSort(temp);                                //Array Sorted Using Insertion Sort
                long endtest = System.currentTimeMillis();          //End Timing the Sort
                insertionSortResults[i][j] = endtest - strttest;    //Calculate Time to Execute
                
                temp = arrSorted;                                   //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                mergeSort(temp);                                    //Array Sorted Using Merge Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                mergeSortResults[i][j] = endtest - strttest;        //Calculate Time to Execute
                
                temp = arrSorted;                                   //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                heapSort(temp);                                     //Array Sorted Using Heap Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                heapSortResults[i][j] = endtest - strttest;         //Calculate Time to Execute
                
                temp = arrSorted;                                   //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                quickSort(temp, 0, temp.length-1);                  //Array Sorted Using Quick Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                quickSortResults[i][j] = endtest - strttest;        //Calculate Time to Execute
                
                temp = arrSorted;                                   //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                modQuickSort(temp, 0, temp.length-1);               //Array Sorted Using Modified Quick Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                modQuickSortResults[i][j] = endtest - strttest;     //Calculate Time to Execute
            }
        }
        
        for(int k = 0; k < s; k++) {
            insertionAvg[k] = arrayAvg(insertionSortResults[k]);
            mergeAvg[k] = arrayAvg(mergeSortResults[k]);
            heapAvg[k] = arrayAvg(heapSortResults[k]);
            quickAvg[k] = arrayAvg(quickSortResults[k]);
            modQuickAvg[k] = arrayAvg(modQuickSortResults[k]);
        }
        
        System.out.println("Special Case 1: Insertion Sort Averages:");
        printAvgs(insertionAvg, setSizes);
        System.out.println("Special Case 1: Merge Sort Averages:");
        printAvgs(mergeAvg, setSizes);
        System.out.println("Special Case 1: Heap Sort Averages:");
        printAvgs(heapAvg, setSizes);
        System.out.println("Special Case 1: Quick Sort Averages:");
        printAvgs(quickAvg, setSizes);
        System.out.println("Special Case 1: Modified Quick Sort Averages:");
        printAvgs(modQuickAvg, setSizes);
        
        
        // Special Case 2 - Array Sorted Backwards
        for(int j = 0; j < t; j++) {                                //Loop iterates through all sort methods and data set sizes to get average
            for(int i = 0; i < s; i++) {                            //Loop runs all sort methods on the same data set before moving to the next data set
                int [] rra=new int[setSizes[i]];                    //Create Presorted Array the size of the current data set
                for (int k=0; k < setSizes[i]; ++k)
                    rra[k] = k * (int)Math.floor(Math.random() * 10); //Array increments by a random number
                reverseArray(rra);                                  //Reverse Presorted Array
               
                int[] temp = rra;                                   //Write test array to temp to keep original order
                long strttest = System.currentTimeMillis();         //Start Timing the Sort
                insertionSort(temp);                                //Array Sorted Using Insertion Sort
                long endtest = System.currentTimeMillis();          //End Timing the Sort
                insertionSortResults[i][j] = endtest - strttest;    //Calculate Time to Execute
                
                temp = rra;                                         //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                mergeSort(temp);                                    //Array Sorted Using Merge Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                mergeSortResults[i][j] = endtest - strttest;        //Calculate Time to Execute
                
                temp = rra;                                         //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                heapSort(temp);                                     //Array Sorted Using Heap Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                heapSortResults[i][j] = endtest - strttest;         //Calculate Time to Execute
                
                temp = rra;                                         //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                quickSort(temp, 0, temp.length-1);                  //Array Sorted Using Quick Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                quickSortResults[i][j] = endtest - strttest;        //Calculate Time to Execute
                
                temp = rra;                                         //Write test array to temp to keep original order
                strttest = System.currentTimeMillis();              //Start Timing the Sort
                modQuickSort(temp, 0, temp.length-1);               //Array Sorted Using Modified Quick Sort
                endtest = System.currentTimeMillis();               //End Timing the Sort
                modQuickSortResults[i][j] = endtest - strttest;     //Calculate Time to Execute
            }
        }
        
        for(int k = 0; k < s; k++) {
            insertionAvg[k] = arrayAvg(insertionSortResults[k]);
            mergeAvg[k] = arrayAvg(mergeSortResults[k]);
            heapAvg[k] = arrayAvg(heapSortResults[k]);
            quickAvg[k] = arrayAvg(quickSortResults[k]);
            modQuickAvg[k] = arrayAvg(modQuickSortResults[k]);
        }      
        
        System.out.println("Special Case 2: Insertion Sort Averages:");
        printAvgs(insertionAvg, setSizes);
        System.out.println("Special Case 2: Merge Sort Averages:");
        printAvgs(mergeAvg, setSizes);
        System.out.println("Special Case 2: Heap Sort Averages:");
        printAvgs(heapAvg, setSizes);
        System.out.println("Special Case 2: Quick Sort Averages:");
        printAvgs(quickAvg, setSizes);
        System.out.println("Special Case 2: Modified Quick Sort Averages:");
        printAvgs(modQuickAvg, setSizes);
        
    }
}
