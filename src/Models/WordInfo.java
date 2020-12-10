/**
 * Tyler Zwiep
 * 200428335
 */
package Models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordInfo {

    private String definition;

    private String partOfSpeech;

    private String[] synonyms;

    private String[] examples;

    public WordInfo(String definition, String partOfSpeech, String[] synonyms, String[] examples) {
        setDefinition(definition);
        setPartOfSpeech(partOfSpeech);
        setSynonyms(synonyms);
        setExamples(examples);
    }


    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        if ((definition.length() > 0) && (definition.matches("^[A-z]*((-|\\s)*[A-z])*$")))
            this.definition = definition;
        else
            throw new IllegalArgumentException("Definition should not be empty and must contain no special characters or numbers");
    }

    public String getPartOfSpeech() {
        return Character.toUpperCase(partOfSpeech.charAt(0)) + partOfSpeech.substring(1);
    }

    public void setPartOfSpeech(String partOfSpeech) {
        List<String> validPartsOfSpeech = Arrays.asList("NOUN", "VERB", "ADJECTIVE", "ADVERB", "PRONOUN", "PREPOSITION",
                "CONJUNCTION", "INTERJECTION", "ARTICLE", "DETERMINER");
        if (validPartsOfSpeech.contains(partOfSpeech.toUpperCase()))
            this.partOfSpeech = Character.toUpperCase(partOfSpeech.charAt(0)) + partOfSpeech.substring(1);
        else
            throw new IllegalArgumentException("Valid parts of speech are: " + validPartsOfSpeech);

    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String[] synonyms) {
        ArrayList<String> validSynonyms = new ArrayList<>();
        for (String synonym : synonyms) {
            if (synonym.matches("[A-Za-z'-]+"))
                validSynonyms.add(synonym);
            else
                throw new IllegalArgumentException("Synonyms must not contain numbers or special characters");
        }
        this.synonyms = validSynonyms.toArray(new String[0]);

    }

    public String[] getExamples() {
        return examples;
    }


    public void setExamples(String[] examples) {

        ArrayList<String> validExamples = new ArrayList<>();
        for (String example : examples) {
            if (example.matches("[A-Za-z'-]+"))
                validExamples.add(example);
            else
                throw new IllegalArgumentException("Synonyms must not contain numbers or special characters");
        }
        this.examples = validExamples.toArray(new String[0]);

    }

    public String toString() {
        int maxLength = 100;
        String definitionDisplay = "";
        if (definition.length() > maxLength) {
            definitionDisplay = definition.substring(0, maxLength) + "...";
            return String.format("%s: %s", partOfSpeech, definitionDisplay);
        } else
            return String.format("%s: %s", partOfSpeech, definition);
    }
}


