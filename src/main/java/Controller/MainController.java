package Controller;

import Model.MyTimer;
import View.MainView;
import Exception.*;
import java.util.*;


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
            processInput(input);
        }
        while (!input.toLowerCase().equals("exit"));
    }



    private void processInput(String input){
        if(input.matches("\\s*start\\s+\\w+\\s*")){
            processStartingThread(input);
        }

        else if(input.toLowerCase().matches("\\s*stop\\s+\\w+\\s*")){
           processStoppingThread(input);
        }


        else if(input.toLowerCase().matches("\\s*check\\s*")){
            processCheckingAllThreads();
        }


        else if(input.toLowerCase().matches("\\s*check\\s+\\w+\\s*")){
           processCheckingOneThread(input);
        }

        else if(input.toLowerCase().matches("\\s*exit\\s*")){
            processExiting();
        }

        else {
            view.println("Wrong command!");
        }
    }



    private void processStartingThread(String input){
        String[] words = input.split("\\s+");
        int indexOfThreadName = 1;
        try{
            startThread(words[indexOfThreadName]);
        }
        catch (DuplicateThreadNameException e){
            view.println(e.getMessage());
        }
    }



    private void processStoppingThread(String input){
        String[] words = input.split("\\s+");
        int indexOfThreadName = 1;

        try{
            stopThread(words[indexOfThreadName]);
        }
        catch (NoSuchThreadException e){
            view.println(e.getMessage());
        }
    }



    private void processCheckingAllThreads(){
        view.printThreadsInfo(threads.toArray(new MyTimer[threads.size()]));
    }



    private void processCheckingOneThread(String input){
        String[] words = input.split("\\s+");
        int indexOfThreadName = 1;
        try {
            checkThread(words[indexOfThreadName]);
        } catch (NoSuchThreadException e) {
            view.println(e.getMessage());
        }
    }


    private void processExiting(){
        threads.stream().forEach(Thread::interrupt);
    }



    private void startThread(String threadName) throws DuplicateThreadNameException {
        Optional<MyTimer> thread = getThread(threadName);
        if(thread.isPresent()){
            if(isThreadStopped(thread.get())){
                threads.remove(thread.get());
                runThread(threadName);
            }
            else {
                throw new DuplicateThreadNameException("There is thread with this name already!");
            }
        }
        else {
            runThread(threadName);
        }
    }



    private void runThread(String threadName){
        MyTimer newThread = new MyTimer(threadName);
        threads.add(newThread);
        newThread.start();
    }



    private void checkThread(String threadName) throws NoSuchThreadException{
        Optional<MyTimer> thread = getThread(threadName);
        if(!thread.isPresent()){
            throw new NoSuchThreadException("There is no such thread with that name!");
        }
        view.printThreadsInfo(thread.get());
    }



    private void stopThread(String threadName) throws NoSuchThreadException{
        Optional<MyTimer> thread = getThread(threadName);
        if(!thread.isPresent()){
            throw new NoSuchThreadException("There is no such thread with that name!");
        }
        thread.get().interrupt();
    }



    private Optional<MyTimer> getThread(String threadName){
        Optional<MyTimer> timer;
        for (MyTimer thread: threads) {
            if(thread.getName().equals(threadName)){
                timer = Optional.of(thread);
                return timer;
            }
        }
        return Optional.empty();
    }



    private boolean isThreadStopped(MyTimer threadName){
        return threadName.isStopped();
    }
}
