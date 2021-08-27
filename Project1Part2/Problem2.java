import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class calculateSD {
    double SD(List<Integer> nums, double average) //Found here: https://www.geeksforgeeks.org/java-program-to-calculate-standard-deviation/
    {
        int n = nums.size();
        double standardDeviation = 0;
        for (int i = 0; i < n; i++) {
            standardDeviation += Math.pow((nums.get(i) - average), 2);
        }
        double sq = standardDeviation / n;
        double res = Math.sqrt(sq);
        return res;
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Boolean going = true;
        int increment = 10;
        int times = 10;
        Scanner scanner = new Scanner(System.in);
        //Get input from console until doiuble 0 has been entered
        while (going){
            System.out.println("Give me a range (two numbers seperated by comma)");
            String message = scanner.nextLine();
            //Remove whitespace from string (i.e. 0, 0 will be processed same as 0,0)
            message = message.replaceAll("\\s+","");
            //Check if double 0 entered to end program
            if (message.equals("0,0")){
                going = false;
            }
            //Change string to uppercase and sort; then print
            else{
                //Check there is comma in list
                int commaPos = message.indexOf(",");
                if (commaPos == -1){
                    System.out.println("Please seperate your numbers by commas");
                }
                else{
                    //Create an array of 100 values 10 at a time
                    List<Integer> nums = new ArrayList<Integer>();
                    for (int i = 0; i < times; i++) {
                        //Get 10 random values in range
                        int min = Integer.parseInt(message.substring(0, commaPos));
                        int max = Integer.parseInt(message.substring(commaPos+1, message.length()));
                        for (int j = 0; j < increment; j++) {
                            int num = ThreadLocalRandom.current().nextInt(min, max + 1);
                            nums.add(num);
                        }
                        //Print list and some maths
                        System.out.println(nums);
                        Double average = nums.stream().mapToInt(val -> val).average().orElse(0.0); //Method found here: https://stackoverflow.com/questions/10791568/calculating-average-of-an-array-list
                        System.out.println("Current Size: " + nums.size());
                        System.out.println("Average: " + average);
                        calculateSD calcSd = new calculateSD();
                        double standardDev = calcSd.SD(nums, average);
                        System.out.println("Standard Devation: " + standardDev);
                    }
                }
            }
        }
        scanner.close();
    }
}