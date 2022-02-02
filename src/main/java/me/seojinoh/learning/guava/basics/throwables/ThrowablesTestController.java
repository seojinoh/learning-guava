package me.seojinoh.learning.guava.basics.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Throwables;

@RestController
@RequestMapping(value = "/throwables")
public class ThrowablesTestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private ThrowablesTestService throwablesTestService;

	/**
	 * Throwables.propagateIfPossible(Throwable throwable)
	 * <pre>
	 * RuntimeException, Error의 인스턴스이면, 해당 Throwable을 그대로 전달합니다.
	 * </pre>
	 * @deprecated 대신, Throwables.throwIfUnchecked(Throwable throwable)를 사용하세요. (null을 허용하지 않음)
	 */
	@GetMapping("/propagateIfPossible")
	public void propagateIfPossible() {
		try {
			try {
				throwablesTestService.methodThatThrowThrowable();
			} catch (Throwable t) {
				Throwables.propagateIfPossible(t);
				throw new RuntimeException(t);
			}
		} catch (Error | RuntimeException e) {
			logger.error("{} ::: {}", "Catch", e.getMessage());
		}
	}

	/**
	 * Throwables.throwIfUnchecked(Throwable throwable)
	 * <pre>
	 * RuntimeException, Error의 인스턴스이면, 해당 Throwable을 그대로 전달합니다. (null을 허용하지 않음)
	 * </pre>
	 */
	@GetMapping("/throwIfUnchecked")
	public void throwIfUnchecked() {
		try {
			try {
				throwablesTestService.methodThatThrowThrowable();
			} catch (Throwable t) {
				Throwables.throwIfUnchecked(t);
				throw new RuntimeException(t);
			}
		} catch (Error | RuntimeException e) {
			logger.error("{} ::: {}", "Catch", e.getMessage());
		}
	}

	/**
	 * Throwables.propagateIfPossible(Throwable throwable, Class<X> declaredType)
	 * <pre>
	 * RuntimeException, Error, declaredType의 인스턴스이면, 해당 Throwable을 그대로 전달합니다.
	 * </pre>
	 */
	@GetMapping("/propagateIfPossibleWithDeclaredType")
	public void propagateIfPossibleWithDeclaredType() {
		try {
			try {
				throwablesTestService.methodThatThrowThrowable();
			} catch (Throwable t) {
				Throwables.propagateIfPossible(t, Exception.class);
				throw new RuntimeException(t);
			}
		} catch (Error | Exception e) {
			logger.error("{} ::: {}", "Catch", e.getMessage());
		}
	}

}