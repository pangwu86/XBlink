package performance.model.school;

import java.util.Date;
import java.util.List;

import org.xblink.annotation.XBlinkAsAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("school")
public class School {

	@XBlinkAsAttribute
	@XStreamAsAttribute
	private String schoolName;

	@XBlinkAsAttribute
	@XStreamAsAttribute
	private Date build;

	@XBlinkAsAttribute
	@XStreamAsAttribute
	private int area;

	private List<Grade> grades;

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Date getBuild() {
		return build;
	}

	public void setBuild(Date build) {
		this.build = build;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
}
