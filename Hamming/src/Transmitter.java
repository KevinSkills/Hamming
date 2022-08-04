import java.util.ArrayList;
import java.util.List;

public class Transmitter {
	
	public String Encode(String bitsString, int mode){
		String bitsEncoded = "";
		switch(mode) {
			case 0: //hamming
				bitsEncoded = EncodeHamming(bitsString, 8);
				break;
			case 1:
				bitsEncoded = bitsString;
				break;
			case 2:
				break;

		}

		
		return bitsEncoded;
	}
	
	
	String EncodeHamming(String bitsString, int parityBits) {
		int k = (int)Math.pow(2, parityBits) - parityBits - 1;
		StringBuffer result = new StringBuffer();
		
		//varibles that change
		char c;
		int numberOfOnes;
		StringBuffer chunk;
		String xored;
		ArrayList<String> set = new ArrayList<String>();
		 
		for(int i = 0; i < (int)(bitsString.length()/k); i++) {  //a few bits are left not encoded
			set = new ArrayList<String>();
			chunk = new StringBuffer(bitsString.substring(i*k, i*k+k)); 
			chunk.insert(0, "0"); // insert the zero bit
			
			for (int j = 0; j < parityBits; j++) { // insert parity bits
				chunk.insert((int) Math.pow(2, j), "0");
			}
			for(int j = 1; j < k+parityBits+1; j++) { //add every position that has a 1
				if(chunk.charAt(j) == '1') set.add(Main.toBitStringOfLength(j, parityBits) );
			}
			
			
			set.add(Main.toBitStringOfLength(0, parityBits)); //ensure at least one neutral "0000" is there.
			
			xored = Main.xor(set); //find xor
			numberOfOnes = set.size();

			for (int j = 0; j < parityBits; j++) { // change parity bits
				c =  xored.charAt(xored.length()-1-j);
				if(c == '1') numberOfOnes++;
				chunk.setCharAt((int) Math.pow(2, j), c); //they are in reverse so - 1 - j
			}
			
			chunk.setCharAt(0, (numberOfOnes % 2 == 0)? '0' : '1'); //parity for DED
			
			result.append(chunk);

		}
		
		String last = bitsString.substring(bitsString.length() - bitsString.length()%k);
		result.append(last); // add the last bits ( not encoded)

		return result.toString(); 
	}
	
}
