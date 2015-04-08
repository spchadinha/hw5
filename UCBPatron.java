
import java.util.*;
import java.lang.Math;


public class UCBPatron {

double[] rewards; //x_j avg reward obtained from machine j 'totalReward[j]/numplays[j]'
int[] totalRewards; //total rewards for each machine
int[] numPlays; //num plays at each machine 'n_j'

private double n; //overall number of plays
private int arms;

//ucbpatron constructor creates a blank patron
public UCBPatron(int numArms){
  this.rewards = new double[numArms];
  this.totalRewards = new int[numArms];
  this.numPlays = new int[numArms];
  this.arms = numArms;

  for (int i = 0; i < this.rewards.length; i++) {
   this.rewards[i] = 0;
   this.totalRewards[i] = 0;
   this.numPlays[i] = 0;
  }
}

//initializes the ucb patron by playing each machine once
public void init(GreedyBandit bandit){
	for(int i = 0; i < this.rewards.length; i++){
		int result = bandit.playMachine(i);
		this.update(result, i);
	}
}

public void update(int reward, int index){
	n++;
	numPlays[index]++;
	totalRewards[index] += reward;
	//System.out.println(totalRewards[index]/numPlays[index]);
	rewards[index] = (double)totalRewards[index]/numPlays[index];
}


//this action chooses the next arm/machine to play
public int chooseArm(){
	double temp = 0;
	int toReturn = 0;
	for (int i = 0; i < this.rewards.length; i++){
		double current = rewards[i] + Math.sqrt(2*Math.log(n)/numPlays[i]);
		if (current > temp){
			temp = current;
			toReturn = i;
		}		
	}
	//System.out.println("Choosing arm" + toReturn);
	return toReturn;
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
