package tubes.project;

import Helper.DBHelper;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class addPenghuniController {

    private Connection connection;

    private final Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private final Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button btnback;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtNik;

    @FXML
    private TextField txtHP;

    @FXML
    private TextField txtWali;

    @FXML
    private TextField txtKota;

    @FXML
    private TextField txtKamar;

    @FXML
    private DatePicker dateMasuk;

    @FXML
    private JFXButton submit;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = new DBHelper();
        connection = dbHelper.getConnection();
    }

    @FXML
    void addClicked(ActionEvent event) throws IOException, SQLException {
        boolean textfieldEmptyResult = isInputEmpty(
                txtNama, txtNik, txtHP, txtWali, txtKota, txtKamar, dateMasuk
        );
        if (textfieldEmptyResult) {
            alertWarning.setTitle("Warning!!!");
            alertWarning.setHeaderText(null);
            alertWarning.setContentText("Data tidak boleh kosong!!");


            alertWarning.showAndWait();
        } else {
            String nama = txtNama.getText();
            String nik = txtNik.getText();
            String nomorhp = txtHP.getText();
            String nomorwali = txtWali.getText();
            String kota = txtKota.getText();
            String nomorkamar = txtKamar.getText();
            LocalDate tanggal = dateMasuk.getValue();

            writeToDB(nama, nik, nomorhp, nomorwali, kota, nomorkamar, tanggal);

            alertInformation.setTitle("Input Sukses!!!");
            alertInformation.setHeaderText(null);
            alertInformation.setContentText("Data berhasil ditambahkan");

            alertInformation.showAndWait();

            txtNama.clear();
            txtNik.clear();
            txtHP.clear();
            txtWali.clear();
            txtKota.clear();
            txtKamar.clear();

        }
    }


    private void writeToDB(String nama, String nik, String nomorhp,
                           String nomorwali, String kota, String nomorkamar,
                           LocalDate tanggal) throws SQLException {

        String query = "INSERT INTO tblpenghuni(nama, nik, nomorhp, nomorwali, kota, nomorkamar, tanggal) VALUES(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, nama);
        preparedStatement.setString(2, nik);
        preparedStatement.setString(3, nomorhp);
        preparedStatement.setString(4, nomorwali);
        preparedStatement.setString(5, kota);
        preparedStatement.setString(6, nomorkamar);
        preparedStatement.setDate(7, Date.valueOf(tanggal));

        preparedStatement.executeUpdate();
    }

    private boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().equals("");
    }

    private boolean isInputEmpty(TextField txtNama, TextField txtNik, TextField txtHP, TextField txtWali,
                                 TextField txtKota, TextField txtKamar, DatePicker dateMasuk) {
        return isTextFieldEmpty(txtNama) || isTextFieldEmpty(txtNik) || isTextFieldEmpty(txtHP)
                || isTextFieldEmpty(txtWali) || isTextFieldEmpty(txtKota) || isTextFieldEmpty(txtKamar)
                || isTextFieldEmpty(dateMasuk);
    }

    private boolean isTextFieldEmpty(DatePicker dateMasuk) {
        return dateMasuk.getValue().equals("");
    }


    public void backclicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Penghuni.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
