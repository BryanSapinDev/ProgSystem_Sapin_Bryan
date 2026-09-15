public class Gradient {
    public static void main(String[] args) {
        Image img = new Image(200, 100);

        // Génération du dégradé de bleu
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                double bleu = ((double)x/200)*255; // quel calcul ?
                img.setPixel(x, y, 0, 0, (int)bleu);
            }
        }
		
		//TODO : Comprendre pourquoi succès mais pas de fichier
        try {
            img.save_txt("gradient.ppm");
            System.out.println("Dégradé créé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du dégradé : " + e.getMessage());
        }
    }
}