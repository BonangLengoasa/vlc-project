module com.example.myvlc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

  opens com.example.myvlc to javafx.fxml;
    exports com.example.myvlc;
}