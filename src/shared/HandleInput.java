package shared;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class to handle input values
 * @author <a href="mailto:lemosantony@gmail.com">Antony Lemos</a>
 */
public class HandleInput {
    /**
     * Reads the number of iteractions to calculate the euler number
     * @return Number of iteractions
     * @throws InputMismatchException
     */
    public static final int readNumIteractions() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of iterations: ");
        int numIteractions = scanner.nextInt();

        scanner.close();
        return numIteractions;
    }
}
