import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Decoder {
    Scanner scanner = new Scanner(System.in);

    //Gets input from console
    String read(){
        System.out.println("Enter a list of ASCII codes seperated by commas");
        String message = scanner.nextLine();
        //Remove whitespace from string (i.e. 0, 0 will be processed same as 0,0)
        return message.replaceAll("\\s+","");
    }

    //Finds type of character from ASCII code and prints it
    void decode(int ASCIICode) {
        // ASCII Ranges from chart at: https://www.ascii-code.com/
        int[] lowercaseBounds = new int[]{97,122};
        int[] uppercaseBounds = new int[]{65,90};
        int[] digitBounds = new int[]{48,57};
        int[] whitespaceBounds = new int[]{9,13,32};
        int[][] specialBounds = new int[][]{{33,47}, {58,64}, {91,96}, {123,126}};

        String type = "";
        // Find type of range of ASCII character using ranges from ASCII table
        if (ASCIICode >= lowercaseBounds[0] && ASCIICode <= lowercaseBounds[1]) {
            type = "Lower Case";
        }
        else if (ASCIICode >= uppercaseBounds[0] && ASCIICode <= uppercaseBounds[1]) {
            type = "Upper Case";
        }
        else if (ASCIICode >= digitBounds[0] && ASCIICode <= digitBounds[1]) {
            type = "Digit";
        }
        else if (ASCIICode >= whitespaceBounds[0] && ASCIICode <= whitespaceBounds[1] || ASCIICode == whitespaceBounds[2]) {
            type = "White Space";
        }
        else {
            type = "Other/Non-Printable";
            // Loop over special character ranges as there are multiple
            for(int[] range:specialBounds) {
                if (ASCIICode >= range[0] && ASCIICode <= range[1]) {
                    type = "Special Character";
                    break;
                }
            }
        }

        // Create string with ASCII code, type, and character
        System.out.println("Code: " + ASCIICode + " \tType: " + type + " \tChar: " + (char)ASCIICode);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Boolean going = true;

        //Get input from console
        while (going){
            Decoder decoder = new Decoder();
            String message = decoder.read();

            //Check there is comma in list and list only contains numbers
            Matcher m = Pattern.compile("[0-9, /,]+").matcher(message);
            if (!m.matches()){
                System.out.println("Please separate your input by commas and/or only enter numbers");
            }
            else {
                //Convert message to list of since ASCII codes to decode
                String[] ASCIIVals = message.split(",");
                //Decode each ASCII code individually
                for(String val:ASCIIVals) {
                    decoder.decode(Integer.parseInt(val));
                }
            }
        }
    }
}