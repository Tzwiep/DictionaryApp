/**
 *  Tyler Zwiep
 *  200428335
 */
package Models;

public class ApiResponseModel {

    private static String word;

    private WordInfo [] results;



    public static String getWord() {
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
}
