package normal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xblink.annotation.XBlinkAsAttribute;
import org.xblink.annotation.XBlinkConverter;
import org.xblink.core.convert.converters.MyDateConverter;

import performance.model.EnumForSeason;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("NormalObject")
public class NormalObject {

	@XStreamAsAttribute
	@XBlinkAsAttribute
	private int i;

	private String abc;

	@XBlinkConverter(MyDateConverter.class)
	private Date aDate;

	private EnumForSeason enumForSeason;

	private long[] num;

	private List<String> strList;

	private Set<Object> objSet;

	private Set objSet2;

	private Map<Integer, Object> objMap;

	private Map objMap2;

	private NormalObject normalObject;

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

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public Set<Object> getObjSet() {
		return objSet;
	}

	public void setObjSet(Set<Object> objSet) {
		this.objSet = objSet;
	}

	public Map<Integer, Object> getObjMap() {
		return objMap;
	}

	public void setObjMap(Map<Integer, Object> objMap) {
		this.objMap = objMap;
	}

	public NormalObject getNormalObject() {
		return normalObject;
	}

	public void setNormalObject(NormalObject normalObject) {
		this.normalObject = normalObject;
	}

}
