package edu.fgcu.djarena3577.fileencoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Encoder {

	/**
	 * Encodes a file using Base64 encoding.
	 * 
	 * @param file to encode
	 * @throws IOException a lot of stuff can go wrong, filenotfoundexception,
	 *                     lacking perms, etc
	 */
	public static void encodeFile(File file) throws IOException {
		if (!file.canRead() || !file.canWrite()) {
			System.err.println("File cannot read or write!");
			System.exit(-1);
		}

		byte[] fileBytes = fileToBytes(file);
		byte[] encoded = Base64.getEncoder().encode(fileBytes);
		File encFile = new File(file.getAbsolutePath() + ".fe");
		bytesToFile(encoded, encFile);
		file.delete();
	}

	/**
	 * Decodes a file using Base64 encoding.
	 * 
	 * @param file to encode
	 * @throws IOException a lot of stuff can go wrong, filenotfoundexception,
	 *                     lacking perms, etc
	 */
	public static void decodeFile(File file) throws IOException {
		if (!file.canRead() || !file.canWrite()) {
			System.err.println("File cannot read or write!");
			System.exit(-1);
		}

		byte[] fileBytes = fileToBytes(file);
		byte[] decoded = Base64.getDecoder().decode(fileBytes);
		String path = file.getAbsolutePath();
		if (path.endsWith(".fe"))
			path = path.substring(0, path.length() - 3);
		File decFile = new File(path);
		bytesToFile(decoded, decFile);
		if (!decFile.getAbsolutePath().equalsIgnoreCase(file.getAbsolutePath()))
			file.delete();
	}

	/**
	 * Converts a file to a byte array using file input streams.
	 * 
	 * @param file to convert
	 * @return the file as a byte[]
	 * @throws IOException man stuff can go very wrong here...
	 */
	private static byte[] fileToBytes(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		if (file.length() > Integer.MAX_VALUE) {
			System.err.println("File is too large!");
			System.exit(-1);
		}
		byte[] bytes = new byte[(int) file.length()];
		int index = 0;
		while (fis.available() != 0) {
			bytes[index++] = (byte) fis.read();
		}
		fis.close();
		return bytes;
	}

	/**
	 * Converts a byte array back into a file.
	 * 
	 * @param bytes to convert back to a file.
	 * @param file  target
	 * @throws IOException not AS much can go wrong here but FileOutputStream
	 *                     requires it.
	 */
	private static void bytesToFile(byte[] bytes, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.close();
	}
}
