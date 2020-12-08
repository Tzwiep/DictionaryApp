/**
 *  Tyler Zwiep
 *  200428335
 */
package Models;

public class ApiResponseModel {

    private String word;

    private WordInfo [] results;

    private Pronunciation pronunciation;

    public ApiResponseModel(String word, WordInfo[] results, Pronunciation pronunciation) {
        setWord(word);
        setResults(results);
        setPronunciation(pronunciation);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        if(word.matches("[A-Za-z'-]+"))
            this.word = word;
        else
            throw new IllegalArgumentException("Valid dictionary words don't contain numbers or special characters");
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
