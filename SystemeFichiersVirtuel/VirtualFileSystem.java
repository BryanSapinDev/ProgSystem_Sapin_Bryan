import java.util.*;

public class VirtualFileSystem {

    private MemoryManager memoryManager;

    public VirtualFileSystem() {
        this.memoryManager =
                new MemoryManager();
    }

    private int allocateInode() {

        byte[] memory =
                memoryManager.getFilesystemMemory();

        // TODO:
        // Parcourir les inodes de 0 à MAX_INODES - 1.
        // Identifier le premier inode libre.
        // Retourner son numéro.
		int numInode = -1;
		
		for (int indice = 0; indice < MemoryManager.MAX_INODES && numInode == -1; indice++) {
			if (Utils.readInt(memory, MemoryManager.INODE_TABLE_OFFSET + 4 
									  + (indice * MemoryManager.INODE_SIZE)) == 0) {
				numInode = indice;
			}
		}

        return numInode;
    }

    public boolean createFile(
            String directory,
            String filename) {

        int inodeNum = allocateInode();

        if (inodeNum == -1) {
            return false;
        }

        // TODO:
        // Construire l'inode.
        // L'initialiser comme fichier vide.
		Inode inode = new Inode(memoryManager, inodeNum);
		inode.writeToMemory(1,0,0,0,new int[10],0,(short)0,0);

        return true;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }
	
	public boolean writeFile(
			int inodeNum,
			byte[] data) {

		int blocksNeeded =
				(data.length
				+ MemoryManager.BLOCK_SIZE - 1)
				/ MemoryManager.BLOCK_SIZE;

		if (blocksNeeded > Inode.DIRECT_POINTERS) {
			return false;
		}

		int[] blockPointers =
				new int[Inode.DIRECT_POINTERS];

		// TODO:
		// Allouer blocksNeeded blocs.
		int[] blockUsed = new int[blocksNeeded];
		for (int indice = 0; indice < blocksNeeded; indice++) {
			blockUsed[indice] = memoryManager.allocateBlock();
		}

		byte[] memory =
				memoryManager.getFilesystemMemory();

		int bytesRemaining =
				data.length;

		int dataSrcOffset = 0;

		// TODO:
		// Pour chaque bloc :
		// - calculer la quantité à copier ;
		// - récupérer le numéro du bloc ;
		// - calculer son offset physique ;
		// - copier les données.
		int quantiteACopier;
		int numBlock;
		int offsetPhysique;
		for (int indice = 0; indice < blocksNeeded; indice++) {
			quantiteACopier = bytesRemaining >= MemoryManager.BLOCK_SIZE ?
								MemoryManager.BLOCK_SIZE : bytesRemaining;
			numBlock = blockUsed[indice];
			offsetPhysique = numBlock * MemoryManager.BLOCK_SIZE;
			for (int aCopier = 0; aCopier < quantiteACopier; aCopier++) {
				memory[offsetPhysique + aCopier] = data[indice * 512 + aCopier];
				//Utils.writeInt(memory, offsetPhysique + aCopier * 4,
				//	Utils.readInt(data,indice * 512 + aCopier * 4));
			}
			bytesRemaining = bytesRemaining - quantiteACopier;
		}

		// TODO:
		// Mettre à jour l'inode.
		Inode inode = new Inode(memoryManager, inodeNum);
		inode.writeToMemory(1,data.length,0,0,blockUsed,0,(short)0,0);
		

		return true;
	}
	
	public byte[] readFile(int inodeNum) {

		Inode inode =
				new Inode(memoryManager, inodeNum);

		int fileSize =
				inode.getFileSize();

		if (fileSize == 0) {
			return new byte[0];
		}

		byte[] fileData =
				new byte[fileSize];

		byte[] memory =
				memoryManager.getFilesystemMemory();

		int[] blockPointers =
				inode.getDirectPointers();

		// TODO:
		// Parcourir les blocs utilisés.
		// Copier chaque fragment vers fileData.
		int bytesRemaining = fileSize;
		for (int indice = 0; indice < blockPointers.length; indice++) {
			for (int aCopier = 0; aCopier < 512 && bytesRemaining > 0; aCopier++) {
				fileData[indice*512 + aCopier] = memory[blockPointers[indice]*512 + aCopier];
				bytesRemaining--;
			}
		}

		return fileData;
	}
}