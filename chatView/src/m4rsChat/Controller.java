package m4rsChat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * Controls the GUI elements and enables file loading and text displaying actions
 *
 * M4RS 6/8/21
 */

public class Controller {


    public TextField fileName;
    public Button openButton;
    public MenuItem fileClose;
    public MenuItem fileOpen;
    public TextFlow textFlow;
    public Slider fontSizeSlider;


    public void fileOpen(ActionEvent actionEvent) {
        /**
         * Creates an instance of FileLoader and Loads a file with it creating an instance of ChatFile that is then used
         * to display the messages in the file on textFlow
         */

        textFlow.getChildren().removeAll(); //clears out all text in textflow
        textFlow.getChildren().clear();

        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Select a chat log");
        fileChoose.setInitialDirectory(new File("./"));
        File file = fileChoose.showOpenDialog(Main.stage);

        if(file == null){
            return;
        }
        else{
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                Path path = Path.of(file.getAbsolutePath());
                System.out.println(path);
                System.out.println(path.relativize(path));
                fileName.setText(String.valueOf(path));
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                return;
            }
            //System.out.println(file.getAbsolutePath());

            FileLoader fileLoader = new FileLoader();
            fileLoader.loadFile(fileInputStream);


            for(int i=0; i<FileLoader.chatFile.numberOfMessages(); i++) {
                Text[] messageText = fileLoader.flowMessage(i);
                textFlow.getChildren().addAll(messageText[0], messageText[1], messageText[2]);
            }
        }


    }

    public void fileClose(ActionEvent actionEvent) {
        /**
         * Closes the application when user clicks menu File>Close
         */
        Platform.exit();
    }

    public void openButton(ActionEvent actionEvent) {
        /**
         * Opens file at path in fileName text field
         */

        System.out.println(fileName.getText());
        textFlow.getChildren().removeAll(); //clears out all text in textflow
        textFlow.getChildren().clear();
        //textFlow = new TextFlow();

        File file = new File(fileName.getText());

        if(file == null){
            return;
        }
        else{
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                Path path = Path.of(file.getAbsolutePath());
                System.out.println(path);
                System.out.println(path.relativize(path));
                fileName.setText(String.valueOf(path));
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                return;
            }
            //System.out.println(file.getAbsolutePath());

            FileLoader fileLoader = new FileLoader();
            fileLoader.loadFile(fileInputStream);


            for(int i=0; i<FileLoader.chatFile.numberOfMessages(); i++) {
                Text[] messageText = fileLoader.flowMessage(i);
                textFlow.getChildren().addAll(messageText[0], messageText[1], messageText[2]);
            }
        }
    }

    public void mouseClick(MouseEvent mouseEvent) {
       // System.out.println(mouseEvent.getSceneX());
       // System.out.println(mouseEvent.getX());

        // create text
        Text text_1 = new Text("GeeksforGeeks\n");

        // set the text color
        //text_1.setFill(Color.RED);

        // set font of the text
        text_1.setFont(Font.font("Verdana", 25));

        // create text
        Text text_2 = new Text("The computer science portal for geeks");

        // set the text color
        text_2.setStyle("-fx-text-base-color: white;");

        // set font of the text
        text_2.setFont(Font.font("Sans", FontPosture.ITALIC, 35));

        textFlow.getChildren().addAll(text_1, text_2);
    }

    public void changeFontSizeMenu(MouseEvent mouseEvent) {
       // textFlow.set(Font.font("Sans", FontPosture.ITALIC, fontSizeSlider.getValue()));
        textFlow.setStyle("-fx-font: " + fontSizeSlider.getValue() + " Sans");
        //textFlow.setStyle("-fx-background-color: blue");
        //textFlow.requestLayout();
        System.out.println("grad");
    }
}
