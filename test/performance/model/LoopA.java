package performance.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.xblink.annotation.XBlinkAsAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Root
@XStreamAlias("loopA")
public class LoopA {

	@Attribute
	@XStreamAsAttribute
	@XBlinkAsAttribute
	private String aName;
	@Element
	private LoopB loopBObj;
	@Element
	private LoopC loopCObj;

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public LoopB getLoopBObj() {
		return loopBObj;
	}

	public void setLoopBObj(LoopB loopBObj) {
		this.loopBObj = loopBObj;
	}

	public LoopC getLoopCObj() {
		return loopCObj;
	}

	public void setLoopCObj(LoopC loopCObj) {
		this.loopCObj = loopCObj;
	}

}
