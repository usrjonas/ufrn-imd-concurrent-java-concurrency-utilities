package executor;

import calculator.EulerTermCalculator;
import shared.HandleInput;
import shared.HandleList;

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
	 */
	public static void execute() {

        final int numIteractions = HandleInput.readNumIteractions();
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		
		List<Future<BigDecimal>> results = new ArrayList<>();

		for ( int i = 1; i <= numIteractions; i++ ) {
			Callable<BigDecimal> calculator = new EulerTermCalculator(new BigDecimal(i));
			results.add(executor.submit(calculator));
		}

		try {
			BigDecimal eulerNumber = HandleList.sumAllValuesInListAndAddOne(results);
            System.out.println("=== Fixed Thread Pool ===");
            System.out.println("Euler number: " + eulerNumber);
            System.out.println("Number of executor threads: " + (Thread.activeCount()-1));
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
			executor.shutdown();
		}
	}
}
