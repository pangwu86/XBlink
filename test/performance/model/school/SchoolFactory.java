package performance.model.school;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SchoolFactory {

	public static School getSchool(int g, int s, int b) throws Exception {
		School school = new School();
		school.setSchoolName("学校" + new Random().nextInt(100));
		school.setArea(new Random().nextInt(300));
		school.setBuild(new Date());
		List<Grade> grades = new ArrayList<Grade>();
		for (int i = 0; i < g; i++) {
			grades.add(getGrade(s, b, school));
		}
		school.setGrades(grades);
		return school;
	}

	private static final Random rd = new Random();
	public static Grade getGrade(int s, int b, School school) throws Exception {
		Grade grade = new Grade();
		grade.setLevel(rd.nextLong());
		Student[] students = new Student[s];
		for (int i = 0; i < s; i++) {
			students[i] = getStudent(b, grade, school);
		}
		grade.setStudents(students);
		return grade;
	}

	public static Student getStudent(int b, Grade grade, School school) throws Exception {
		Student student = new Student();
		student.setAge(new Random().nextInt(100));
		student.setName("Stu_" + String.valueOf(rd.nextDouble()));
		// student.setGrade(grade);
		// student.setSchool(school);
		Set<Book> books = new HashSet<Book>();
		for (int i = 0; i < b; i++) {
			books.add(getBook());
		}
		student.setBooks(books);
		return student;
	}

	private static Book getBook() {
		Random r = new Random();
		Book book = new Book();
		book.setBookName("Book_" + r.nextInt(10));
		Map inners = new HashMap();
		for (int i = 0; i < r.nextInt(10); i++) {
			inners.put(r.nextLong(), ("dfdf" + r.nextInt(100)));
		}
		book.setInners(inners);
		return book;
	}
}
