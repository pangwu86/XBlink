package normal;

import java.util.ArrayList;
import java.util.List;

import org.xblink.core.reflect.ObjectOperator;
import org.xblink.core.reflect.ObjectOperatorFactory;

public class GenericTest {

	public static void main(String[] args) {
		ObjectOperator objectOperator = ObjectOperatorFactory.createObjectOperator();

		List<String> list = new ArrayList<String>();

		System.out.println(list.getClass());
	}

}
