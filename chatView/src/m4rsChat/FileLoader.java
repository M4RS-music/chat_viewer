package m4rsChat;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles all interactions between ".msg" files and "Controller".
 * Contains field "chatFile" that upon "loadFile()" stores the loaded ".msg" file
 * Requires class "ChatFile" to function.
 *
 * M4RS 6/8/21
 */

public class FileLoader {
    String lastName = null;
    static ChatFile chatFile = new ChatFile();

//    public boolean checkSyntax(FileInputStream fileInputStream){
//        /**
//         * Checks if the chatfile is correctly structured and alerts the user if it isn't
//         */
//        Scanner scanner = new Scanner(fileInputStream);
//        int amountPastBlankSpace = 0;
//
//        while (scanner.hasNextLine()) { //Scan fileIOStream nextLine
//            String line = scanner.nextLine();
//            Pattern upToColon = Pattern.compile(",.*$");
//            Matcher m1 = upToColon.matcher(line);
//            if(!m1.replaceFirst("").equals("Time")){
//                return true;
//            }
//        }
//        return false;
//    }



    public void loadFile(FileInputStream fileInputStream) {
        /**
         * Takes a FileInputStream and adds it line by line int "chatFile.fileArray" using "chatFile.addLine()"
         */
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) { //Scan fileIOStream nextLine
            chatFile.addLine(scanner.nextLine());
        }

        //System.out.println(chatFile.fileArray);
    }

    public Text[] flowMessage(int i){
        /**
         * Returns a Text[] array of three elements: Time, Name, Message Body. These elements are read from the chatFile
         * starting from message "i"
         */

        Text[] messageText = new Text[3];

        List<String> message = chatFile.accessMessage(i*3); //Access the 3 lines that constitute a message from pos i

        Pattern pattern = Pattern.compile("^(.*?)\\:"); //Match everything up to the first colon
        Matcher m1 = pattern.matcher(message.get(0));
        Matcher m2 = pattern.matcher(message.get(1));
        Matcher m3 = pattern.matcher(message.get(2));

        //System.out.println(m1.replaceFirst(""));

        messageText[0] = new Text("[" + m1.replaceFirst("") + "] "); //Create Text for <time of msg>

        if(lastName != null){ //Creating Text for and checking if sending "..." or <Name>
            if(lastName.equals(m2.replaceFirst(""))) {
            messageText[1] = new Text("...  ");
        }else {
                messageText[1] = new Text(m2.replaceFirst("") + ":  ");
            }}
        else if(lastName == null){
                messageText[1] = new Text(m2.replaceFirst("") + ":  ");
            }

        lastName = m2.replaceFirst(""); //Remember the current name to enable checking for repeat names
        //System.out.println(lastName);
        //System.out.println(m2.replaceFirst("") == lastName);
        messageText[2] = new Text(m3.replaceFirst("") + "\n"); //Create Text for <msg body>

        messageText[0].setFont(Font.font("Verdana", 12)); //Setting fonts and styling for Texts
        messageText[1].setFont(Font.font("Verdana", 12));
        messageText[1].setStyle("-fx-fill: blue;");
        messageText[2].setFont(Font.font("Verdana", 12));
        messageText[2].setStyle("-fx-font-weight: bold;");

        return messageText;

    }
}
