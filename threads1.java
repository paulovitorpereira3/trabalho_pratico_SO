public class threads1 {

	public static void main(String[] args) {
		new Thread(t1).start();
		new Thread(t2).start();

	}
	
	private static Runnable t1 = new Runnable() {
		public void run() {
			for(int i=1; i<=500; i++) {
				System.out.println("Thread 1: " + i);
			}
		}
	};
	
	private static Runnable t2 = new Runnable() {
		public void run() {
			for(int j=500; j>=1; j--) {
				System.out.println("Thread 2: " + j);
			}
		}
	};

}
