/**
 *  Tyler Zwiep
 *  200428335
 */
package Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WordInfo {

    private String definition;

    private String partOfSpeech;

    private String [] synonyms;

    private String [] examples;

    public WordInfo(String definition, String partOfSpeech, String[] synonyms, String[] examples) {
        setDefinition(definition);
        setPartOfSpeech(partOfSpeech);
        setSynonyms(synonyms);
        setExamples(examples);
    }

    public void ValidateInstanceVariables(){
        setDefinition(definition);
        setPartOfSpeech(partOfSpeech);
        setSynonyms(synonyms);
        setExamples(examples);
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return Character.toUpperCase(partOfSpeech.charAt(0)) + partOfSpeech.substring(1);
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;

    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String[] synonyms) {
            this.synonyms = synonyms;
    }

    public String[] getExamples() {
        return examples;
    }


    public void setExamples(String[] examples) {
            this.examples = examples;

    }

    public String toString(){
        return String.format("%s: %s", partOfSpeech,definition);
    }
}


