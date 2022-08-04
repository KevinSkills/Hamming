
import java.io.FileInputStream;


public class SourceMessage {
	private String bits = "";

	
	
	
	
	
	
	
	public String getBits() {
		return this.bits;
	}
	
	public void setBits(String newBits) {
		this.bits = newBits;
	}
	
	
	String ByteArrayToBitString(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		
		for (byte b : bytes) {
			s.append(Integer.toBinaryString(b + 128));
		}
		return s.toString();
	}
	
	
	public void LoadFile(String filename) {
		byte[] b = new byte[0];
		
		try {
			FileInputStream fileInput = new FileInputStream(filename);
			
			b = fileInput.readAllBytes();
			
			fileInput.close();
			
		}
		catch (Exception e) {
		}
		
		this.bits = ByteArrayToBitString(b);
	}
		
	
}
