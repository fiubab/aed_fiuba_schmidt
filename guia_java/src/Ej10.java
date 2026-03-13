import java.util.InputMismatchException;
import java.util.Scanner;

public class Ej10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter A: ");
            int a = sc.nextInt();
            System.out.print("Enter B: ");
            int b = sc.nextInt();
            System.out.println("""
                    Pick an option:
                        1. Add
                        2. Sub
                        3. Mult
                        4. Div
                    """);
            System.out.print("Choose: ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.printf("%s + %s = %s", a, b, a + b);
                    break;
                case 2:
                    System.out.printf("%s - %s = %s", a, b, a - b);
                    break;
                case 3:
                    System.out.printf("%s * %s = %s", a, b, a * b);
                    break;
                case 4:
                    if (b == 0) {
                        System.out.println("\tDiv: Error, cant divide by cero");

                    } else  {
                        System.out.printf("%s / %s = %s", a, b, a / b);
                    }
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("Input error");
        }
    }
}
