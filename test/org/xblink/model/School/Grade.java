package org.xblink.model.School;

import org.xblink.annotations.XBlinkAsArray;
import org.xblink.annotations.XBlinkAsAttribute;

public class Grade {

	@XBlinkAsAttribute
	private long level;

	@XBlinkAsArray
	private Student[] students;

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}

}
