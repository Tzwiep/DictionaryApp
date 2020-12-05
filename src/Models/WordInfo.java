/**
 *  Tyler Zwiep
 *  200428335
 */
package Models;

public class WordInfo {

    private String definition;

    private String partOfSpeech;

    private String [] synonyms;

    private String [] examples;



    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
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


