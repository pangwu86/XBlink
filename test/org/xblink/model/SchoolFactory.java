package org.xblink.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xblink.model.school.Grade;
import org.xblink.model.school.School;
import org.xblink.model.school.Student;

public class SchoolFactory {

	public static School getSchool() throws Exception {
		School school = new School();
		school.setSchoolName("学校" + new Random().nextInt(10));
		school.setArea(new Random().nextInt(300));
		school.setBuild(new SimpleDateFormat("yyyy-MM-dd").parse("2010-08-09"));
		List<Grade> grades = new ArrayList<Grade>();
		school.setGrades(grades);
		return school;
	}

	public static Grade getGrade() throws Exception {
		Grade grade = new Grade();
		grade.setLevel(new Random().nextLong());
		Student[] students = new Student[0];
		grade.setStudents(students);
		return grade;
	}

	public static Student getStudent() throws Exception {
		Student student = new Student();
		student.setAge(new Random().nextInt(100));
		student.setName("Stu_" + String.valueOf(new Random().nextDouble()));
		return student;
	}

	public static School getSchool(int grdNum, int stuNum) throws Exception {
		School school = SchoolFactory.getSchool();
		List<Grade> grades = school.getGrades();
		for (int i = 0; i < grdNum; i++) {
			Grade grade = SchoolFactory.getGrade();
			Student[] students = new Student[stuNum];
			grade.setStudents(students);
			for (int j = 0; j < stuNum; j++) {
				students[j] = SchoolFactory.getStudent();
			}
			grades.add(grade);
		}
		return school;
	}

}
