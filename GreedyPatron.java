
import java.util.*;

public class GreedyPatron {

 double[] rewards;
 int[] numPlays;
 private int arms;
 private double d;
 private int n = 1;
 private double c;
 public int lastPlayedIndex = 0;
 private Random r = new Random();

 public GreedyPatron(int numArms, double cConst) {
  this.rewards = new double[numArms];
  this.numPlays = new int[numArms];
  this.arms = numArms;
  this.c = cConst;

  for (int i = 0; i < this.rewards.length; i++) {
   this.rewards[i] = 0;
   this.numPlays[i] = 0;
  }
 }

 public void updateRewards(int reward) {
  //System.out.println("Rewards update");
  //System.out.println("   Last played: " + Integer.toString(this.lastPlayedIndex));
  int i = this.lastPlayedIndex;
  this.numPlays[i]++;
  int num = numPlays[i];
  this.rewards[i] = ((this.rewards[i]*(num-1)) + reward)/num;
  this.n++;
 }

 public int chooseArm() {
  //System.out.println("Updating d");
  updateD();
  //System.out.println("getting epsilon");
  double epsilon = getEpsilon();

  double playMax = this.r.nextDouble();
  // System.out.print(1-epsilon);
  // //System.out.println(" " + Double.toString(playMax));
  if (playMax <= (1-epsilon)) {
   this.lastPlayedIndex = findMaxIndex();
   //System.out.println("     Should play max: " + Integer.toString(this.lastPlayedIndex));
   return this.lastPlayedIndex;
  }
  else {
   this.lastPlayedIndex = this.r.nextInt(this.rewards.length);
   //System.out.println("     Playing random arm: " + Integer.toString(this.lastPlayedIndex));
   return this.lastPlayedIndex;
  }
 }

 private double getEpsilon() {
  double eq = (this.c*this.arms)/(this.d*this.d*this.n);
  //System.out.println("Epsilon: " + Double.toString(eq));
  if (eq > 1) {
   return 1;
  }
  else {
    //System.out.println("   1-E: " + Double.toString(1-eq));
   return eq;
  }
 }

 private void updateD() {
  double min = 999999;
  int minIndex = 0;
  int maxIndex = findMaxIndex();
  for (int i = 0; i < this.rewards.length; i++) {
   double delta = this.rewards[maxIndex] - this.rewards[i];
   if (i != maxIndex && min > delta) {
    min = delta;
    minIndex = i;
   }
  }
  //System.out.println("     updated D: " + Double.toString(min));
  this.d = min;
 }

 private int findMaxIndex() {
  int max = -1;
  for (int i = 0; i < this.rewards.length; i++) {
    //System.out.println("         Observing i: " + Integer.toString(i) + ", value: " + Double.toString(this.rewards[i]));
   if (max < this.rewards[i]) {
    //System.out.println("          max has been updated to: " + Integer.toString(i));
    max = i;
   }
  }
  return max;
 }

 @Override
 public String toString() {
  String out = "";
  for (int i = 0; i < this.arms; i++) {
   out += "Arm: " + Integer.toString(i+1) + ", Current Reward: " + Double.toString(rewards[i])
   + ", Played: " + Integer.toString(numPlays[i]) + "\n";   
  }
  return out;
 }


}