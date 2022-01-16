module tubes.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens tubes.project to javafx.fxml;
    exports tubes.project;
}