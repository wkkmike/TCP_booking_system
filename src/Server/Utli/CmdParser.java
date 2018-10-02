package Server.Utli;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by lidawei on 07/04/2017.
 */
public class CmdParser {
    private String command;
    private ArrayList<String> arg = new ArrayList<String>();

    /**
     * constructor
     * @param command the input command
     */
    public CmdParser(String command){
        StringTokenizer st = new StringTokenizer(command, ",");
        while (st.hasMoreTokens()) {
            this.arg.add(st.nextToken());
        }
        if(arg.size() > 0){
            this.command = arg.get(0);
        }
        else this.command = "n";
    }

    /**
     * return a list of command split by " "
     * @return list of command
     */
    public ArrayList<String> getArg(){
        return this.arg;
    }

    /**
     * return the entire command
     * @return entrie emooand
     */
    public String getCmd(){
        return command;
    }
}
