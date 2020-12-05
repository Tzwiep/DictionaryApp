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
import com.sun.javafx.property.adapter.PropertyDescriptor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.EventObject;
import java.util.ResourceBundle;

public class SearchForWordViewController implements Initializable {

    @FXML
    private JFXListView<WordInfo> wordListView;

    @FXML
    private JFXTextField wordInputTextField;

    @FXML
    private Label rowsReturnedLabel;

    private ApiResponseModel response;

    private WordInfo wordSelected;

    private static String wordName;

    public static String getWordName(){
        return wordName;
    }

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
        searchText = searchText.replace(" ","%20");
        wordName = searchText;

        // call the api
        WordsApiUtility.getWordFromApi(searchText);

        // read the json file and create ApirRsponseModel
        File jsonFile = new File("src/Utilities/words.json");
        response = JsonFileUtility.getApiInfoFromJson(jsonFile);

        updateScene();

    }

    private void updateScene()
    {
      wordListView.getItems().clear();
      wordListView.setExpanded(true);
      wordListView.depthProperty().set(1);
      wordListView.getItems().addAll(response.getResults());
      rowsReturnedLabel.setText("Definitions: " + wordListView.getItems().size());

    }

    public void reloadScene() {
        File jsonFile = new File("src/Utilities/words.json");
        response = JsonFileUtility.getApiInfoFromJson(jsonFile);
        wordListView.getItems().clear();
        wordListView.setExpanded(true);
        wordListView.depthProperty().set(1);
        wordListView.getItems().addAll(response.getResults());
        rowsReturnedLabel.setText("Definitions: " + wordListView.getItems().size());
    }
}
