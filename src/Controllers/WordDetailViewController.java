/**
 * Tyler Zwiep
 * 200428335
 */
package Controllers;

import Models.ApiResponseModel;
import Models.WordInfo;
import Utilities.JsonFileUtility;
import Utilities.WordsApiUtility;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WordDetailViewController implements Initializable {

    @FXML
    private Label partOfSpeechLabel;

    @FXML
    private Label definitionLabel;


    @FXML
    private Label exampleLabel;

    @FXML
    private Label titleWordLabel;

    @FXML
    private Label pronunciationLabel;

    @FXML
    private HBox synHBox;

    @FXML
    private HBox synHBoxWLabel;

    @FXML
    private HBox exampleHBox;

    private ApiResponseModel response;


    /**
     * This method is called from the SearchForWord controller and it is responsible for initializing the WordDetails view
     * with the the details of the selected word from the listView.
     * @param selectedWord
     */
    public void initWordDetails(WordInfo selectedWord) {
        File jsonFile = new File("src/Utilities/words.json");
        response = JsonFileUtility.getApiInfoFromJson(jsonFile);
        // set the title to display the selected word
        titleWordLabel.setText(response.getWord().toUpperCase());

        // if response contains a pronunciation... set the label
        if (response.getPronunciation() != null)
            pronunciationLabel.setText(response.getPronunciation().toString());
        else
            pronunciationLabel.setText("");

        // set other labels
        partOfSpeechLabel.setText(selectedWord.getPartOfSpeech());
        definitionLabel.setText(selectedWord.getDefinition());

        // StringBuilder for the array of Synonyms - Change first letter in each word to capital and add comma between.
        StringBuilder sbSynonyms = new StringBuilder();
        if (selectedWord.getSynonyms() != null) {
            for (String syn : selectedWord.getSynonyms()) {
                syn = Character.toUpperCase(syn.charAt(0)) + syn.substring(1);
                Hyperlink link = new Hyperlink(syn);
                String finalSyn = syn.replace(" ", "%20");

                // add an ActionEvent to the new synonym hyperlinks that will search the selected word and return to the searchWord view
                link.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        WordsApiUtility.getWordFromApi(finalSyn);
                        try {
                            goBack(event);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Label error = new Label(" Word not found - Try another word");
                            synHBox.getChildren().add(error);
                        }
                    }
                });
                // add the new hyperlink synonym to it's HBox for display
                synHBox.getChildren().add(link);
            }
        } else {
            // hide the Synonyms field if none available
            synHBoxWLabel.setVisible(false);
        }

        // check if word contains usage examples
        if (selectedWord.getExamples() != null) {
            // StringBuilder for the array of usage examples
            StringBuilder sbExamples = new StringBuilder();
            for (String ex : selectedWord.getExamples()) {
                // add brackets to demonstrate it as a sentence
                sbExamples.append("'" + Character.toUpperCase(ex.charAt(0)) + ex.substring(1) + "'\n" + "\n");
            }
            // set example label with array of formatted string examples
            exampleLabel.setText(String.valueOf(sbExamples));
        } else
            // hide example usage label if none available
            exampleHBox.setVisible(false);

    }

    /**
     * This method calls the reloadScene() method in SearchForWord Controller and loads the searchWord view
     * @param event
     * @throws IOException
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/searchForWordView.fxml"));
        Parent wordViewParent = loader.load();
        Scene wordViewScene = new Scene(wordViewParent);

        SearchForWordViewController controller = loader.getController();
        controller.reloadScene();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(wordViewScene);
        window.setTitle("Dictionary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
