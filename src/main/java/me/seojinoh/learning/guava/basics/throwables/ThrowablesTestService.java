package me.seojinoh.learning.guava.basics.throwables;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ThrowablesTestService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	void methodThatThrowThrowable() throws Throwable {
		Random random = new Random();
		int num = random.nextInt(10);

		if(num % 3 == 0)
			throw new Error("Hello, Error!");
		else if(num % 3 == 1)
			throw new RuntimeException("Hello, RuntimeException!");
		else
			throw new Exception("Hello, Exception!");
	}

}
