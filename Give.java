import java.util.Random;

public class Give implements Move {
	
	private Place place;
	private Character character;
	
	//constructor
	public Give(Place place, Character character) {
		this.place = place;
		this.character = character;
	}
	
	public void execute() {
		Random rand = new Random(); 
		
		//Generate random health to give to the selected character
		int randomReplenish = rand.nextInt(20);
		
		//Increase the characters health
		this.character.increaseHealth(randomReplenish);
		
		System.out.println(character.name() + "gained" + randomReplenish + " points of health");
	}

}
