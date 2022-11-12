import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Concurrent calculation of the euler number using multiple
 * threads (more specifically, within a fixed thread pool)
 *
 * @author <a href="mailto:jonas.oliveira.111@ufrn.edu.br">Jonas de Oliveira</a>
 */
public final class ExecutorFixed {
    /** Number of threads to use */
    private static final int NUM_THREADS = 8;

	/**
	 * Main method
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {

        final int numIteractions = readNumIteractions();
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		
		List<Future<BigDecimal>> results = new ArrayList<>();

		for ( int i = 1; i <= numIteractions; i++ ) {
			Callable<BigDecimal> calculator = new EulerTermCalculator(new BigDecimal(i));
			results.add(executor.submit(calculator));
		}

		try {
			BigDecimal eulerNumber = sumAllValuesInListAndAddOne(results);
            System.out.println("=== Fixed Thread Pool ===");
            System.out.println("Euler number: " + eulerNumber);
            System.out.println("Number of executor threads: " + (Thread.activeCount()-1));
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
	}

    /**
     * Reads the number of iteractions to calculate the euler number
     * @return Number of iteractions
     */
    private static final int readNumIteractions() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of iterations: ");
        int numIteractions = scanner.nextInt();

        scanner.close();
        return numIteractions;
    }

    /**
     * Sums all values in the list and add one
     * @param results Results of the factorial calculations
     * @return Sum of the results add one
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static final BigDecimal sumAllValuesInListAndAddOne(List<Future<BigDecimal>> results) throws ExecutionException, InterruptedException {
        BigDecimal sum = new BigDecimal(1);
        for (Future<BigDecimal> result : results) {
            sum = sum.add(result.get());
        }
        return sum;
    }
}
