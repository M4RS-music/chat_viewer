package m4rsChat;

import java.util.ArrayList;
import java.util.List;

/**
 * Data structure for storing imported ".msg" files
 * Data stored in fileArray
 *
 * M4RS 6/8/21
 */


public class ChatFile {
    List<String> fileArray = new ArrayList<String>();


    public void addLine(String nextLine) {
        /**
         * Add a new line of ".msg" information to fileArray
         */

        if(nextLine.length() != 0){
            fileArray.add(nextLine);
        }
    }

    public int numberOfMessages(){
        /**
         * Returns the amount of messages stored in fileArray minus 1 (for iteration purposes)
         */

        return (fileArray.size()/3)-1;
    }

    public List<String> accessMessage(int i){
        /**
         * Returns a list of 3 strings corresponding to 3 lines of ".msg" data
         */

        List<String> message = new ArrayList<String>();
        for(int x = 0; x<3; x++){
            message.add(fileArray.get(i+x));
        }
       // System.out.println(message);
        return message;

    }

    public void clearFileArray(){
        /**
         * Clears out data from the previously loaded file
         */
        fileArray.removeAll(fileArray);
    }
}
