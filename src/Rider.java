public class Rider implements Runnable{

    private int riderNumber;
    private BusStop busStop;

    public Rider(int riderNumber, BusStop busStop){
        this.riderNumber = riderNumber;
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            busStop.getMutex().acquire();
            busStop.setWaitingRiders(busStop.getWaitingRiders()+1);
            enterBusStop();
            busStop.getMutex().release();

            busStop.getBusArrived().acquire();
            boardBus();
            busStop.getRidersBoarded().release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterBusStop(){
        System.out.println("Rider "+riderNumber+ " enters the bus stop.");
    }

    public void boardBus(){
        System.out.println("Rider "+riderNumber+ " board the bus");
    }
}
