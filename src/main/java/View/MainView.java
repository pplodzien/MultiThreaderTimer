package View;

import Model.MyTimer;

import java.util.List;

public class MainView {


    public void println(String string){
        System.out.println(string);
    }



    public void print(String string){
        System.out.print(string);
    }


    public void printThreadsInfo(List<MyTimer> threads){
        threads.stream().forEach( thread -> System.out.println("Name: " + thread.getName() + ", ThreadId: " + thread.getId() + ", Seconds: " + thread.getTimeInSeconds()));
    }


}
