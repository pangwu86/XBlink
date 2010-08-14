package org.xblinkvsxstream.model.school;

import java.util.Date;
import java.util.List;

import org.xblink.annotations.XBlinkAlias;
import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XBlinkAlias("school")
@XStreamAlias("school")
public class School {

	@XBlinkAsAttribute
	@XStreamAsAttribute
	private String schoolName;

	@XBlinkAsList
	@XStreamImplicit
	private List<Grade> grades;
	
	@XBlinkAsAttribute
	@XStreamAsAttribute
	private Date build;

	@XBlinkAsAttribute
	@XStreamAsAttribute
	private int area;


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
