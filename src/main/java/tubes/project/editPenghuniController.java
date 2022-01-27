package tubes.project;

import Helper.DBHelper;
import boardin.penghuni;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class editPenghuniController {

    private final Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    private final Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);

    private Connection connection;
    private boardin.penghuni penghuni;
    private PreparedStatement preparedStatement;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private ObservableList<penghuni> listOfpenghuni;

    @FXML
    private TextField txtHP;

    @FXML
    private TextField txtWali;

    @FXML
    private TextField txtKamar;

    @FXML
    private JFXButton updateButton;

    @FXML
    private TableView<penghuni> tblShowPenghuni;

    @FXML
    private TableColumn<penghuni, String> namaCol;

    @FXML
    private TableColumn<penghuni, String> nikCol;

    @FXML
    private TableColumn<penghuni, String> hpCol;

    @FXML
    private TableColumn<penghuni, String> waliCol;

    @FXML
    private TableColumn<penghuni, String> kotaCol;

    @FXML
    private TableColumn<penghuni, String> kamarCol;

    @FXML
    private TableColumn<penghuni, LocalDate> tanggalCol;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DBHelper dbHelper = new DBHelper();
        connection = dbHelper.getConnection();

        listOfpenghuni = readDB(connection);

        namaCol.setCellValueFactory(new PropertyValueFactory<>("namaSC"));
        nikCol.setCellValueFactory(new PropertyValueFactory<>("nikSC"));
        hpCol.setCellValueFactory(new PropertyValueFactory<>("nomorhpSC"));
        waliCol.setCellValueFactory(new PropertyValueFactory<>("nomorwaliSC"));
        kotaCol.setCellValueFactory(new PropertyValueFactory<>("kotaSC"));
        kamarCol.setCellValueFactory(new PropertyValueFactory<>("nomorkamarSC"));
        tanggalCol.setCellValueFactory(new PropertyValueFactory<>("tanggalSC"));


        tblShowPenghuni.setItems(listOfpenghuni);

    }

    public void editClicked(ActionEvent event) throws SQLException {
        boolean resultTrue = isTextFieldCorrect(txtHP, txtWali, txtKamar);

        if (resultTrue) {
            alertWarning.setTitle("Warning!!!");
            alertWarning.setHeaderText(null);
            alertWarning.setContentText("Textfield tidak boleh kosong!!");

            alertWarning.showAndWait();

    } else {


        String query = "UPDATE tblpenghuni set nomorhp = ?, nomorwali = ? , nomorkamar = ? where idpenghuni = ?";

        int id = penghuni.getIdSC();
        String nomorHP = txtHP.getText();
        String nomorWali = txtWali.getText();
        String nomorKamar = txtKamar.getText();

        alertInformation.setTitle("Update Sukses!!!");
        alertInformation.setHeaderText(null);
        alertInformation.setContentText("Data berhasil diubah!!");

        alertInformation.showAndWait();

        tblShowPenghuni.refresh();
        tblShowPenghuni.getSelectionModel().select(null);

        txtKamar.clear();
        txtWali.clear();
        txtHP.clear();

        txtHP.setDisable(true);
        txtKamar.setDisable(true);
        txtWali.setDisable(true);
        updateButton.setDisable(true);

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nomorHP);
        preparedStatement.setString(2, nomorWali);
        preparedStatement.setString(3, nomorKamar);
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        listOfpenghuni = readDB(connection);
        tblShowPenghuni.setItems(listOfpenghuni);
    }
}



    private boolean isTextFieldCorrect(TextField txtHP, TextField txtWali, TextField txtKamar) {
        return isTextFieldEmpty(txtHP) || isTextFieldEmpty(txtWali) || isTextFieldEmpty(txtKamar);
    }

    private boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().equals("");
    }

    private ObservableList<penghuni> readDB(Connection connection) throws SQLException {
        String query = "SELECT * FROM tblpenghuni";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<penghuni> allPenghuni = FXCollections.observableArrayList();

        while (resultSet.next()) {
            penghuni Penghuni = new penghuni(

                    resultSet.getString("nama"),
                    resultSet.getString("nik"),
                    resultSet.getString("nomorhp"),
                    resultSet.getString("nomorwali"),
                    resultSet.getString("kota"),
                    resultSet.getString("nomorkamar"),
                    resultSet.getDate("tanggal"),
                    resultSet.getInt("idpenghuni"));
            allPenghuni.add(Penghuni);
        }

        return allPenghuni;
    }

    public void onTableClicked(MouseEvent event) {
        penghuni = tblShowPenghuni.getSelectionModel().getSelectedItem();

        txtHP.setDisable(false);
        txtKamar.setDisable(false);
        txtWali.setDisable(false);
        updateButton.setDisable(false);

        txtHP.setText(String.valueOf(penghuni.getNomorhpSC()));
        txtKamar.setText(String.valueOf(penghuni.getNomorkamarSC()));
        txtWali.setText(String.valueOf(penghuni.getNomorwaliSC()));
    }

    public void backclicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Penghuni.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
