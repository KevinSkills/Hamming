import java.util.Random;

public class Channel {
	private float noiseLevel; //0 - 1000
	public int counter;
	
	public Channel(float noiseLevel) {
		this.noiseLevel = noiseLevel;
	}
	
	
	public String SendThrough(String s) {
		counter = 0;
		Random rand = new Random();
		StringBuffer sb = new StringBuffer(s);
		for(int i = 0; i < sb.length(); i++) {
			if(rand.nextInt(10000) < noiseLevel) {
				counter++;
				sb.setCharAt(i, Main.Flip(sb.charAt(i)));
			}
		}
		System.out.println("made " + counter + "errors");
		
		return sb.toString();
	}
	

	
	
	
	

}
