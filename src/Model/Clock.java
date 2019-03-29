package Model;

public class Clock implements Runnable {

    private long start;
    private long end;
    private boolean isFinished;
    private static Clock clock;

    public Clock() {

    }

    public void start() {
        this.isFinished = false;
        this.start = System.currentTimeMillis();
    }

    public void end() {
        this.end = System.currentTimeMillis();
    }

    public long getTime() {
        return (System.currentTimeMillis() - this.start) / 1000;
    }

    public void sendSignal() {
        this.isFinished = true;
    }

    public long getAllTime() {
        return (this.end - this.start) / 1000;
    }

    @Override
    public void run() {
        this.start();
        while(!this.isFinished);
        this.end();
    }

    public static Clock getInstance() {
        if (Clock.clock == null) {
            Clock.clock = new Clock();
        }
        return Clock.clock;
    }
}
