package backgammontourney;

import java.util.ArrayList;
import java.util.Random;

/***
 * 
 * @author andy
 *
 */
public class Tournament {
	protected ArrayList<Person> participants;
	protected Random random = new Random();
	protected int bracketSize;
	protected int playInGames;
	
	/**
	 * Constructor
	 * @param participants
	 */
	public Tournament(ArrayList<Person> participants) {
		this.participants = participants;
		tournamentStats();
	}
	
	/**
	 * Figure out what the bracket will look like (2, 4, 8, 16, 32) and how
	 * many play-in games there will be. Assign to global variables.
	 */
	public void tournamentStats() {
		int powTwo = 2;
		while(powTwo < participants.size()) {
			powTwo = powTwo*2;
		}
		powTwo = powTwo/2;
		bracketSize = powTwo;
		playInGames = participants.size() - powTwo;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Person> evenBracket(){
		ArrayList<Person> pip = playInPlayers();
		ArrayList<Person> evenBracket = (ArrayList<Person>) participants.clone();
		while(pip.size() > 0) {
			int winnerIndex = random.nextInt()%2 == 0 ? 0 : 1;
			Person winner = pip.remove(winnerIndex);
			Person loser = pip.remove(0);
			victory(winner.getName(), loser.getName());
			evenBracket.remove(indexOfPlayer(loser.getName(),evenBracket));
		}
		return evenBracket;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Person> playInPlayers(){
		ArrayList<Person> pCopy = (ArrayList<Person>) participants.clone();
		ArrayList<Person> pip = new ArrayList<Person>();
		for(int i = 0 ; i < playInGames*2; i++) {
			pip.add(pCopy.remove(random.nextInt(pCopy.size())));
		}
		return pip;
	}
	
	/**
	 * 
	 * @param players
	 * @return
	 */
	public ArrayList<Person> play(ArrayList<Person> players){
		if(players.size() == 1) {
			return players;
		}
		ArrayList<Person> winners = new ArrayList<Person>();
		int numWinners = players.size()/2;
		while(winners.size() < numWinners) {
			Person winner = players.remove(random.nextInt(2));
			Person loser = players.remove(0);
			winners.add(winner);
			victory(winner.getName(), loser.getName());
		}
		
		return play(winners);
	}
	
	/**
	 * 
	 * @param winnerName
	 * @param loserName
	 */
	public void victory(String winnerName, String loserName) {
		participants.get(indexOfPlayer(winnerName,participants))
			.addBeat(participants.get(indexOfPlayer(loserName,participants)));
		participants.get(indexOfPlayer(loserName,participants))
			.addLostTo(participants.get(indexOfPlayer(winnerName,participants)));
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Person> getMostWins(){
		int wins = 0;
		ArrayList<Person> best = new ArrayList<Person>();
		for(Person p : participants) {
			if(p.getNumWins() > wins) {
				wins = p.getNumWins();
			}
		}
		for(Person p : participants) {
			if(p.getNumWins() == wins) {
				best.add(p);
			}
		}
		return best;
	}

	/**
	 * 
	 * @param name
	 * @param players
	 * @return
	 */
	public int indexOfPlayer(String name, ArrayList<Person> players) {
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
