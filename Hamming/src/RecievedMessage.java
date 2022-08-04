import java.io.FileOutputStream;

public class RecievedMessage {
	private String bits;
	
	
	public void setBits(String newBits) {
		this.bits = newBits;
	}
	
	public byte[] BitsStringToByteArray(String bitsString) {
		int rest = bitsString.length()%8;
		int bytesArrLength = (bitsString.length()+rest)/8;
		byte[] bytes = new byte[bytesArrLength];
		
		for (int i = 0; i < bytesArrLength; i++) {
			
			bytes[i] = (byte)(Integer.parseInt(bitsString.substring(i*8, i*8+8), 2)-128);
		}

		return bytes;
	}
	
	
	public void WriteFile(String filename) {
		byte[] b = BitsStringToByteArray(bits);
		System.out.println(b.length);
		
		
		
		try {
			FileOutputStream fileOutput = new FileOutputStream(filename);
			fileOutput.write(b);
			
			
			fileOutput.close();
			
		}
		catch (Exception e) {
		}
		
	}
}

