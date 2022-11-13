package shared;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Utility class to handle list values
 * @author <a href="mailto:lemosantony@gmail.com">Antony Lemos</a>
 */
public class HandleList {
    /** Number one in BigDecimal */
    static private final BigDecimal ONE = new BigDecimal(1);

    /**
     * Sums all values in the list and add one
     * @param results Results of the factorial calculations
     * @return Sum of the results add one
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static final BigDecimal sumAllValuesInListAndAddOne(List<Future<BigDecimal>> results) throws ExecutionException, InterruptedException {
        BigDecimal sum = ONE;
        for (Future<BigDecimal> result : results) {
            sum = sum.add(result.get());
        }
        return sum;
    }
}
