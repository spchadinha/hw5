

public class GreedClient {

 public static void main(String[] args) {
  System.out.println("Generating the probability array");
  double[] p = {.9, .6};
  System.out.println("Building the bandit");
  GreedyBandit b = new GreedyBandit(p);
  
  GreedyPatron g = new GreedyPatron(2, .5);
  System.out.println(g);
  
  for (int i = 0; i < 100000; i++) {
      int choice = g.chooseArm();
      int result = b.playMachine(choice);
      g.updateRewards(result);
  }
  
  
  System.out.println(g);
 }
}