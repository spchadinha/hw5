
import java.util.*;
/*
This class represents a K-armed bandit that can be solved using the e-Greedy algorithm
described in P. Aurer et al 2002. 
*/
public class GreedyBandit {
	// The probability of payout for each arm
	double[] arms;
	// The random number generator, from which random doubles will be used for rewards
	Random luck = new Random();

	/*
	Creates a K-armed bandit from a given payout probability distribution

	@param probs A list of doubles that contains the probability of payout for each arm of the bandit
	*/
	public GreedyBandit(double[] probs) {
		arms = probs;
	}

	/*
	Play one of the K machines on the bandit

	@param i The number of the machine being played
	@return Retuns 1 if the randomly generated number is lower than the payout probability and 0
	if the number is higher
	*/
	public int playMachine(int i) {
		double attempt = luck.nextDouble();
		if (attempt <= arms[i]) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < this.arms.length; i++) {
			output += "arm " +  Integer.toString(i+1) + ": " + Double.toString(arms[i]) + " ";
		}

		return output;
	}
}