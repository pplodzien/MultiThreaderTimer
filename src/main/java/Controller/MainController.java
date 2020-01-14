package Controller;

import Model.MyTimer;
import View.MainView;
import Exception.DuplicateThreadNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController {
    MainView view;
    Scanner scanner;
    List<MyTimer> threads;


    public MainController(){
        this.view = new MainView();
        this.scanner = new Scanner(System.in);
        this.threads = new ArrayList<>();
    }


    public void run(){
        String input;
        do{
            view.println("Command?");
            input = scanner.nextLine();
            makeDecision(input);
        }
        while (!input.toLowerCase().equals("exit"));
    }


    private void makeDecision(String input){
        if(input.matches("\\s*start\\s+\\w+\\s*")){
            String[] words = input.split("\\s+");
            int indexOfThreadName = 1;
            try{
                startNewThread(words[indexOfThreadName]);
            }
            catch (DuplicateThreadNameException e){
                view.println(e.getMessage());
            }
        }

        else if(input.toLowerCase().matches("\\s*check\\s*")){
            view.printThreadsInfo(threads);
        }

        else if(input.toLowerCase().matches("\\s*exit\\s*")){
            threads.stream().forEach(Thread::interrupt);
        }
    }


    private void startNewThread(String threadName) throws DuplicateThreadNameException {
            if (threadNameAlreadyUsed(threadName)){
                throw new DuplicateThreadNameException("There is thread with this name already!");
            }

            MyTimer thread = new MyTimer(threadName);
            threads.add(thread);
            thread.start();
    }


    private boolean threadNameAlreadyUsed(String threadName){
        return threads.stream().anyMatch(name -> threadName.equals(name.getName()));
    }






}
