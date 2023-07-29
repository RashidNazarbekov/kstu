package com.kstu.fitnes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authentification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Аутентификация");
        stage.getIcons().add(new Image(("D:\\Neobis\\Dima Neman\\fitnes\\fitnes\\images\\logo.jpg")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}