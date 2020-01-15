package Model;

public class MyTimer extends Thread {

    float time;
    boolean stopped;

    public MyTimer(String name){
        super(name);
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                time += 100;
            }

            catch (InterruptedException e) {
                stopped = true;
                break;
            }
        }
    }

    public boolean isStopped(){
        return stopped;
    }


    public float getTimeInSeconds(){
        return time/1000;
    }


}
