package org.andrejsuharew.valutpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        GridPane root = loader.load();
        primaryStage.setTitle("ValutPro by Andrej Suharew 2024");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}