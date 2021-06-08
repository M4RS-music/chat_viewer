package m4rsChat;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;

import java.util.List;
import java.util.Scanner;

public class FileLoader {

    static ChatFile chatFile = new ChatFile();

    public void loadFile(FileInputStream fileInputStream) {
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            chatFile.addLine(scanner.nextLine());
        }

        System.out.println(chatFile.fileArray);
    }

    public Text flowFile(int i){

            List<String> message = chatFile.accessMessage(i);
            System.out.println(message);

            Text text = new Text(message.get(1));
            text.setFont(Font.font("Verdana", 25));
            return text;

    }
}
