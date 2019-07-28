import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        float busArrivalMeanTime = 20 * 60f * 1000 ;
        float riderArrivalMeanTime = 30f * 1000;
        /*Uncomment below two lines and comment above two lines when testing*/
//        float busArrivalMeanTime = 5;
//        float riderArrivalMeanTime = 1;


        RiderGenerator riderGenerator = new RiderGenerator(riderArrivalMeanTime);
        (new Thread(riderGenerator)).start();

        BusGenerator busGenerator = new BusGenerator(busArrivalMeanTime);
        (new Thread(busGenerator)).start();

        Scanner scanner = new Scanner(System.in);
        String input;
        String termination = "q";

        while(true){
            input = scanner.nextLine();
            if(input.equals(termination)){
                System.exit(0);
            }

        }
    }
}
