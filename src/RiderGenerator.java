import java.util.Random;

public class RiderGenerator implements Runnable {
    private float riderArrivalMeanTime;
    Random random;

    public RiderGenerator(float riderArrivalMeanTime) {
        this.riderArrivalMeanTime = riderArrivalMeanTime;
        random = new Random();
    }

    @Override
    public void run() {
        int riderNumber = 1;
        while (!Thread.currentThread().isInterrupted()) {
            Rider rider = new Rider(riderNumber);
            (new Thread(rider)).start();
            riderNumber++;
            try {
                Thread.sleep(getRandomVariateRiderArrivalTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Rider arriving is done!");
    }

    public long getRandomVariateRiderArrivalTime() {
        float lambda = 1 / riderArrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
