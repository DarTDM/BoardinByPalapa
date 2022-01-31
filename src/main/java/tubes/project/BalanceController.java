package tubes.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BalanceController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void tfPengeluaran(ActionEvent actionEvent) {
    }

    public void btTambah(ActionEvent actionEvent) {
    }

    public void btKembali(ActionEvent event)throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Tanggal(ActionEvent actionEvent) {
    }

    public void tfKeterangan(ActionEvent actionEvent) {
    }

    public void tfPemasukan(ActionEvent actionEvent) {
    }
}
