package backgammontourney;

import java.util.ArrayList;


/***
 * A Person object corresponds to a real person and serves as the primary
 * object used in the BackgammonTourney application
 * @author andy
 *
 */
public class Person {
	private String name;
	private int wins = 0;
	private int losses = 0;
	private int seed;
	private ArrayList<Person> beat = new ArrayList<Person>();
	private ArrayList<Person> lostTo = new ArrayList<Person>();
	
	/**
	 * Constructor
	 * @param name	name of the corresponding person
	 */
	public Person(String name) {
		this.name = name;
	}
	
	/**
	 * Accessor method for name
	 * @return	String name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Accessor for the number of wins
	 * @return	wins
	 */
	public int getNumWins() {
		return wins;
	}
	
	/**
	 * Accessor for the number of losses
	 * @return	losses
	 */
	public int getNumLosses() {
		return losses;
	}
	
	/**
	 * Accessor for seed in the tournament
	 * @return	seed
	 */
	public int getSeed() {
		return seed;
	}
	
	/**
	 * Gets a list of Person objects this has beaten
	 * @return	victories
	 */
	public ArrayList<Person> getVictories(){
		return beat;
	}
	
	/**
	 * Gets a list of Person objects this has lost to
	 * @return	losses
	 */
	public ArrayList<Person> getLosses(){
		return lostTo;
	}
	
	/**
	 * Add a person who this has beaten
	 * @param p
	 */
	public void addBeat(Person p) {
		wins++;
		beat.add(p);
	}
	
	/**
	 * Add a person who this has lost to
	 * @param p
	 */
	public void addLostTo(Person p) {
		losses++;
		lostTo.add(p);
	}
	
	/**
	 * Add the seed for ths person
	 * @param seed
	 */
	public void addSeed(int seed) {
		this.seed = seed;
	}
	
	/**
	 * Overrides Object's toString method and returns a String of the Person
	 * object's name and seed number
	 */
	public String toString() {
		return name + ", seed #" + seed;
	}
	
}
