import java.util.InputMismatchException;
import java.util.Scanner;

public class Ej2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Show A + B; A - B; A * B; A / B");
        System.out.println("--------------------------------");
        System.out.print("Enter A: ");
        try {
            int a = sc.nextInt();
            System.out.print("Enter B: ");
            int b = sc.nextInt();

            System.out.println();
            System.out.println("Results:");
            System.out.printf("\tAddition: %s", a + b);
            System.out.println();
            System.out.printf("\tSubstraction: %s", a - b);
            System.out.println();
            System.out.printf("\tMultip: %s", a * b);
            System.out.println();
            if (b == 0) {
                System.out.println("\tDiv: Error, cant divide by cero");

            } else  {
                System.out.printf("\tDiv: %s", a / b);
            }
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("Input error");
        }


    }
}
