import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Quicksort {
	
	Integer[] elements = null;
	private String filepath = "D:/Pal/sortedFiles/";
	private String filename = "";
	BufferedWriter output = null;

	public Quicksort(String filename) {
		this.filename = filepath + filename;
		System.out.println("Sorting filename: " + this.filename);
	}

	 public Integer[] sortFile(Integer[] arr, Integer low, Integer high)
			throws Exception {

		try {
			if (low < high) {
			
				int pi = partition(arr, low, high);

			
				sortFile(arr, low, pi - 1);
				sortFile(arr, pi + 1, high);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return arr;

	}

	private int partition(Integer[] arr, Integer low, Integer high) {

		int pivot = arr[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;

	}
	
	/*
	 A utility function to print array of size n 
    static void printArray(Integer[] arr)
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
       
    }
 
    // Driver program
    public static void main(String args[])
    {
        Integer[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;
 
        Quicksort ob = new Quicksort("test");
        try {
			ob.sortFile(arr, 0, n-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        System.out.println("sorted array");
        printArray(arr);
    }
*/
}
