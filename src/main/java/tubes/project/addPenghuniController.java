package tubes.project;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class addPenghuniController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private JFXTextField txtNAMA;

    @FXML
    private Label show;

    @FXML
    private Button btnback;


    public void submitClicked(ActionEvent event) throws IOException {
        show.setText(String.valueOf(txtNAMA));

    }

    public void backclicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
