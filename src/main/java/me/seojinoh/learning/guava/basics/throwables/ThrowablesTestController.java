package me.seojinoh.learning.guava.basics.throwables;

import java.util.List;

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

	/**
	 * Throwables.propagateIfInstanceOf(Throwable throwable, Class<X> declaredType)
	 * <pre>
	 * declaredType의 인스턴스이면, 해당 Throwable을 그대로 전달합니다.
	 * </pre>
	 * @deprecated 대신, Throwables.throwIfInstanceOf(Throwable throwable, Class<X> declaredType)를 사용하세요. (null을 허용하지 않음)
	 */
	@GetMapping("/propagateIfInstanceOf")
	public void propagateIfInstanceOf() {
		try {
			try {
				throwablesTestService.methodThatThrowThrowable();
			} catch (Throwable t) {
				Throwables.propagateIfInstanceOf(t, Error.class);
				Throwables.propagateIfInstanceOf(t, Exception.class);
				throw new RuntimeException(t);
			}
		} catch (Error | Exception e) {
			logger.error("{} ::: {}", "Catch", e.getMessage());
		}
	}

	/**
	 * Throwables.throwIfInstanceOf(Throwable throwable, Class<X> declaredType)
	 * <pre>
	 * declaredType의 인스턴스이면, 해당 Throwable을 그대로 전달합니다. (null을 허용하지 않음)
	 * </pre>
	 */
	@GetMapping("/throwIfInstanceOf")
	public void throwIfInstanceOf() {
		try {
			try {
				throwablesTestService.methodThatThrowThrowable();
			} catch (Throwable t) {
				Throwables.throwIfInstanceOf(t, Error.class);
				Throwables.throwIfInstanceOf(t, Exception.class);
				throw new RuntimeException(t);
			}
		} catch (Error | Exception e) {
			logger.error("{} ::: {}", "Catch", e.getMessage());
		}
	}

	/**
	 * Throwable Throwables.getRootCause(Throwable throwable);
	 * <pre>
	 * 가장 안쪽 Throwable의 원인(cause)을 반환하여, Throwable의 초기 원인(cause)을 찾을 때 유용합니다.
	 * </pre>
	 */
	@GetMapping("/getRootCause")
	public void getRootCause() {
		try {
			throwablesTestService.methodThatThrowThrowables();
		} catch (Throwable t) {
			Throwable throwable = Throwables.getRootCause(t);
			logger.error("{} ::: {}", "Root Cause of Throwable", throwable.getMessage());
		}
	}

	/**
	 * <xmp>
	 * List<Throwable> Throwables.getCausalChain(Throwable throwable);
	 * </xmp>
	 * <pre>
	 * 모든 Throwable의 원인(cause) 체인을 리스트로 반환하여, 특정 유형의 Throwable이 포함되었는지 찾을 때 유용합니다.
	 * </pre>
	 */
	@GetMapping("/getCausalChain")
	public void getCausalChain() {
		try {
			throwablesTestService.methodThatThrowThrowables();
		} catch (Throwable t) {
			List<Throwable> throwableList = Throwables.getCausalChain(t);

			int index = throwableList.size() - 1;

			for(Throwable throwable : throwableList) {
				logger.error("{} {} ::: {}", "Cause of Throwable", index, throwable.getMessage());

				index--;
			}
		}
	}

	/**
	 * String Throwables.getStackTraceAsString(Throwable throwable);
	 * <pre>
	 * Throwable의 재귀 스택 추적(recursive stack trace)을 반환합니다.
	 * </pre>
	 */
	@GetMapping("/getStackTraceAsString")
	public void getStackTraceAsString() {
		try {
			throwablesTestService.methodThatThrowThrowables();
		} catch (Throwable t) {
			String stackTrace = Throwables.getStackTraceAsString(t);

			logger.error("{} ::: {}", "Stack Trace", stackTrace);
		}
	}

}
