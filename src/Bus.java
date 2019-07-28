import java.util.concurrent.Semaphore;

public class Bus implements Runnable{
    private final int BUS_CAPACITY= 50;
    private int busNumber;
    private BusStop busStop;

    public Bus(int busNumber, BusStop busStop){
        this.busNumber = busNumber;
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            busStop.getMutex().acquire();
            int allowedRiders = Math.min(busStop.getWaitingRiders(),BUS_CAPACITY);
            for(int rider=0;rider < allowedRiders;rider++){
                busStop.getBusArrived().release();
                busStop.getRidersBoarded().acquire();
            }
            busStop.setWaitingRiders(Math.max(busStop.getWaitingRiders()-BUS_CAPACITY,0));
            busStop.getMutex().release();

            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void depart(){
        System.out.println("Bus No: "+ busNumber + " depart");
    }

    public void arrived(){
        System.out.println("Bus No: "+ busNumber + " arrived");
    }
}
