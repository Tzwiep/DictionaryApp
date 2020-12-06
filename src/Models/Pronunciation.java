package Models;

import com.google.gson.annotations.SerializedName;

public class Pronunciation {
    @SerializedName("all")
    private String pronunciation;

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }
    public String toString(){
        return String.format("(%s)", pronunciation);
    }
}
