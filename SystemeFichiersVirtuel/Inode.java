public class Inode {

    private MemoryManager memoryManager;
    private int inodeNumber;

    public static final int INODE_SIZE = 128;
    public static final int DIRECT_POINTERS = 10;

    public Inode(
            MemoryManager memoryManager,
            int inodeNumber) {

        this.memoryManager = memoryManager;
        this.inodeNumber = inodeNumber;
    }

    public int getInodeOffset() {
        // TODO:
        // Calculer l'offset exact de l'inode.
        return MemoryManager.INODE_TABLE_OFFSET + inodeNumber * INODE_SIZE;
    }

    public int getFileType() {
        // TODO:
        // Lire le type à offset + 4.
        return Utils.readInt(memoryManager.getFilesystemMemory(), getInodeOffset() + 4);
    }

    public int getFileSize() {
        // TODO:
        // Lire la taille à offset + 8.
        return Utils.readInt(memoryManager.getFilesystemMemory(), getInodeOffset() + 8);
    }

    public int[] getDirectPointers() {

        byte[] memory =
                memoryManager.getFilesystemMemory();

        int[] pointers =
                new int[DIRECT_POINTERS];
		for (int indice = 0; indice < 10; indice++) {
			pointers[indice] = Utils.readInt(memory, getInodeOffset() + 28 + (indice * 4));
		}

        // TODO:
        // Lire les 10 pointeurs directs.

        return pointers;
    }
	
	public void writeToMemory(
								int fileType,
								int fileSize,
								long creationTime,
								long modificationTime,
								int[] directPointers,
								int indirectPointer,
								short permissions,
								int linkCount) {

		byte[] memory =
				memoryManager.getFilesystemMemory();

		int offset = getInodeOffset();

		// TODO:
		// 1. Numéro d'inode
		// 2. Type
		// 3. Taille
		// 4. Création
		// 5. Modification
		// 6. 10 pointeurs directs
		// 7. Pointeur indirect
		// 8. Permissions
		// 9. Nombre de liens
	}
}
