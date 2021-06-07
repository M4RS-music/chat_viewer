package m4rsChat;

import java.io.FileInputStream;

import java.util.Scanner;

public class FileLoader {

    ChatFile chatFile = new ChatFile();

    public void loadFile(FileInputStream fileInputStream) {
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            chatFile.addLine(scanner.nextLine());
        }

        System.out.println(chatFile.fileArray);
    }
}
