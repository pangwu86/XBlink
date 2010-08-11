package org.xblink.model.school;

import java.util.Date;
import java.util.List;

import org.xblink.annotations.XBlinkAsAttribute;
import org.xblink.annotations.XBlinkAsList;

public class School {

	@XBlinkAsAttribute
	private String schoolName;

	@XBlinkAsAttribute
	private Date build;

	@XBlinkAsAttribute
	private int area;

	@XBlinkAsList
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
