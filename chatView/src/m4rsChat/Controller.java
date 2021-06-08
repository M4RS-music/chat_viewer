package m4rsChat;

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

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Controller {


    public TextField fileName;
    public Button openButton;
    public MenuItem fileClose;
    public MenuItem fileOpen;
    public TextFlow textFlow;


    public void fileOpen(ActionEvent actionEvent) {
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
                fileName.setText(file.getAbsolutePath());
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                return;
            }
            System.out.println(file.getAbsolutePath());

            FileLoader fileLoader = new FileLoader();
            fileLoader.loadFile(fileInputStream);


            for(int i=0; i<FileLoader.chatFile.numberOfMessages(); i++) {
                Text[] messageText = fileLoader.flowFile(i);
                textFlow.getChildren().addAll(messageText[0], messageText[1], messageText[2]);
            }
        }


    }

    public void fileClose(ActionEvent actionEvent) {
    }

    public void openButton(ActionEvent actionEvent) {
        System.out.println(fileName.getText());
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
        //text_2.setFill(Color.BLUE);

        // set font of the text
        text_2.setFont(Font.font("Sans", FontPosture.ITALIC, 15));

        textFlow.getChildren().addAll(text_1, text_2);
    }

}
