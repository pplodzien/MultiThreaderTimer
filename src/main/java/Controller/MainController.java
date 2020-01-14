package Controller;

import View.MainView;

import java.util.Scanner;

public class MainController {
    MainView view;
    Scanner scanner;


    public MainController(){
        this.view = new MainView();
        this.scanner = new Scanner(System.in);
    }


    public void run(){
        String input;
        do{
            view.print("Command?");
            input = scanner.next();
            makeDecision(input);
        }
        while (!input.equals("exit"));
    }


    private void makeDecision(String input){

    }


}
