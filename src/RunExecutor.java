import executor.ExecutorCached;
import executor.ExecutorFixed;
import executor.ExecutorWorkStealingPool;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class
 *
 * @author <a href="mailto:lemosantony@gmail.com">Jonas de Oliveira</a>
 */
public class RunExecutor {

    /**
     * Main method
     *
     * @param args
     * @throws InputMismatchException
     */
    public static void main(String[] args) throws InputMismatchException {
        System.out.println("===   Select the executor  ===");
        System.out.println("=== 1 - Cached Thread Pool ===");
        System.out.println("=== 2 - Fixed Thread Pool  ===");
        System.out.println("=== 3 - Work Stealing Pool ===");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the executor selection number: ");
        int executorSelection = scanner.nextInt();

        switch (executorSelection) {
            case 1:
                ExecutorCached.execute();
            case 2:
                ExecutorFixed.execute();
            case 3:
                ExecutorWorkStealingPool.execute();
            default:
                System.out.println("Invalid argument");
                System.exit(-1);
        }
    }
}
