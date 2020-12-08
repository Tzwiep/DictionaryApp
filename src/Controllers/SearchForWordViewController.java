/**
 *  Tyler Zwiep
 *  200428335
 */
package Controllers;

import Models.ApiResponseModel;
import Models.WordInfo;
import Utilities.JsonFileUtility;
import Utilities.WordsApiUtility;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class SearchForWordViewController implements Initializable {

    @FXML
    private Label errMessageLabel;
    @FXML
    private JFXListView<WordInfo> wordListView;

    @FXML
    private JFXTextField wordInputTextField;

    @FXML
    private Label rowsReturnedLabel;

    private ApiResponseModel response;

    private WordInfo wordSelected;


    private static String wordName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rowsReturnedLabel.setText("");

        wordListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldWord, newWord) ->{
               wordSelected = newWord;
        });
    }


    public void changeToSelectedWord(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/wordDetailView.fxml"));
        Parent wordViewParent = loader.load();
        Scene wordDetailViewScene = new Scene(wordViewParent);

        WordDetailViewController controller = loader.getController();
        controller.initWordDetails(wordSelected);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(wordDetailViewScene);
        window.setTitle("Word Details");
    }


    @FXML
    private void searchWord()
    {
        String searchText = wordInputTextField.getText();

        searchText = searchText.trim();
        wordName = searchText;
        searchText = searchText.replace(" ","%20");

        // call the api
        WordsApiUtility.getWordFromApi(searchText);

        // read the json file and create ApiResponseModel
        File jsonFile = new File("src/Utilities/words.json");
        response = JsonFileUtility.getApiInfoFromJson(jsonFile);

        updateScene();

    }

    private void updateScene()
    {
        errMessageLabel.setText("");
      wordListView.getItems().clear();
      wordListView.setExpanded(true);
      wordListView.depthProperty().set(1);
        try{
      wordListView.getItems().addAll(response.getResults());
        } catch(Exception e){
            if(!wordInputTextField.getText().isEmpty())
                errMessageLabel.setText("Sorry, no definitions were found - Please search for a different word");
            else
                errMessageLabel.setText("Please enter the word that you want to find the definition of before clicking 'Search'");
        }
      rowsReturnedLabel.setText(wordName.toUpperCase() + " definitions: " + wordListView.getItems().size());


    }

    public void reloadScene() {
        File jsonFile = new File("src/Utilities/words.json");
        response = JsonFileUtility.getApiInfoFromJson(jsonFile);
        wordListView.getItems().clear();
        wordListView.setExpanded(true);
        wordListView.depthProperty().set(1);
        wordListView.getItems().addAll(response.getResults());
        wordName =response.getWord();
        wordInputTextField.setText(wordName);
        rowsReturnedLabel.setText(wordName.toUpperCase() + " Definitions: " + wordListView.getItems().size());
    }


}
