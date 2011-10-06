package normal;

import java.lang.reflect.Field;

import normal.model.OB;

public class RefTest {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		// OB b1 = new OB();
		// b1.setN(123123);
		// OB b2 = new OB();
		// b2.setN(454545);
		//
		// OB b3 = b1;
		//
		// OA a1 = new OA();
		// a1.setOb(b3);
		//
		// a1.say();
		//
		// b3 = b2;
		// b1 = b2;
		//
		// a1.say();

		OB b2 = new OB();
		b2.setN(123);
		for (Field field : b2.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			field.set(b2, null);
		}
		b2.say();
	}

}
