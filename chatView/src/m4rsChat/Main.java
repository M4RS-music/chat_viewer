package m4rsChat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("chatView_m4rs");
        primaryStage.setScene(new Scene(root, 920, 880));
        primaryStage.setResizable(true);
        primaryStage.show();

        stage = primaryStage;



    }


    public static void main(String[] args) {
        launch(args);
    }
}
