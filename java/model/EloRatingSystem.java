package model;

public class EloRatingSystem {

	private static final int STARTING_RATING = 1500;
	private static final int K = 32;

	public static int getStartingRating() {
		return STARTING_RATING;
	}

	public static void main(String[] args){

		// Tests
		Player george = new Player(0,"George",1800);
		Player ben = new Player(0,"Ben",1500);


		System.out.println(getUpdatedRating(george, ben, false));
	}

	public static int getUpdatedRating(Player a, Player b, boolean aWins){

		double R_a = (double) a.getRating();
		double R_b = (double) b.getRating();

		double E_a = 1.0/(1+Math.pow(10,((R_b-R_a)/400)));

		double S;

		if(aWins){
			S = 1;
		}else{
			S = 0;
		}

		double R_prime_a = R_a + K*(S-E_a);

		return (int)R_prime_a;

	}

}
