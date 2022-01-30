package me.seojinoh.learning.guava.basics.preconditions;

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

}
