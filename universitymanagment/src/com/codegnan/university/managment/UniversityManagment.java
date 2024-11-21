package com.codegnan.university.managment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.codegnan.univerity.exceptions.CourseNotFoundException;
import com.codegnan.univerity.exceptions.ProfessorNotFoundException;
import com.codegnan.univerity.exceptions.StudentNotFoundException;

public class UniversityManagment {
	private List<Student> students;// list to hold Students
	private List<Professor> professors;// list to hold professors.
	private List<Course> courses;// list to hold courses

	// constructor initilizes empty list for students,professors,courses.
	public UniversityManagment() {
		students = new ArrayList<>();
		professors = new ArrayList<>();
		courses = new ArrayList<>();
	}

	// method to add a student.
	public void addStudent(String name) {
		students.add(new Student(name));
	}

	// method to add professor
	public void addProfessor(String name) {
		professors.add(new Professor(name));
	}

	// method to add courses.
	public void addCourse(String title) {
		courses.add(new Course(title));
	}

	// method to entroll a student in course.
	public void enrollStudentInCourse(String studentName, String courseTitle)
			throws StudentNotFoundException, CourseNotFoundException {
		Student student = findStudentByName(studentName);// helper method to find the student by name.
		Course course = findCourseByTitle(courseTitle);// FIND THE COURSE BY NAME.
		if (student == null) {
			throw new StudentNotFoundException("Student" + studentName + "not found ");
		}
		if (course == null) {
			throw new CourseNotFoundException("Course " + courseTitle + "Not Found");
		}
		student.enrollInCourse(course);// enroll the student in course

	}

	// method to assign a course to professor.
	public void assignCourseToProfessor(String professorName, String courseTitle)
			throws ProfessorNotFoundException, CourseNotFoundException {
		Professor professor = findProfessorByName(professorName);
		Course course = findCourseByTitle(courseTitle);
		if (professor == null) {
			throw new ProfessorNotFoundException("professor " + professorName + " not found  ");
		}
		if (course == null) {
			throw new CourseNotFoundException("Course :" + courseTitle + " not found");
		}
		professor.assignCourse(course);
	}

	// method to list all Students
	public void listStudents() {
		if (students.isEmpty()) {
			System.out.println(" No Students are Avilable ");
		} else {
			System.out.println("List of Students ");
			for (Student student : students) {
				System.out.println(student);// print each student name
			}
		}
	}

	// method to list all professors
	public void listProfessors() {
		if (professors.isEmpty()) {
			System.out.println("professors data not avialble ");
		} else {
			System.out.println("list of professors");
			for (Professor professor : professors) {
				System.out.println(professor);// print each professor name
			}
		}
	}

	// method to list all courses
	public void listCourses() {
		if (courses.isEmpty()) {
			System.out.println("Courses are not avialble for now");

		} else {
			System.out.println("List of Courses  ");
			for (Course course : courses) {
				System.out.println(course);// print each course name
			}
		}
	}

	public void displayStudentCourses(String studentName) throws StudentNotFoundException {
		Student student = findStudentByName(studentName);// find student by name.
		if (student == null) {
			throw new StudentNotFoundException("Student " + studentName + " not Found");
		}
		System.out.println("Course for Student : " + studentName);
		for (Course course : student.getEnrolledCourses()) {
			System.out.println(course);// print each enrolled course
		}
	}

	public void displayProfessorCourses(String professorName) throws ProfessorNotFoundException {
		Professor professor = findProfessorByName(professorName);

		if (professor == null) {
			System.out.println("Professor " + professorName + " not found ");
		}
		System.out.println("Courses Assigned for professor : " + professorName);
		for (Course course : professor.getAssignedCourses()) {
			System.out.println(course);// print each assigned courses to professor.
		}
	}

	// helper method to find student by name.
	public Student findStudentByName(String name) {
		for (Student student : students) {
			if (student.getName().equalsIgnoreCase(name)) {
				return student;// return student if found
			}
		}
		return null;
	}

	// helper method to find professor by name
	public Professor findProfessorByName(String name) {
		for (Professor professor : professors) {
			if (professor.getName().equalsIgnoreCase(name)) {
				return professor;// return professor if found
			}
		}
		return null;
	}

	public Course findCourseByTitle(String title) {
		for (Course course : courses) {
			if (course.getTitle().equalsIgnoreCase(title)) {
				return course;// return professor if found
			}
		}
		return null;
	}

	public static void main(String[] args) {
		UniversityManagment management = new UniversityManagment();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("||==========================================||");
			System.out.println("   University Managment System    ");
			System.out.println("||==========================================||");
			System.out.println("||              1. Add Student              ||");
			System.out.println("||               2. Add Professor           ||");
			System.out.println("||               3. Add Courses              ||");
			System.out.println("||               4. Enroll Student in Course ||");
			System.out.println("||              5. Assign Courses to Professor||");
			System.out.println("||               6. List of Students          ||");
			System.out.println("||               7. List of Professors        ||");
			System.out.println("||               8. List Of Courses            ||");
			System.out.println("||               9. Display Student Courses    || ");
			System.out.println("||               10. Display Professor Courses ||");
			System.out.println("||               11. Exit                       ||");
			System.out.println("====================================================");
			int choice = scanner.nextInt();
			scanner.nextLine();
			// switch case to handle user option
			try {
				switch (choice) {
				case 1:
					System.out.print("Enter Student Name : ");
					String studentName = scanner.nextLine();
					management.addStudent(studentName);
					break;
				case 2:
					System.out.print("Enter Professor Name : ");
					String professorName = scanner.nextLine();
					management.addProfessor(professorName);
					break;
				case 3:
					System.out.print("Enter Course Title  : ");
					String courseTitle = scanner.nextLine();
					management.addCourse(courseTitle);
					break;
				case 4:
					System.out.print("Enter Student Name : ");
					String enrollStudent = scanner.nextLine();
					System.out.print("Enter Course Title : ");
					String enrollCourse = scanner.nextLine();
					try {
						management.enrollStudentInCourse(enrollStudent, enrollCourse);
					} catch (StudentNotFoundException | CourseNotFoundException e) {
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.print("Enter Professor Name : ");
					String assignProfessor = scanner.nextLine();
					System.out.print("Enter Course Title : ");
					String assignCourse = scanner.nextLine();
					try {
						management.assignCourseToProfessor(assignProfessor, assignCourse);
					} catch (ProfessorNotFoundException | CourseNotFoundException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					management.listStudents();
					break;
				case 7:
					management.listProfessors();
					break;
				case 8:
					management.listCourses();
					break;
				case 9:
					System.out.print("Enter Student Name : ");
					String displayStudent = scanner.nextLine();
					try {
						management.displayStudentCourses(displayStudent);
					} catch (StudentNotFoundException e) {
						e.printStackTrace();
					}
					break;
				case 10:
					System.out.print("Enter Professor Name : ");
					String displayProfessor = scanner.nextLine();
					try {
						management.displayProfessorCourses(displayProfessor);
					} catch (ProfessorNotFoundException e) {
						e.printStackTrace();
					}
					break;
				case 11:
					running = false;
					System.out.print("Existing ..");
					break;
				default:
					System.out.println("invalid Choice.. please enter a number between 1 to 11");
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		scanner.close();
	}

}
