public class Utils {

    public static int writeInt(byte[] memory, int offset, int value) {
		for (int indice = 0; indice < 4; indice++){
			byte b = (byte)((value >> 8*(3 - indice) & 0xFF));
			//b = b & 0xFF;
			memory[offset+indice] = b;
		}
        return 4;
    }

    public static int readInt(byte[] memory, int offset) {
		int value = 0;
		for (int indice = 0; indice < 4 ; indice++){
			int entier = (int)((memory[offset+indice] & 0xFF) << (8*(3 - indice)));
			value |= entier;
		}
        return value;
    }

    public static int writeShort(byte[] memory, int offset, short value) {
		for (int indice = 0; indice < 2; indice++){
			byte b = (byte)((value >> 8*(1 - indice) & 0xFF));
			//b = b & 0xFF;
			memory[offset+indice] = b;
		}
        return 2;
    }

    public static short readShort(byte[] memory, int offset) {
        // TODO: Lire le short sur 2 octets.
		short value = 0;
		for (int indice = 0; indice < 2 ; indice++){
			int entier = (int)((memory[offset+indice] & 0xFF) << (8*(1 - indice)));
			value |= entier;
		}
        return value;
    }
	
	public static int writeLong(byte[] memory, int offset, long value) {
		for (int indice = 0; indice < 8; indice++){
			byte b = (byte)((value >> 8*(7 - indice) & 0xFF));
			//b = b & 0xFF;
			memory[offset+indice] = b;
		}
		return 8;
	}

	public static long readLong(byte[] memory, int offset) {
		long value = 0;
		for (int indice = 0; indice < 8 ; indice++){
			long entier = (long)(memory[offset+indice] & 0xFF) << (8*(7 - indice));
			value |= entier;
		}
        return value;
	}

	public static int writeString(
			byte[] memory,
			int offset,
			String str,
			int maxLength) {

		// TODO:
		// 1. Convertir la chaîne en octets.
		// 2. Copier les octets sans dépasser maxLength.
		// 3. Nettoyer le reste de la zone avec des zéros.
		byte[] strByte = str.getBytes();
		for (int i = 0; i < strByte.length; i++) {
			memory[offset+i] = strByte[i];
		}
		for (int i = strByte.length; i < maxLength; i++) {
			memory[offset+i] = 0x00;
		}

		return maxLength;
	}

	public static String readString(
			byte[] memory,
			int offset,
			int maxLength) {
		
		// TODO:
		// Lire jusqu'au premier octet nul
		// ou jusqu'à maxLength.
		String result = "";
		for (int i = 0;i < maxLength && memory[offset+i] != 0; i++) {
			result = result + String.valueOf((char)memory[offset+i]);
		}

		return result;
	}
	
	
	
	public static void main(String[] args) {
		byte[] mem = new byte[6];
		System.out.println(writeInt(mem, 0, 10));
		System.out.println(readInt(mem, 0));

		System.out.println(writeShort(mem, 3, (short)8));
		System.out.println(readShort(mem, 3));

	}
}