import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import m4rsChat.Main;

import java.io.File;

public class Controller {


    public TextField fileName;
    public Button openButton;
    public MenuItem fileClose;
    public MenuItem fileOpen;

    public void fileOpen(ActionEvent actionEvent) {
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Select a chat log");
        //fileChoose.setSelectedExtensionFilter(msg);
        //fileChoose.setInitialDirectory();
        File file = fileChoose.showOpenDialog(Main.stage);

        if(file == null){
            return;
        } else{
        System.out.println(file.getAbsolutePath());}

    }

    public void fileClose(ActionEvent actionEvent) {
    }

    public void openButton(ActionEvent actionEvent) {
    }
}
