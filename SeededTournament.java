package backgammontourney;

import java.util.ArrayList;


public class SeededTournament extends Tournament {
	public SeededTournament(ArrayList<Person> participants) {
		super(participants);
		addSeeds();
	}
	
	/**
	 * 
	 */
	public void addSeeds() {
		for(int i = 0; i < participants.size(); i++) {
			participants.get(i).addSeed(i+1);
		}
	}
	
	/**
	 * 
	 */
	public ArrayList<Person> evenBracket(){
		ArrayList<Person> pip = playInPlayers();
		ArrayList<Person> evenBracket = (ArrayList<Person>) participants.clone();
		while(pip.size() > 0) {
			int winnerIndex = random.nextInt()%2 == 0 ? 0 : 1;
			Person winner = pip.remove(winnerIndex);
			Person loser = pip.remove(0);
			victory(winner.getName(), loser.getName());
			evenBracket = removePlayer(loser.getName(), evenBracket);
		} 
		return orderBySeed(evenBracket);
	}
	
	/**
	 * 
	 */
	public ArrayList<Person> playInPlayers(){
		ArrayList<Person> pCopy = (ArrayList<Person>) this.participants.clone();
		ArrayList<Person> pip = new ArrayList<Person>();
		int numPip = playInGames*2;
		for(int i = 0; i < numPip; i++) {
			Person p = pCopy.remove(highestSeedIndex(pCopy));
			pip.add(p);
		}
		return orderBySeed(pip);
	}
	
	/**
	 * 
	 * @param bracket
	 * @return
	 */
	public ArrayList<Person> orderBySeed(ArrayList<Person> bracket){
		ArrayList<Person> ordered = new ArrayList<Person>();
		while(bracket.size() > 0) {
			Person low = bracket.remove(lowestSeedIndex(bracket));
			Person high = bracket.remove(highestSeedIndex(bracket));
			ordered.add(low);
			ordered.add(high);
		}
		return ordered;
	}
	
	/**
	 * 
	 * @param bracket
	 * @return
	 */
	public int lowestSeedIndex(ArrayList<Person> bracket) {
		int lowestSeedIndex = 0;
		int lowestSeed = bracket.get(0).getSeed();
		for(int i = 0; i < bracket.size(); i++) {
			if(bracket.get(i).getSeed() < lowestSeed) {
				lowestSeed = bracket.get(i).getSeed();
				lowestSeedIndex = i;
			}
		}
		return lowestSeedIndex;
	}
	
	/**
	 * 
	 * @param bracket
	 * @return
	 */
	public int highestSeedIndex(ArrayList<Person> bracket) {
		int highestSeedIndex = 0;
		int highestSeed = 1;
		for(int i = 0; i < bracket.size(); i++) {
			if(bracket.get(i).getSeed() > highestSeed) {
				highestSeed = bracket.get(i).getSeed();
				highestSeedIndex = i;
			}
		}
		return highestSeedIndex;
	}
	
	/**
	 * 
	 * @param name
	 * @param bracket
	 * @return
	 */
	public ArrayList<Person> removePlayer(String name, ArrayList<Person> bracket){
		for(int i = 0; i < bracket.size(); i++) {
			if(bracket.get(i).getName().equals(name)) {
				bracket.remove(i);
			}
		}
		return bracket;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public Person winLoss(Person a, Person b) {
		int denominator = participants.size();
		int numeratorA = denominator - a.getSeed() + 1;
		int numeratorB = denominator - b.getSeed() + 1;
		return random.nextInt(denominator) >= numeratorA ? b : a;
	}
}
