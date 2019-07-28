import java.util.concurrent.Semaphore;

public class Bus implements Runnable{
    private final int BUS_CAPACITY= 50;
    private int busNumber;

    public Bus(int busNumber){
        this.busNumber = busNumber;
    }

    @Override
    public void run() {
        try {
            arrived();
            BusStop.getMutex().acquire();
            int allowedRiders = Math.min(BusStop.getWaitingRiders(),BUS_CAPACITY);
            for(int rider=0;rider < allowedRiders;rider++){
                BusStop.getBusArrived().release();
                BusStop.getRidersBoarded().acquire();
            }
            BusStop.setWaitingRiders(Math.max(BusStop.getWaitingRiders()-BUS_CAPACITY,0));
            BusStop.getMutex().release();
            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void depart(){
        System.out.println("Bus No: "+ busNumber + " depart and Waiting rider count : " + BusStop.getWaitingRiders());
    }

    public void arrived(){
        System.out.println("Bus No: "+ busNumber + " arrived");
    }
}
