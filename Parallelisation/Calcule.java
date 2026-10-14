public class Calcule extends Thread {
	private int valeur, multiplicateur, index, resultat;
	
	public Calcule(int valeur, int multiplicateur, int index) {
		this.valeur = valeur;
		this.multiplicateur = multiplicateur;
		this.index = index;
		resultat = 0;
	}
    
	@Override
	public void run() {
		// TODO: Implémenter le calcul intensif
		// Même logique que dans la boucle interne de CalculSequentiel
		for (int j = 0; j < multiplicateur; j++) {
            resultat += valeur * valeur + valeur;
        }
	}
	
	public int getResultat() {
		return resultat;
	}
}