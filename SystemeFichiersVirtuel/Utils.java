public class Utils {

    public static int writeInt(byte[] memory, int offset, int value) {
        // TODO: Écrire les 4 octets de 'value' dans 'memory'
        // à partir de 'offset', en big-endian.
		for (int indice = 0; indice < 4; indice++){
			byte b = (byte)value >> 8*indice;
			b = b & 0xFF
			memory[offset+indice] = b
		}
        return 4;
    }

    public static int readInt(byte[] memory, int offset) {
        // TODO: Reconstituer le int sur 4 octets.
		int value;
		for (int indice = 3; indice >= 0 ; indice--){
			int entier = (int)memory[offset+indice] << 8*indice;
			value |= entier;
		}
        return 0;
    }

    public static int writeShort(byte[] memory, int offset, short value) {
        // TODO: Écrire les 2 octets de 'value'.
		for (int indice = 0; indice < 2; indice++){
			byte b = (byte)value >> 8*indice;
			b = b & 0xFF
			memory[offset+indice] = b
		}
        return 2;
    }

    public static short readShort(byte[] memory, int offset) {
        // TODO: Lire le short sur 2 octets.
		short value;
		for (int indice = 3; indice >= 0 ; indice--){
			short entier = (short)memory[offset+indice] << 8*indice;
			value |= entier;
		}
        return 0;
    }
}