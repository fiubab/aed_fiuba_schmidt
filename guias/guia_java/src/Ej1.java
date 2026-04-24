import java.util.InputMismatchException;
import java.util.Scanner;

public class Ej1 {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        try {
            int number = scanner.nextInt();
            System.out.printf("your number is: %s", number);
            return;
        } catch (InputMismatchException e) {
            System.out.println("Not an int");
            return;
        }
    }
}
