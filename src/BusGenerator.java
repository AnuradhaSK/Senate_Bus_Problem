import java.util.Random;

public class BusGenerator implements Runnable {
    private float busArrivalMeanTime;
    Random random;

    public BusGenerator(float busArrivalMeanTime) {
        this.busArrivalMeanTime = busArrivalMeanTime;
        random = new Random();
    }

    @Override
    public void run() {
        int busNumber = 1;
        while (!Thread.currentThread().isInterrupted()) {
            Bus bus = new Bus(busNumber);
            (new Thread(bus)).start();
            busNumber++;
            try {
                Thread.sleep(getRandomVariateBusArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Bus arriving is done!");
    }

    public long getRandomVariateBusArrivalTime() {
        float lambda = 1 / busArrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
