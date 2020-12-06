/**
 *  Tyler Zwiep
 *  200428335
 */
package Utilities;

import Models.WordInfo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WordsApiUtility {

    public static void getWordFromApi(String searchText)
    {
        // precaution to correct errors in overwriting file
        File file = new File("src/Utilities/words.json");
        if (file.exists())
            file.delete();

        String searchUrl = "https://wordsapiv1.p.rapidapi.com/words/" + searchText;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wordsapiv1.p.rapidapi.com/words/" + searchText))
                .header("x-rapidapi-key", "b6f552e987msh50291edc83cc305p169d2djsn3fe126bc23a7")
                .header("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();


        try {
            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("src/Utilities/words.json")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
