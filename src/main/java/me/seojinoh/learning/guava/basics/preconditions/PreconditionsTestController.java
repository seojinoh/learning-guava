package me.seojinoh.learning.guava.basics.preconditions;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

@RestController
@RequestMapping(value = "/preconditions")
public class PreconditionsTestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Preconditions.checkArgument
	 * 메서드에 전달된 매개변수의 유효성을 검사하는데 유용합니다.
	 * boolean 컨디션이 false이면, IllegalArgumentException을 발생시킵니다.
	 */
	@GetMapping("/checkArgument")
	public void checkArgument() {
		int limit = 0;
		int[] ageArray = {1, -1};

		for(int age : ageArray) {
			try {
				Preconditions.checkArgument(age > limit);
				logger.info("{} ::: {} > {}", "인자 검사 성공", age, limit);
			} catch (IllegalArgumentException e) {
				logger.error("{} ::: {} > {}", "인자 검사 실패", age, limit);
			}
		}
	}

	/**
	 * Preconditions.checkArgument
	 * IllegalArgumentException 발생 시 전달될 메시지를 추가할 수 있습니다.
	 */
	@GetMapping("/checkArgumentWithMessage")
	public void checkArgumentWithMessage() {
		int limit = 0;
		int[] ageArray = {1, -1};

		String message = "age > limit";

		for(int age : ageArray) {
			try {
				Preconditions.checkArgument(age > limit, message);
				logger.info("{} ::: {} > {}", "인자 검사 성공", age, limit);
			} catch (IllegalArgumentException e) {
				logger.error("{} ::: {}", "인자 검사 실패", e.getMessage());
			}
		}
	}

	/**
	 * Preconditions.checkArgument
	 * IllegalArgumentException 발생 시 전달될 메시지를 템플릿 형태로도 추가할 수 있습니다.
	 */
	@GetMapping("/checkArgumentWithTemplateMessage")
	public void checkArgumentWithTemplateMessage() {
		int limit = 0;
		int[] ageArray = {1, -1};

		String message = "%s > %s";

		for(int age : ageArray) {
			try {
				Preconditions.checkArgument(age > limit, message, age, limit);
				logger.info("{} ::: {} > {}", "인자 검사 성공", age, limit);
			} catch (IllegalArgumentException e) {
				logger.error("{} ::: {}", "인자 검사 실패", e.getMessage());
			}
		}
	}

	/**
	 * Preconditions.checkElementIndex
	 * array, list 또는 string에서 해당 인덱스의 유효성을 검사하는데 유용합니다. (index < array|list|string length)
	 * boolean 컨디션이 false이면, IndexOutOfBoundsException을 발생시킵니다.
	 */
	@GetMapping("/checkElementIndex")
	public void checkElementIndex() {
		int[] indexArray = {0, 1};
		int[] arrayToCheck = {0};

		for(int index : indexArray) {
			try {
				Preconditions.checkElementIndex(index, arrayToCheck.length);
				logger.info("{} ::: {} < {}", "인자 검사 성공", index, arrayToCheck.length);
			} catch (IndexOutOfBoundsException e) {
				logger.error("{} ::: {} < {}", "인자 검사 실패", index, arrayToCheck.length);
			}
		}
	}

	/**
	 * Preconditions.checkElementIndex
	 * IndexOutOfBoundsException 발생 시 전달될 메시지를 추가할 수 있습니다.
	 */
	@GetMapping("/checkElementIndexWithMessage")
	public void checkElementIndexWithMessage() {
		int[] indexArray = {0, 1};
		int[] arrayToCheck = {0};

		String message = "index < array|list|string length";

		for(int index : indexArray) {
			try {
				Preconditions.checkElementIndex(index, arrayToCheck.length, message);
				logger.info("{} ::: {} < {}", "인자 검사 성공", index, arrayToCheck.length);
			} catch (IndexOutOfBoundsException e) {
				logger.error("{} ::: {}", "인자 검사 실패", e.getMessage());
			}
		}
	}

	/**
	 * Preconditions.checkNotNull
	 * 메서드에 전달된 매개변수의 null 여부를 검사하는데 유용합니다.
	 * boolean 컨디션이 false이면, NullPointerException을 발생시킵니다.
	 */
	@GetMapping("/checkNotNull")
	public void checkNotNull() {
		String[] stringArray = {"Hello, World!", null};

		for(String string : stringArray) {
			try {
				Preconditions.checkNotNull(string);
				logger.info("{} ::: {} is not null", "인자 검사 성공", string);
			} catch (NullPointerException e) {
				logger.error("{} ::: {} is null", "인자 검사 실패", string);
			}
		}
	}

	/**
	 * Preconditions.checkNotNull
	 * NullPointerException 발생 시 전달될 메시지를 추가할 수 있습니다.
	 */
	@GetMapping("/checkNotNullWithMessage")
	public void checkNotNullWithMessage() {
		String[] stringArray = {"Hello, World!", null};

		String message = "object is null";

		for(String string : stringArray) {
			try {
				Preconditions.checkNotNull(string, message);
				logger.info("{} ::: {} is not null", "인자 검사 성공", string);
			} catch (NullPointerException e) {
				logger.error("{} ::: {}", "인자 검사 실패", e.getMessage());
			}
		}
	}

	/**
	 * Preconditions.checkNotNull
	 * NullPointerException 발생 시 전달될 메시지를 템플릿 형태로도 추가할 수 있습니다.
	 */
	@GetMapping("/checkNotNullWithTemplateMessage")
	public void checkNotNullWithTemplateMessage() {
		String[] stringArray = {"Hello, World!", null};

		String message = "%s is null";

		for(String string : stringArray) {
			try {
				Preconditions.checkNotNull(string, message, string);
				logger.info("{} ::: {} is not null", "인자 검사 성공", string);
			} catch (NullPointerException e) {
				logger.error("{} ::: {}", "인자 검사 실패", e.getMessage());
			}
		}
	}

	/**
	 * Preconditions.checkPositionIndex
	 * array, list 또는 string에서 해당 인덱스의 유효성을 검사하는데 유용합니다. (index <= array|list|string length)
	 * boolean 컨디션이 false이면, IndexOutOfBoundsException을 발생시킵니다.
	 */
	@GetMapping("/checkPositionIndex")
	public void checkPositionIndex() {
		int[] indexArray = {1, 2};
		int[] arrayToCheck = {0};

		for(int index : indexArray) {
			try {
				Preconditions.checkPositionIndex(index, arrayToCheck.length);
				logger.info("{} ::: {} <= {}", "인자 검사 성공", index, arrayToCheck.length);
			} catch (IndexOutOfBoundsException e) {
				logger.error("{} ::: {} <= {}", "인자 검사 실패", index, arrayToCheck.length);
			}
		}
	}

	/**
	 * Preconditions.checkPositionIndex
	 * IndexOutOfBoundsException 발생 시 전달될 메시지를 추가할 수 있습니다.
	 */
	@GetMapping("/checkPositionIndexWithMessage")
	public void checkPositionIndexWithMessage() {
		int[] indexArray = {1, 2};
		int[] arrayToCheck = {0};

		String message = "index <= array|list|string length";

		for(int index : indexArray) {
			try {
				Preconditions.checkPositionIndex(index, arrayToCheck.length, message);
				logger.info("{} ::: {} <= {}", "인자 검사 성공", index, arrayToCheck.length);
			} catch (IndexOutOfBoundsException e) {
				logger.error("{} ::: {}", "인자 검사 실패", message);
			}
		}
	}

	/**
	 * Preconditions.checkPositionIndexes
	 * array, list 또는 string에서 해당 인덱스들의 유효성을 검사하는데 유용합니다. (0 <= start <= end <= array|list|string length)
	 * boolean 컨디션이 false이면, IndexOutOfBoundsException을 발생시킵니다.
	 */
	@GetMapping("/checkPositionIndexes")
	public void checkPositionIndexes() {
		int start = 0;
		int[] endArray = {1, 2};
		int[] arrayToCheck = {0};

		for(int end : endArray) {
			try {
				Preconditions.checkPositionIndexes(start, end, arrayToCheck.length);
				logger.info("{} ::: {} <= {} <= {}", "인자 검사 성공", start, end, arrayToCheck.length);
			} catch (IndexOutOfBoundsException e) {
				logger.error("{} ::: {} <= {} <= {}", "인자 검사 실패", start, end, arrayToCheck.length);
			}
		}
	}

	/**
	 * Preconditions.checkState
	 * object 상태의 유효성을 검사하는데 유용합니다.
	 * boolean 컨디션이 false이면, IllegalStateException을 발생시킵니다.
	 */
	@GetMapping("/checkState")
	public void checkState() {
		int[] validStateArray = {-1, 0, 1};
		int[] stateToCheck = {1, 2};

		for(int state : stateToCheck) {
			try {
				Preconditions.checkState(Arrays.binarySearch(validStateArray, state) > 0);
				logger.info("{} ::: {} in {}", "인자 검사 성공", state, Arrays.toString(validStateArray));
			} catch (IllegalStateException e) {
				logger.error("{} ::: {} in {}", "인자 검사 실패", state, Arrays.toString(validStateArray));
			}
		}
	}

	/**
	 * Preconditions.checkState
	 * IllegalStateException 발생 시 전달될 메시지를 추가할 수 있습니다.
	 */
	@GetMapping("/checkStateWithMessage")
	public void checkStateWithMessage() {
		int[] validStateArray = {-1, 0, 1};
		int[] stateToCheck = {1, 2};

		String message = "state in validStateArray";

		for(int state : stateToCheck) {
			try {
				Preconditions.checkState(Arrays.binarySearch(validStateArray, state) > 0, message);
				logger.info("{} ::: {} in {}", "인자 검사 성공", state, Arrays.toString(validStateArray));
			} catch (IllegalStateException e) {
				logger.error("{} ::: {}", "인자 검사 실패", e.getMessage());
			}
		}
	}

	/**
	 * Preconditions.checkState
	 * IllegalStateException 발생 시 전달될 메시지를 템플릿 형태로도 추가할 수 있습니다.
	 */
	@GetMapping("/checkStateWithTemplateMessage")
	public void checkStateWithTemplateMessage() {
		int[] validStateArray = {-1, 0, 1};
		int[] stateToCheck = {1, 2};

		String message = "%s in %s";

		for(int state : stateToCheck) {
			try {
				Preconditions.checkState(Arrays.binarySearch(validStateArray, state) > 0, message, state, Arrays.toString(validStateArray));
				logger.info("{} ::: {} in {}", "인자 검사 성공", state, Arrays.toString(validStateArray));
			} catch (IllegalStateException e) {
				logger.error("{} ::: {}", "인자 검사 실패", e.getMessage());
			}
		}
	}

}
