package ru.vsu.cs.course1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.vsu.cs.course1.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView.getRoot());
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Крестики-нолики: Анализ вероятностей");
        primaryStage.setMaximized(true); // Полноэкранный режим
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}