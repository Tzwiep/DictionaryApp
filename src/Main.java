import Utilities.WordsApiUtility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WordsApiUtility.getWordFromApi("tenet");
        System.out.println();
    }
}

