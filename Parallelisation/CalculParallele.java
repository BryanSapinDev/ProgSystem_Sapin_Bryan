public class CalculParallele {
    private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;

	public static void main(String[] args) {
		System.out.println("=== Calcul Parallele ===");

        long debut = System.nanoTime();
		Calcule[] threads = new Calcule[DONNEES.length];
		
		for (int indice = 0; indice < DONNEES.length; indice++) {
			threads[indice] = new Calcule(DONNEES[indice],MULTIPLICATEUR,indice);
			threads[indice].start();
		}
		long resultat = 0;
		for (int indice = 0; indice < threads.length; indice++) {
			try {
				threads[indice].join();
			} catch (Exception e) {
				
			}
			resultat += threads[indice].getResultat();
		}
		
		long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat total : " + resultat);
        System.out.println("Durée : " + duree + " ms");
		
	}
}