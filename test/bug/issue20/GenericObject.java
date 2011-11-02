package bug.issue20;

public class GenericObject<T> {

	private String name;

	private T abc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getAbc() {
		return abc;
	}

	public void setAbc(T abc) {
		this.abc = abc;
	}

}
