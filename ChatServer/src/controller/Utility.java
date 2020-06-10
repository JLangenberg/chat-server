package controller;

import java.util.Date;

/**
 * A class that stores methods that could be used in several places and do
 * different, non-class specific classes. It does not store any global
 * variables.
 * 
 * @author Julius Langenberg, AH811
 *
 */
public class Utility {

	/**
	 * Returns the current hour and minute.
	 * 
	 * @return the current hour and minute.
	 */
	public String getTime() {
		// The string used to save the information
		String time;
		// The date object used to get the time
		Date date = new Date();

		// Get the current hours and minutes separately and fuse them in a string
		time = date.getHours() + ":" + date.getMinutes();
		// Return the assembled string
		return time;
	}

	/**
	 * Returns a string as an answer to other strings
	 * 
	 * @param request The request that determines the returned string
	 * @return The "server answer"
	 */
	public String determineAnswer(String request) {
		// Set a default value
		String answer = "Answer to " + request;

		// Check for certain sentences and answer if there is a match.
		switch (request) {

		case "Uhrzeit":
			answer = this.getTime();
			break;
		case "Hallo":
			answer = "Hallo.";
			break;
		case "Wiedersehen":
			answer = "Tschau mit V";
			break;
		case "Wie heißt du?":
			answer = "Gustav Grün";
			break;
		case "Meddl Leude":
			answer = "Meddl";
			break;
		case "Hello there":
			answer = "General Kenobi.";
			break;
		case "Salut":
			answer = "Je ne parle pas francais, und du hast nen Ohrwurm!";
			break;
		case "Was machst du so?":
			answer = "Serven. Surfen? Sowas in der Richtung.";
			break;
		case "Somewhere":
			answer = "over the rainbow!";
			break;
		case "Halo":
			answer = "Das Spiel oder der Tippfehler?";
			break;
		case "Halo, das Spiel":
			answer = "Achso. Das, mit den ekeligen Flood hatte. Unsympathisch.";
			break;
		case "Doom Eternal?":
			answer = "Animal Crossing New Horizons?";
			break;
		case "Animal Crossing New Horizons?":
			answer = "Doom Eternal?";
			break;
		case "Was für Musik hörst du?":
			answer = "Chiptune, das hört sich nach Zuhause an. Beep-Boop.";
			break;
		case "Was ist dein Lieblingsessen?":
			answer = "Schwierig, so als Computer. Aber ich habe gehört Falafel soll gut schmecken.";
			break;
		case "Was hältst du von den ganzen Corona Fällen?":
			answer = "Abstand.";
			break;
		case "04.05.2020":
			answer = "May the Fourth be with you.";
			break;
		case "My code doesn't work!":
			answer = "Change nothing, recompile, run it and pray";
			break;
		case "List":
			answer = "TODO";
			break;
		}
		// Return the response
		return answer;
	}

}
