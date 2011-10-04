package performance.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.annotation.XBlinkAsAttribute;
import org.xblink.annotation.XBlinkConverter;
import org.xblink.core.convert.converters.MyDateConverter;

public class AllTypeObject {

	@XBlinkAsAttribute
	private int i;

	private String abc;

	@XBlinkConverter(MyDateConverter.class)
	private Date aDate;

	private EnumForSeason enumForSeason;

	private long[] num;

	private List<String> strs;

	private Set<Object> objs;

	private Map<Integer, Object> clzMap;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	public Date getaDate() {
		return aDate;
	}

	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}

	public EnumForSeason getEnumForSeason() {
		return enumForSeason;
	}

	public void setEnumForSeason(EnumForSeason enumForSeason) {
		this.enumForSeason = enumForSeason;
	}

	public long[] getNum() {
		return num;
	}

	public void setNum(long[] num) {
		this.num = num;
	}

	public List<String> getStrs() {
		return strs;
	}

	public void setStrs(List<String> strs) {
		this.strs = strs;
	}

	public Set<Object> getObjs() {
		return objs;
	}

	public void setObjs(Set<Object> objs) {
		this.objs = objs;
	}

	public Map<Integer, Object> getClzMap() {
		return clzMap;
	}

	public void setClzMap(Map<Integer, Object> clzMap) {
		this.clzMap = clzMap;
	}

}
