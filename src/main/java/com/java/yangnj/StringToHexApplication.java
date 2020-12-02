package com.java.yangnj;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * @author ynj
 * @program: StringToHex
 * @description:
 * @date 2020-11-30 19:52:26
 */
@SpringBootApplication
public class StringToHexApplication extends Application {
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(StringToHexApplication.class, args);
        Application.launch(StringToHexApplication.class,args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("String Hex Tools");
        Scene scene = new Scene(createRoot());
        scene.getStylesheets().add(
                getClass().getResource("/stringtohex.css")
                        .toExternalForm());
        primaryStage.setScene(scene );
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/01.jpg"));
        primaryStage.show();
    }

    private Parent createRoot() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/stringtohex.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }

}

