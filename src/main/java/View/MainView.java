package View;

import Model.MyTimer;
import java.util.Arrays;


public class MainView {


    public void println(String string){
        System.out.println(string);
    }


    public void print(String string){
        System.out.print(string);
    }


    public void printThreadsInfo(MyTimer... threads){
        Arrays.stream(threads).forEach( thread -> System.out.println("Name: " + thread.getName() + ", ThreadId: "
                + thread.getId() + ", Seconds: " + thread.getTimeInSeconds()));
    }
}
