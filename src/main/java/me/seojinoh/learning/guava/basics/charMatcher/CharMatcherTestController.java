package me.seojinoh.learning.guava.basics.charMatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.CharMatcher;

@RestController
@RequestMapping(value = "/charMatcher")
public class CharMatcherTestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * String에서 특수문자를 제거 (CharMatcher.retainFrom(CharSequence sequence) 활용)
	 * <pre>
	 * CharMatcher 클래스는 Java char value의 true or false를 결정하며, retainFrom 메서드는 문자 시퀀스의 일치하는 모든 BMP 문자를 포함한 문자열을 순서대로 반환합니다.
	 * </pre>
	 * @deprecated 대신, CharMatcher.inrange(char startInclusive, char endInclusive) 등의 사용을 권장합니다.
	 * CharMatcher의 메서드 중 "Deprecated. ... are supplementary characters;"를 포함한 docs와 함께 deprecated 된 메서드들이 있는데,
	 * Devanagari, Fullwidth digits과 같은 이상한 문자와 일치될 수 있는 오해의 소지가 있기 때문입니다. (따라서, 실제 사용하여도 큰 이슈가 없을 수 있음)
	 */
	@GetMapping("/removeSpecialCharacters")
	public void removeSpecialCharacters() {
		String input = "Hello, World! 12345";

		CharMatcher charMatcher = CharMatcher.javaLetterOrDigit();
		logger.info("Retain Letter and Digit ::: {}", charMatcher.retainFrom(input));

		charMatcher = CharMatcher.javaLetter();
		logger.info("Retain Letter ::: {}", charMatcher.retainFrom(input));

		charMatcher = CharMatcher.digit();
		logger.info("Retain Digit ::: {}", charMatcher.retainFrom(input));
	}

}
