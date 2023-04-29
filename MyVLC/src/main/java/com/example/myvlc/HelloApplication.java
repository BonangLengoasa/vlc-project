package com.example.myvlc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("My-view.fxml"));
        Scene scene = new Scene(root.load());
      // linking project to my css style
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
        stage.setTitle("Bonang Lengoasa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}