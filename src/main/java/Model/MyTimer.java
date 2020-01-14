package Model;

public class MyTimer implements Runnable {
    int time;

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                time += 10;
                }

             catch (InterruptedException e) {
                break;
            }
        }
    }
    }
