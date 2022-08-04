import java.util.ArrayList;

public class Reciever {
	
	
	public String Decode(String bitsString, int mode){
		String bitsDecoded = "";
		switch(mode) {
			case 0: //hamming
				bitsDecoded = DecodeHamming(bitsString, 8);
				break;
			case 1:
				bitsDecoded = bitsString;
				break;
			case 2:
				break;

		}

		
		return bitsDecoded;
	}
	
	
	
	
	String DecodeHamming(String bitsString, int parityBits) {
		int counter = 0;
		int n = (int)Math.pow(2, parityBits);
		char c;
		StringBuilder result = new StringBuilder();
		StringBuffer chunk;
		StringBuffer xored;
		ArrayList<String> set = new ArrayList<String>();
		
		for(int i = 0; i < (int)(bitsString.length()/n); i++) {  //does not include the last ones
			
			set = new ArrayList<String>();
			chunk = new StringBuffer(bitsString.substring(i*n, i*n+n)); 

			for(int j = 1; j < n; j++) { //add every position that has a 1
				if(chunk.charAt(j) == '1') set.add(Main.toBitStringOfLength(j, parityBits) );
			}
			
			set.add(Main.toBitStringOfLength(0, parityBits)); //ensure at least one neutral "0000" is there.
			
			xored = new StringBuffer(Main.xor(set)); //find xor
			
			//Change the flipped bit:
			
			int pos = Integer.parseInt(xored.toString(), 2);
			
			if(pos!= 0) {
				c =  (set.size() % 2 == 0)? '0' : '1';
				//if(chunk.charAt(0) == c) System.out.println("2 or more errors or");
				
				chunk.setCharAt(pos, Main.Flip(chunk.charAt(pos)));

				counter++;
			}
			
			//remove all redundancy
			for(int j = parityBits-1; j >= 0; j--) {
				chunk.deleteCharAt((int)Math.pow(2, j));
			}

			chunk.deleteCharAt(0);
			
			result.append(chunk);

		}
		
		
		String last = bitsString.substring(bitsString.length() - bitsString.length()%n);
		result.append(last);
		
		
		
		return result.toString(); 
	}
	
}
