

public class GreedClient {

 public static void main(String[] args) {
  System.out.println("Generating the probability array");
  double[] p = {.9, .6};
  System.out.println("Building the bandit");
  GreedyBandit b = new GreedyBandit(p);
  
  UCBPatron u = new UCBPatron(2);
  System.out.println("UCBPatron");
  System.out.println(u);

  System.out.println("Initializing...");
  u.init(b);
  System.out.println(u);


  GreedyPatron g = new GreedyPatron(2, .5);
  System.out.println("GreedyPatron");
  System.out.println(g);
  
  for (int i = 0; i < 100000; i++) {
      int choice = g.chooseArm();
      int result = b.playMachine(choice);
      g.updateRewards(result);

      int choice2 = u.chooseArm();
      int result2 = b.playMachine(choice2);
      u.update(result2, choice2);
  }
  
  System.out.println("RESULTS FOR GreedyPatron");
  System.out.println(g);
  System.out.println("RESULTS FOR UCBPatron");
  System.out.println(u);
 }
}
