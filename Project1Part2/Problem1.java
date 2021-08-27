import java.util.Scanner;
import java.util.Arrays;

public class Problem1 {
    public static void main(String[] args) {
        Boolean going = true;
        Scanner scanner = new Scanner(System.in);
        while (going){
            System.out.println("Give me a string");
            String message = scanner.nextLine();
            //Check if null string entered to end program
            if (message.equals("")){
                going = false;
            }
            //Change string to uppercase and sort
            else{
                //Uppercase whole string method found here: https://www.w3schools.com/java/ref_string_touppercase.asp
                message = message.toUpperCase();
                //Sorting method found here: https://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
                char tempArray[] = message.toCharArray();
                Arrays.sort(tempArray);
                System.out.println(new String(tempArray));
            }
        }
        scanner.close();
    }
}
