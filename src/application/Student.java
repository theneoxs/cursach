package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
	private IntegerProperty IdStudent;
	private StringProperty Name;
	private StringProperty Surname;
	private StringProperty Middle_name;
	private ObjectProperty<Date> Date_of_birth;
	private StringProperty Sex;
	private ObjectProperty<Date> Year_of_enrollment;
	private StringProperty Status;
	private StringProperty Type;
	private IntegerProperty idGroup;

	public Student() {
		this.IdStudent=new SimpleIntegerProperty(1);
		this.Name= new SimpleStringProperty("");
		this.Surname= new SimpleStringProperty("");
		this.Middle_name= new SimpleStringProperty("");
		this.Date_of_birth = new SimpleObjectProperty<Date>(Date.valueOf("1488-01-01"));
		this.Sex=new SimpleStringProperty("");
		this.Year_of_enrollment = new SimpleObjectProperty<Date>(Date.valueOf("1337-01-01"));
		this.Status=new SimpleStringProperty("");
		this.Type=new SimpleStringProperty("");
		this.idGroup=new SimpleIntegerProperty(1);
		}
	public Student(Integer IdStudent, String Name, String Surname,String Middle_name,Date Date_of_birth, String Sex,Date Year_of_enrollment, String Status, String Type, Integer idGroup) {
		this.IdStudent = new SimpleIntegerProperty(IdStudent);
		this.Name = new SimpleStringProperty(Name);
		this.Surname = new SimpleStringProperty(Surname);
		this.Middle_name = new SimpleStringProperty(Middle_name);
		this.Date_of_birth = new SimpleObjectProperty<Date>(Date_of_birth);
		this.Sex = new SimpleStringProperty(Sex);
		this.Year_of_enrollment = new SimpleObjectProperty<Date>(Year_of_enrollment);
		this.Status = new SimpleStringProperty(Status);
		this.Type = new SimpleStringProperty(Type);
		this.idGroup = new SimpleIntegerProperty(idGroup);
		}


	public Integer getIdStudent() {
		return this.IdStudent.get();
	}
	public String getName() {
		return this.Name.get();
	}
	public String getSurname() {
		return this.Surname.get();
	}
	public String getMiddle_name() {
		return this.Middle_name.get();
	}
	public Date getDate_of_birth() {
		return this.Date_of_birth.get();
	}
	public String getSex() {
		return this.Sex.get();
	}
	public Date getYear_of_enrollment() {
		return this.Year_of_enrollment.get();
	}
	public String getStatus() {
		return this.Status.get();
	}
	public String getType() {
		return this.Type.get();
	}
	public Integer getidGroup() {
		return this.idGroup.get();
	}
}
