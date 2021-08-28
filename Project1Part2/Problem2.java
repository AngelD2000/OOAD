import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Ranger {
    Scanner scanner = new Scanner(System.in);
    //Calculates standard deviation of a list of numbers given the numbers' average
    double calcSD(List<Integer> nums, double average) //Found here: https://www.geeksforgeeks.org/java-program-to-calculate-standard-deviation/
    {
        double standardDeviation = 0;
        for (int i = 0; i < nums.size(); i++) {
            standardDeviation = standardDeviation + Math.pow((nums.get(i) - average), 2);
        }
        double sq = standardDeviation / (nums.size()-1);
        double res = Math.sqrt(sq);
        return res;
    }
    //Gets input from console
    String read(){
        System.out.println("Give me a range (two numbers seperated by comma)");
        String message = scanner.nextLine();
        //Remove whitespace from string (i.e. 0, 0 will be processed same as 0,0)
        return message.replaceAll("\\s+","");
    }
    //Gets a list of n integers within a range min-max (inclusive)
    List<Integer> getNRandom(int min, int max, int n){
        List<Integer> nums = new ArrayList<Integer>();
        for (int j = 0; j < n; j++) {
            int num = ThreadLocalRandom.current().nextInt(min, max + 1);
            nums.add(num);
        }
        return nums;
    }
    //Prints to console (string)
    void write(String output){
        System.out.println(output);
    }
    //Prints to console (list)
    void write(List<Integer> output){
        System.out.println(output);
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Boolean going = true;
        int increment = 10;
        int times = 10;
        //Get input from console until doiuble 0 has been entered
        while (going){
            Ranger ranger = new Ranger();
            String message = ranger.read();
            //Check if double 0 entered to end program
            if (message.equals("0,0")){
                going = false;
            }
            else{
                //Check there is comma in list
                int commaPos = message.indexOf(",");
                if (commaPos == -1){
                    ranger.write("Please seperate your numbers by commas");
                }
                else if(message.indexOf(".") != -1 | message.indexOf("-") != -1){
                    ranger.write("Please only enter positive integers");
                }
                else{
                    //Find ends of range and check valid
                    List<Integer> nums = new ArrayList<Integer>();
                    int min = Integer.parseInt(message.substring(0, commaPos));
                    int max = Integer.parseInt(message.substring(commaPos+1, message.length()));
                    if (min >= max){
                        ranger.write("Make sure end of range is larger than start of range");
                    }
                    else{
                        //Create 100 vals in range 10 at a time and print list, average, and SD each time
                        for (int i = 0; i < times; i++) {
                            //Get 10 random values in range
                            nums.addAll(ranger.getNRandom(min, max, increment));
                            //Print list and some maths
                            ranger.write(nums);
                            ranger.write("Current Size: " + nums.size());
                            Double average = nums.stream().mapToInt(val -> val).average().orElse(0.0); //Method found here: https://stackoverflow.com/questions/10791568/calculating-average-of-an-array-list
                            ranger.write("Average: " + average);
                            double standardDev = ranger.calcSD(nums, average);
                            ranger.write("Standard Devation: " + standardDev);
                        }
                    }
                }
            }
        }
    }
}