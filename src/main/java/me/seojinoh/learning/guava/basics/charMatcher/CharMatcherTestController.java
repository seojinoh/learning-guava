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
	 * CharMatcher의 메서드 중 "Deprecated. ... are supplementary characters;"를 포함한 javadoc과 함께 deprecated 된 메서드들이 있는데,
	 * Devanagari, Fullwidth digits와 같은 의도하지 않은 문자와 일치될 수 있기 때문입니다. (따라서, 상황에 따라 실제 사용에 이슈가 없을 수 있음)
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

	/**
	 * String에서 ASCII 이외를 제거 (CharMatcher.retainFrom(CharSequence sequence) 활용)
	 * <pre>
	 * CharMatcher 클래스는 Java char value의 true or false를 결정하며, retainFrom 메서드는 문자 시퀀스의 일치하는 모든 BMP 문자를 포함한 문자열을 순서대로 반환합니다.
	 * </pre>
	 */
	@GetMapping("/removeNonAsciiCharacters")
	public void removeNonAsciiCharacters() {
		String input = "Hello, World! 12345 一二三四五";

		logger.info("Retain ASCII ::: {}", CharMatcher.ascii().retainFrom(input));

		CharMatcher numberMatcher = CharMatcher.inRange('0', '9');
		CharMatcher lowerCaseMatcher = CharMatcher.inRange('a', 'z');

		logger.info("Retain 0-9, a-z ::: {}", numberMatcher.or(lowerCaseMatcher).retainFrom(input));
	}

}
