package m4rsChat;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String lastPath = "./";



    public void fileOpen(ActionEvent actionEvent) {
        /**
         * Creates an instance of FileLoader and Loads a file with it creating an instance of ChatFile that is then used
         * to display the messages in the file on textFlow
         */

        textFlow.getChildren().removeAll(); //clears out all text in textflow
        textFlow.getChildren().clear();
        fileName.setText("LOADING FILE");

        FileChooser fileChoose = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MSG files (*.msg)", "*.msg");
        fileChoose.getExtensionFilters().add(extFilter);

        fileChoose.setTitle("Select a chat log");
        fileChoose.setInitialDirectory(new File(lastPath));
        File file = fileChoose.showOpenDialog(Main.stage);


        if (file == null) {
            fileName.setText(null);
            return;
        } else {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                Path path = Path.of(file.getAbsolutePath());
                //System.out.println(path);
                //System.out.println(path.relativize(path));
                fileName.setText(String.valueOf(path));
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                return;
            }
            //System.out.println(file.getAbsolutePath());

            FileLoader fileLoader = new FileLoader();
            fileLoader.chatFile.clearFileArray();
//            if(fileLoader.checkSyntax(fileInputStream)){
//                new Alert(Alert.AlertType.ERROR, "Error file syntax", ButtonType.OK);
//            }
            fileLoader.loadFile(fileInputStream);
            lastPath = file.getParent();


            for (int i = 0; i <= FileLoader.chatFile.numberOfMessages(); i++) {
                Text[] messageText = fileLoader.flowMessage(i);
                textFlow.getChildren().addAll(messageText[0], messageText[1], messageText[2]);
//                Text text1 = messageText[2];
//                if (text1.getText().contains(":)")) {
//                    //System.out.println("moji match");
//                    ImageView imageView = new ImageView("http://files.softicons.com/download/web-icons/network-and-security-icons-by-artistsvalley/png/16x16/Regular/Friend%20Smiley.png");
//                    // Remove :) from text
//                    text1.setText(text1.getText().replace(":)", " "));
//                    textFlow.getChildren().addAll(text1, imageView);
//                }else if (text1.getText().contains(":(")) {
//                    //System.out.println("moji match");
//                    ImageView imageView = new ImageView("https://files.softicons.com/download/web-icons/august-icon-set-by-austintheheller/ico/Smiley%20Confused.ico");
//                    // Remove :) from text
//                    text1.setText(text1.getText().replace(":)", " "));
//                    textFlow.getChildren().addAll(text1, imageView);
//                }
//
//                else {
//                    textFlow.getChildren().add(text1);
//                }
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

        //System.out.println(fileName.getText());
        textFlow.getChildren().removeAll(); //clears out all text in textflow
        textFlow.getChildren().clear();


        File file;
        try {
            file = new File(fileName.getText());
            //Path path = Path.of(file.getAbsolutePath());
            //System.out.println(path);
            //System.out.println(path.relativize(path));
            //fileName.setText(String.valueOf(path));
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "File Name Empty", ButtonType.OK).showAndWait();
            System.out.println("ciowouf");
            fileName.setText(null);
            return;
        }
        fileName.setText("LOADING FILE");

        Pattern pattern = Pattern.compile("^.*(?=(\\.msg))"); //Match everything up to the first colon
        Matcher m1 = pattern.matcher(file.getAbsolutePath());

        if(!m1.replaceFirst("").equals(".msg")){
            new Alert(Alert.AlertType.ERROR, "File Does Not Have A '.msg' Extension", ButtonType.OK).showAndWait();
            fileName.setText(null);
            return;
        }

        //System.out.println(file.exists());
        if(!file.exists()){
            new Alert(Alert.AlertType.ERROR, "File Does Not Exist", ButtonType.OK).showAndWait();
            fileName.setText(null);
            return;
        }
        else{
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                //Path path = Path.of(file.getAbsolutePath());
                //System.out.println(path);
                //System.out.println(path.relativize(path));
                //fileName.setText(String.valueOf(path));
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                return;
            }
            //System.out.println(file.getAbsolutePath());

            FileLoader fileLoader = new FileLoader();
            fileLoader.chatFile.clearFileArray();
            fileLoader.loadFile(fileInputStream);


            for(int i=0; i<=FileLoader.chatFile.numberOfMessages(); i++) {
                Text[] messageText = fileLoader.flowMessage(i);
                textFlow.getChildren().addAll(messageText[0], messageText[1], messageText[2]);
            }
        }
    }

    public void mouseClick(MouseEvent mouseEvent) {
       // System.out.println(mouseEvent.getSceneX()); //just test function
       // System.out.println(mouseEvent.getX());

        // create text
       // Text text_1 = new Text("GeeksforGeeks\n");
////
        // set the text color
        //text_1.setFill(Color.RED);

        // set font of the text
    //    text_1.setFont(Font.font("Verdana", 25));

        // create text
       // Text text_2 = new Text("The computer science portal for geeks");

        // set the text color
       // text_2.setStyle("-fx-text-base-color: white;");

        // set font of the text
        ///text_2.setFont(Font.font("Sans", FontPosture.ITALIC, 35));

       // textFlow.getChildren().addAll(text_1, text_2);
    }

}
