/**
 *  Tyler Zwiep
 *  200428335
 */
package Models;

public class ApiResponseModel {

    private String word;

    private WordInfo [] results;

    private Pronunciation pronunciation;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordInfo[] getResults() {
        return results;
    }

    public void setResults(WordInfo[] results) {
        this.results = results;
    }

    public Pronunciation getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(Pronunciation pronunciation) {
        this.pronunciation = pronunciation;
    }
}
