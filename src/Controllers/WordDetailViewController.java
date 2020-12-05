package Controllers;

import Models.ApiResponseModel;
import Models.WordInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class WordDetailViewController implements Initializable {

    @FXML
    private Label partOfSpeechLabel;

    @FXML
    private Label definitionLabel;

    @FXML
    private Label synonymsLabel;

    @FXML
    private Label exampleLabel;

    @FXML
    private Label titleWordLabel;


    public void initWordDetails(WordInfo selectedWord){
        titleWordLabel.setText(SearchForWordViewController.getWordName().toUpperCase());
        partOfSpeechLabel.setText(selectedWord.getPartOfSpeech());
        definitionLabel.setText(selectedWord.getDefinition());
        if(selectedWord.getSynonyms().length == 0)
            synonymsLabel.setText(" No synonyms available for this word");
        else
            synonymsLabel.setText(Arrays.toString(selectedWord.getSynonyms()));

        if(selectedWord.getExamples().length == 0)
            exampleLabel.setText("No examples available for this word");
        else
            exampleLabel.setText(Arrays.toString(selectedWord.getExamples()));
    }
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
