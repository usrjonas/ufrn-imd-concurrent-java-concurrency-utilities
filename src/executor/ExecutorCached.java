package executor;

import calculator.EulerTermCalculator;
import shared.HandleInput;
import shared.HandleList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Concurrent calculation of the euler number using multiple
 * threads (more specifically, within a cached thread pool)
 *
 * @author <a href="mailto:lemosantony@gmail.com">Antony Lemos</a>
 * @author <a href="mailto:jonas.oliveira.111@ufrn.edu.br">Jonas de Oliveira</a>
 */
public final class ExecutorCached {
    /**
     * Execute method
     */
    public static void execute() {

        final int numInteractions = HandleInput.readNumInteractions();
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future<BigDecimal>> results = new ArrayList<>();

        for (int i = 1; i <= numInteractions; i++) {
            Callable<BigDecimal> calculator = new EulerTermCalculator(new BigDecimal(i));
            results.add(executor.submit(calculator));
        }

        try {
            BigDecimal eulerNumber = HandleList.sumAllValuesInListAndAddOne(results);
            System.out.println("=== Cached Thread Pool ===");
            System.out.println("Euler number: " + eulerNumber);
            System.out.println("Number of executor threads: " + (Thread.activeCount() - 1));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            System.exit(0);
        }
    }
}
