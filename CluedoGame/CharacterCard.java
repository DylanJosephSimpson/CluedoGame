/**
 * Class which is necessary for interactions. Describes the character *in question.
 * CharacterCard subclass:
 *
 * The CharacterCard subclass is a class which extends the Card Superclass. CharacterCards are used to carry out
 * interaction between players during the suggestions faze of the game.
 */

public class CharacterCard extends Card {


    private String characterName;

    /**Constructor for a single CharacterCard
     * @param aCharacterName - the name of the card. For CharacterCards this is the character names.
     */
    CharacterCard(String aCharacterName) {
        characterName = aCharacterName;
    }
    /**
     * getCharacterName method :
     *
     * getter method used to get the name of the characterCard
     *
     * @return - The name of the characterCard as a String
     */
    String getCharacterName() {
        return characterName;
    }

    /**
     * toString method:
     *
     * basic toString method for a CharacterCard
     *
     * @return - returns the name of the character as a String
     */

    public String toString() {
        return characterName;
    }
}
