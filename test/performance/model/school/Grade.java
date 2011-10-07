package performance.model.school;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("grade")
public class Grade {

	private Student[] students;

	private long level;

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
