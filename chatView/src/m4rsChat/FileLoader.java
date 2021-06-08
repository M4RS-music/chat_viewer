package m4rsChat;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import java.io.FileInputStream;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLoader {
    String lastName = null; //yes its cheap,


    static ChatFile chatFile = new ChatFile();

    public void loadFile(FileInputStream fileInputStream) {
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            chatFile.addLine(scanner.nextLine());
        }

        System.out.println(chatFile.fileArray);
    }

    public Text[] flowFile(int i){
        Text[] messageText = new Text[3];

        List<String> message = chatFile.accessMessage(i*3);
        //System.out.println(message);
        Pattern pattern = Pattern.compile("^(.*?)\\:");
        Matcher m1 = pattern.matcher(message.get(0));
        Matcher m2 = pattern.matcher(message.get(1));
        Matcher m3 = pattern.matcher(message.get(2));

        System.out.println(m1.replaceFirst(""));

        messageText[0] = new Text("[" + m1.replaceFirst("") + "] ");

        if(lastName != null){
            if(lastName.equals(m2.replaceFirst(""))) {
            messageText[1] = new Text("...");
        }else {
                messageText[1] = new Text(m2.replaceFirst("") + " ");
            }}
        else if(lastName == null){
                messageText[1] = new Text(m2.replaceFirst("") + " ");
            }

        lastName = m2.replaceFirst("");
        System.out.println(lastName);
        System.out.println(m2.replaceFirst("") == lastName);
        messageText[2] = new Text(m3.replaceFirst("") + "\n");

        messageText[0].setFont(Font.font("Verdana", 35));
        messageText[1].setFont(Font.font("Verdana", 35));
        messageText[1].setStyle("-fx-text-fill: blue;");
        messageText[2].setFont(Font.font("Verdana", 35));
        messageText[2].setStyle("-fx-font-weight: bold;");
        return messageText;

    }
}
