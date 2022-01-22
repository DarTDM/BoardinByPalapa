package tubes.project;

import Helper.DBHelper;
import boardin.penghuni;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PenghuniController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnback;

    @FXML
    public void backclicked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void addClicked(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("addPenghuni.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private ObservableList<penghuni> listOfpenghuni = FXCollections.observableArrayList();

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
        Connection connection = dbHelper.getConnection();

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
                    resultSet.getDate("tanggal"));
            allPenghuni.add(Penghuni);
        }

        return allPenghuni;
    }

}
