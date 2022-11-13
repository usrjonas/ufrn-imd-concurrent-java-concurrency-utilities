package shared;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Utility class to handle list values
 *
 * @author <a href="mailto:lemosantony@gmail.com">Antony Lemos</a>
 * @author <a href="mailto:jonas.oliveira.111@ufrn.edu.br">Jonas de Oliveira</a>
 */
public class HandleList {
    /**
     * Sums all values in the list and add one
     *
     * @param results Results of the factorial calculations
     * @return Sum of the results add one
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static final BigDecimal sumAllValuesInListAndAddOne(List<Future<BigDecimal>> results) throws ExecutionException, InterruptedException {
        BigDecimal sum = new BigDecimal(1);

        for (Future<BigDecimal> result : results) {
            sum = sum.add(result.get());
        }
        return sum;
    }
}
