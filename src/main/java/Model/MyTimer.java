package Model;

public class MyTimer extends Thread {

    float time;

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
                break;
            }
        }
    }


    public float getTimeInSeconds(){
        return time/1000;
    }


}
