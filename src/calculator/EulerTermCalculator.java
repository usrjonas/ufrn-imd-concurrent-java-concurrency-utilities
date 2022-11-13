package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

/**
 * Calculator of the factorial of a number, implemented as a
 * <code>Callable</code> object to run upon a thread able to
 * return the calculated factorial
 * @see java.util.concurrent.Callable
 *
 * @author <a href="mailto:jonas.oliveira.111@ufrn.edu.br">Jonas de Oliveira</a>
 */
public class EulerTermCalculator implements Callable<BigDecimal> {
	/** Number one in BigDecimal */
	static private final BigDecimal ONE = new BigDecimal(1);

	/** Scale for division between BigDecimal */
	static private final int SCALE = 200;
	
	/** Number to calculate factorial */
	private BigDecimal number;

	/**
	 * Parameterized constructor
	 * @param number Number to calculate factorial
	 */
	public EulerTermCalculator(BigDecimal number) {
		this.number = number;
	}

	/**
	 * Task to run upon a thread
	 * @return Factorial of a given number
	 */
	@Override
	public BigDecimal call() {
		return ONE.divide(factorial(this.number), SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * Recursively calculates the factorial of a given number
	 * @param number Number to calculate factorial
	 * @return Factorial of the number
	 */
	private BigDecimal factorial(BigDecimal number) {
		if (number.compareTo(ONE) <= 0) {
			return ONE;
		} else {
			return number.multiply(factorial(number.subtract(ONE)));
		}
	}
}
