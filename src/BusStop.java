import java.util.concurrent.Semaphore;

public class BusStop {
    //Track on waiting riders
    private volatile int waitingRiders = 0;

    private volatile Semaphore mutex = new Semaphore(1);

    //Semaphore to signal bus hass arrived
    private volatile Semaphore busArrived = new Semaphore(0);

    //Semaphore to signal a rider has boarded
    private volatile Semaphore ridersBoarded = new Semaphore(0);

    public Semaphore getMutex(){
        return mutex;
    }

    public void setWaitingRiders(int waitingRiders) {
        this.waitingRiders = waitingRiders;
    }

    public int getWaitingRiders() {
        return waitingRiders;
    }

    public Semaphore getBusArrived() {
        return busArrived;
    }

    public Semaphore getRidersBoarded() {
        return ridersBoarded;
    }

}
