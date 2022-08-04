import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {		
		
		
		SourceMessage srcMes = new SourceMessage();
		Transmitter trans = new Transmitter();
		
		RecievedMessage recMes = new RecievedMessage();
		Reciever rec = new Reciever();
		
		srcMes.LoadFile("C:\\Users\\kevin\\Pictures\\testTekst.txt");
		
		String encodedBits = trans.Encode(srcMes.getBits(), 0);
		Channel chan;
		chan = new Channel(10);
		String recievedBits = chan.SendThrough(encodedBits);
		
		String decodedBits =  rec.Decode(recievedBits, 0);
		
		
		
		System.out.println("decoded: " +( chan.counter - getHD(srcMes.getBits(), decodedBits)));
		System.out.println("Hamming: " +( getHD(srcMes.getBits(), decodedBits)));
		
		recMes.setBits(decodedBits);
		
		recMes.WriteFile("C:\\Users\\kevin\\Pictures\\testTekstOutput.txt");
		
		
		
	}	
		
	public static int log2(int N)
    {	
        int result = (int)(Math.log(N) / Math.log(2));
  
        return result;
    }
	
	
	public static int getHD(String d1, String d2) {
		int distance = 0;
		if (d1 == null || d2 == null) {
		  System.out.println("Either String is null");
		  System.exit(0); // or throw a RuntimeException here
		}

		d1 = d1.toUpperCase();
		d2 = d2.toUpperCase();

		if (d1.length() != d2.length()) {
		  System.out.println("The string are not equal in length.");
		  System.exit(0); //or throw a RuntimeException here
		}

		for (int i = 0; i < d1.length(); i++) {
		  if (d1.charAt(i) != d2.charAt(i))
		    distance++;
		}

		return distance;
		}
	
	public static boolean isPowOf2(int n) {
		 while(n%2==0){
			 n=n/2;
		 }
		 if(n==1){
		 return true;
		 }
		
		
		return false;
	}
	
	public static String toBitStringOfLength(int n, int l) {
		StringBuffer sb = new StringBuffer(Integer.toBinaryString(n));
		int count = sb.length();
		for(int i = 0; i < l-count; i++) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}
	
	public static char Flip (char c) {
		if (c == '1') return '0';
		
		return '1';
	}
	
	public static String xor (ArrayList<String> arr) {
		StringBuffer result = new StringBuffer(arr.get(0));
		
		int c;
		
		for(int i = 0; i<result.length(); i++){
			c = 0;
			for(int j = 0; j<arr.size(); j++) {
				if(arr.get(j).charAt(i) == '1' ) c++;
			}
			result.setCharAt(i, (c % 2 == 0)? '0' : '1' );
		}
		
		return result.toString(); 
	}

}
