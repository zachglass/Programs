import java.util.Random;

public final class Myguess {
	public static final void main( String[] args ) {
		log("Generating 10 random integers in range 0-99");

		Random randomGenerator = new Random();

		for (int i = 1; i <= 10; ++i){
		int randomInt = randomGenerator.nextInt(100);
		log("Generated : " + randomInt);
	   }

	  log("Done!");
	}
	private static void log(String aMessage){
	 System.out.println(aMessage);

	}
}
