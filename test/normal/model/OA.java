package normal.model;

public class OA {

	private OB ob;

	public OB getOb() {
		return ob;
	}

	public void setOb(OB ob) {
		this.ob = ob;
	}

	public void say() {
		ob.say();
	}

}
