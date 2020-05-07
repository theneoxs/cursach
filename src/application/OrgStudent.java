package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrgStudent {
	private IntegerProperty Organization_has_Student;
	private IntegerProperty Student_idStudent;

	public OrgStudent() {
		this.Organization_has_Student=new SimpleIntegerProperty(1);
		this.Student_idStudent=new SimpleIntegerProperty(1);
		}
	public OrgStudent(Integer Organization_has_Student, Integer Student_idStudent) {
		this.Organization_has_Student = new SimpleIntegerProperty(Organization_has_Student);
		this.Student_idStudent = new SimpleIntegerProperty(Student_idStudent);
		}
	public Integer getOrganization_has_Student() {
		return this.Organization_has_Student.get();
	}
	public Integer getStudent_idStudent() {
		return this.Student_idStudent.get();
	}
}
