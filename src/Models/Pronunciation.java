/*
 Tyler Zwiep
  200428335
 */
package Models;

import com.google.gson.annotations.SerializedName;

public class Pronunciation {
    @SerializedName("all")
    private String pronunciation;

    public String getPronunciation() {
        return pronunciation;
    }

    /**
     * This method checks that the pronunciation matches letters and unicode characters in accordance with the international
     * phonetic alphabet (IPA)
     * @param pronunciation
     */
    public void setPronunciation(String pronunciation) {

        //if(pronunciation.matches("[A-z\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]*"))
            this.pronunciation = pronunciation;
        //else
          //  throw new IllegalArgumentException("Pronunciation must follow IPA standards");
    }

    /**
     * toString method returns the pronunciation formatted with brackets around it
     * @return
     */
    public String toString(){
        return String.format("(%s)", pronunciation);
    }
}
