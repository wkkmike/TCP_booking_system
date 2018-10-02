package Server.Utli;

import java.io.PrintStream;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by lidawei on 03/04/2017.
 */
public class ConsoleIO {

    /**
     * the scannet
     */
    protected Scanner input;
    /**
     * used for printing information to users when the program runs normally
     */
    protected PrintStream output;

    private Queue<String> inputTest;
    private Queue<String> outputTest;

    private boolean isTest;


    /**
     * constructor
     */
    public ConsoleIO(){
        input = new Scanner(System.in);
        output=new PrintStream(System.out);
    }

    /**
     * read a line
     * @return the content
     */
    public String readLine() {
        if(isTest){
            String nextLine = inputTest.element();
            inputTest.remove();
            return nextLine;
        }
        else{
            return input.nextLine();
        }
    }

    /**
     * print the line
     * @param str the information that will be shown to user
     */
    public void printLine(String str) {
        if(isTest){
            outputTest.add(str);
        }
        else{
            output.print(str);
        }
    }
}
