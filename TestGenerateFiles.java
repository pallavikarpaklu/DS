
public class TestGenerateFiles {
	
	public static void main(String[] args) {
		System.out.println("Starting Main");
		
		int threadcount = 10;
		generateFiles[] gt  = new generateFiles[10];
		
		try {
			for (int i = 0; i < threadcount; i++) {
				System.out.println("thread count: " + i);
				gt[i] = new generateFiles(i);
				gt[i].start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		for (int i = 0; i < threadcount; i++) {
			try {
				if(gt[i].isAlive()) 	gt[i].join(); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

