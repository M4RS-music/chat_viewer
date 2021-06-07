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

    public int numberOfMessages(){
        return (fileArray.size()/3)-1;
    }

    public List<String> accessMessage(int i){
        List<String> message = new ArrayList<String>();
        for(int x = 0; x<2; x++){
            message.add(fileArray.get(i+0));
        }

        return message;
    }
}
