
import java.util.*;


public class Bandit {

	List<Double> arms;

	public Bandit(ArrayList<Double> probs) {
		arms = probs;
	}

	public boolean[] getResults() {
		boolean[] results = new boolean[arms.size()];
		Random r = new Random();
		int at = 0;
		for (double prob : arms) {
			double s = r.nextDouble();
			if (s<prob){
				results[at] = true;
			}
			else {
				results[at] = false;
			}
			at++;
		}
		return results;
	}
}