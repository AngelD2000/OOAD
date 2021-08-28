import java.util.Scanner;
import java.util.Arrays;

class Sorter {
    Scanner scanner = new Scanner(System.in);
    //Gets string from user input
    String read(){
        System.out.println("Give me a string");
        String message = scanner.nextLine();
        //Remove whitespace from string (i.e. 0, 0 will be processed same as 0,0)
        return message;
    }
    //Turns string to upper case, sorts it, writes it back to console
    void process(String message){
        //Uppercase whole string method found here: https://www.w3schools.com/java/ref_string_touppercase.asp
        message = message.toUpperCase();
        message = sort(message);
        write(message);
    }
    //Sorts string alphabetically
    String sort(String message){
        //Sorting method found here: https://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
        char tempArray[] = message.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
    //Writes string to console
    void write(String output){
        System.out.println(output);
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Boolean going = true;
        Sorter sorter = new Sorter();
        //Get input from console until null string has been entered
        while (going){
            String message = sorter.read();
            //Check if null string entered to end program
            if (message.equals("")){
                going = false;
            }
            //Change string to uppercase and sort; then print
            else{
                sorter.process(message);
            }
        }
    }
}