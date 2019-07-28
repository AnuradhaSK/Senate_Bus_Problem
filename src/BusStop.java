import java.util.concurrent.Semaphore;

public class BusStop {
    //Track on waiting riders
    private static volatile int waitingRiders = 0;

    private static volatile Semaphore mutex = new Semaphore(1);

    //Semaphore to signal bus has arrived
    private static volatile Semaphore busArrived = new Semaphore(0);

    //Semaphore to signal a rider has boarded
    private static volatile Semaphore ridersBoarded = new Semaphore(0);

    public static Semaphore getMutex(){
        return mutex;
    }

    public static void setWaitingRiders(int waitingRiders) {
        BusStop.waitingRiders = waitingRiders;
    }

    public static int getWaitingRiders() {
        return waitingRiders;
    }

    public static Semaphore getBusArrived() {
        return busArrived;
    }

    public static Semaphore getRidersBoarded() {
        return ridersBoarded;
    }

}
