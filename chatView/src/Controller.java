import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import m4rsChat.FileLoader;
import m4rsChat.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {


    public TextField fileName;
    public Button openButton;
    public MenuItem fileClose;
    public MenuItem fileOpen;

    public void fileOpen(ActionEvent actionEvent) {
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Select a chat log");
        //fileChoose.setInitialDirectory();
        File file = fileChoose.showOpenDialog(Main.stage);

        if(file == null){
            return;
        }
        else{
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                return;
            }
            System.out.println(file.getAbsolutePath());

            FileLoader fileLoader = new FileLoader();
            fileLoader.loadFile(fileInputStream);

        }

    }

    public void fileClose(ActionEvent actionEvent) {
    }

    public void openButton(ActionEvent actionEvent) {
    }
}
