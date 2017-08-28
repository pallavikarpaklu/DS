import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class threadManagement extends Thread {

	public  int startindex;
	public  int endindex;
	public  int id;

	static int filecount = 10000;
	static int interval = filecount / 10;

	private BigInteger sum = BigInteger.ZERO;
	public static BigInteger totalsum = BigInteger.ZERO;
	public static BigInteger[] arr = new BigInteger[10];
	 
	public String filename = "";
	public static String filepath = "D:/Pal/threadExample/";
	
	public static String pattern ="-?\\d+";
	
	public Integer[] elements = null;
	Quicksort qs;
	BufferedReader reader;
	BufferedWriter output;
	
	threadManagement(int startindex) {
		this.id = startindex;
		this.startindex = id * interval;
		this.endindex = (id + 1) * interval;
		System.out.println("Initializing thread: " + this.id);
	}

	public void run() {

		try {
			System.out.println("Running thread :" +this.id);
			
			int readingposition = this.startindex;
			for ( readingposition = this.startindex; readingposition <  this.endindex; readingposition++) {

			
				this.filename = filepath + "inputfile_" + readingposition
						+ ".txt";
				//System.out.println("Executing thread of starting index: "+ readingposition);

				String[] values = null;
			

				FileReader fr = new FileReader(filename);
				reader = new BufferedReader(fr);

				String line;
				while ((line = reader.readLine()) != null) {
					values = line.split(",");
					elements = new Integer[values.length];
					for (int index = 0; index < values.length; index++)
					{	
						if(values[index].matches(pattern)){
							elements[index] = Integer.parseInt(values[index]);							
						}
					}

				}
				
				//call quicksort
				qs = new Quicksort("outputfile_" + readingposition + ".txt");
			    Integer[] result = qs.sortFile(elements,0, elements.length - 1);
			    
			    
			    File file = new File("D:/Pal/sortedFiles/outputfile_" + readingposition + ".txt");

				output = new BufferedWriter(new FileWriter(file));

				for (int i = 0; i < result.length; i++) {
					output.append(result[i] + ",");
				}
				
			}					
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				output.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}		
		}
	}

	public static void main(String[] args) throws InterruptedException {
	

		int threadcount = 10;
		threadManagement t[] = new threadManagement[10];
		
		try {
			for (int i = 0; i < threadcount; i++) {
				System.out.println("thread count: " + i);
				t[i] = new threadManagement(i);
				t[i].start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < threadcount; i++) {
			try {
				if(t[i].isAlive()) 	t[i].join(); 
			
				/*totalsum = totalsum.add(arr[i]);
				System.out.println("********************** TOTAL SUM IS : *******************"+ totalsum.toString()); */
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		

	}

}
