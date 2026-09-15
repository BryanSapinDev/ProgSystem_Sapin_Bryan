import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Image {
    private int width;
    private int height;
    // pixels[y][x][0=R,1=G,2=B]
    private int[][][] pixels; // pixels[y][x][0=R,1=G,2=B]

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /**
     * Constructeur : initialise une image vide.
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[height][width][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    /**
     * Sauvegarde l'image au format texte PPM (P3)
     */
    public void save_txt(String filename) throws IOException {
        try {
            FileWriter writer = new FileWriter(filename);

            writer.write("P3\n");
            writer.write(width + " " + height + "\n");
            writer.write("255\n");// Écriture de la valeur maximal
            // Écriture des pixels
			for (int hauteur = 0; hauteur < height; hauteur++) {
				for (int largeur = 0; largeur < width; largeur++) {
					writer.write(pixels[hauteur][largeur][0] + " "); //R
					writer.write(pixels[hauteur][largeur][1] + " "); //G
					writer.write(pixels[hauteur][largeur][2] + " "); //B
				}
			}
			writer.write("\n");

            writer.close(); // Fermeture du fichier

            System.out.println("Image PPM créée avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
	
	/**
     * Sauvegarde l'image au format binaire PPM (P6)
     */
    public void save_bin(String filename) throws IOException {
        try {
            FileOutputStream writer = new FileOutputStream(filename);
			String header = "P6\n" + width + " " + height + "\n255\n";
            writer.write(header.getBytes());
			
			byte[] rgb = new byte[width*height*3];
			for (int hauteur = 0; hauteur < height; hauteur++) {
				for (int largeur = 0; largeur < width; largeur++) {
					rgb[(hauteur*width+largeur)*3] = (byte)pixels[hauteur][largeur][0];
					rgb[(hauteur*width+largeur)*3+1] = (byte)pixels[hauteur][largeur][1];
					rgb[(hauteur*width+largeur)*3+2] = (byte)pixels[hauteur][largeur][2];
				}
			}
			
            writer.write(rgb);

            writer.close(); // Fermeture du fichier

            System.out.println("Image PPM binaire créée avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
}