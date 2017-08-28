import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class generateFiles extends Thread {

	public  int startindex;
	public  int endindex;
	public static  int filecount = 10000;
	public  int Integercount = 100000;
	public  String filename = "";
	public  String filepath = "D:/Pal/threadExample/";
	static int interval = filecount / 10;
	public  int id;

	public generateFiles(int id) {
		this.id = id;
		this.startindex = id * interval;
		this.endindex = (id + 1) * interval;
		System.out.println("Initializing thread: " + this.id);
	}
	
	 public void run() {
		BufferedWriter output = null;
		Random r = new Random();
		int randomValue = Integer.MIN_VALUE;
		String line = "";

		System.out.println("Running thread " + this.id);
		try {
			int readingposition = this.startindex;
			for (readingposition = this.startindex; readingposition < this.endindex; readingposition++) {

				this.filename = filepath + "inputfile_" + readingposition
						+ ".txt";
				// System.out.println("creating file in thread " + this.id +
				// ": " + this.filename);

				File file = new File(this.filename);

				output = new BufferedWriter(new FileWriter(
						file.getAbsoluteFile()));

				for (int count = 0; count <= Integercount; count++) {
					randomValue = Integer.MIN_VALUE
							+ (Integer.MAX_VALUE - Integer.MIN_VALUE)
							* r.nextInt();
					line = Integer.toString(randomValue) + ",";
					output.append(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
