public class TestSemaphore {

	public static void main (String[] args) {
		Thread[] tabTh = new Thread[7];
		ProducteurConsommateur pc = new ProducteurConsommateur();
		for (int i = 1; i <= 5; i++) {
            tabTh[i-1] = new Thread(pc.new Producteur(i));
			tabTh[i-1].start();
        }

        for (int i = 1; i <= 2; i++) {
            tabTh[i+4] = new Thread(pc.new Consommateur(i));
			tabTh[i+4].start();
        }
	}
}