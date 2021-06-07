package m4rsChat;

import java.util.ArrayList;
import java.util.List;

public class ChatFile {
    List<String> fileArray = new ArrayList<String>();


    public void addLine(String nextLine) {
        if(nextLine.length() != 0){
            fileArray.add(nextLine);
        }
    }
}
