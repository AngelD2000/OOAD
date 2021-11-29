module com.example.proj6restartreal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.proj6restartreal to javafx.fxml;
    exports com.example.proj6restartreal;
}