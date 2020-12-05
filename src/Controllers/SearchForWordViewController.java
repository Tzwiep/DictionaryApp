package Controllers;

import Models.ApiResponseModel;
import Models.WordInfo;
import Utilities.JsonFileUtility;
import Utilities.WordsApiUtility;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchForWordViewController implements Initializable {

    @FXML
    private JFXListView<WordInfo> wordListView;

    @FXML
    private JFXTextField wordInputTextField;

    @FXML
    private Label rowsReturnedLabel;

    private ApiResponseModel response;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rowsReturnedLabel.setText("");
    }

    @FXML
    private void searchWordButton()
    {
        String searchText = wordInputTextField.getText();
        searchText = searchText.replace(" ","%20");
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
      wordListView.getItems().addAll();

    }
}
