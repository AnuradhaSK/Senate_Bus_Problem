public class Rider implements Runnable{

    private int riderNumber;

    public Rider(int riderNumber){
        this.riderNumber = riderNumber;
    }

    @Override
    public void run() {
        try {
            BusStop.getMutex().acquire();
            enterBusStop();
            BusStop.setWaitingRiders(BusStop.getWaitingRiders()+1);
            BusStop.getMutex().release();

            BusStop.getBusArrived().acquire();
            boardBus();
            BusStop.getRidersBoarded().release();

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
