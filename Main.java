package backgammontourney;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/***
 * 
 * @author andy
 *
 */
public class Main {
	private static ArrayList<Person> participants = new ArrayList<Person>();
	private static Scanner scanner;
	
	public static void main(String[] args) throws FileNotFoundException{
		uploadParticipants("/Users/andy/Desktop/over32.csv");
		randomizeP();
		SeededTournament tournament = new SeededTournament(participants);
		
	}
	
	public static void uploadParticipants(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		scanner = new Scanner(file).useDelimiter("\\Z");
        String[] names = scanner.next().split(",");
        for(String s : names) {
        	Person p = new Person(s);
        	participants.add(p);
        }
	}
	
	public static void randomizeP() {
		Random random = new Random();
		ArrayList<Person> randomized = new ArrayList<Person>();
		ArrayList<Person> copy = (ArrayList<Person>) participants.clone();
		while(copy.size() > 0) {
			Person p = copy.remove(random.nextInt(copy.size()));
			randomized.add(p);
		}
		participants = randomized;
	}
}
