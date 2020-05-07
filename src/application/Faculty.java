package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Faculty {
	private IntegerProperty idFaculty;
	private StringProperty Name_of_faculty;
	private ObjectProperty<Date> Date_of_establishment_faculty;
	private StringProperty Name_of_head_of_faculty;
	private IntegerProperty Number_of_faculty_members;
	private IntegerProperty Members_now;

	public Faculty() {
		this.idFaculty=new SimpleIntegerProperty(1);
		this.Name_of_faculty= new SimpleStringProperty("");
		this.Date_of_establishment_faculty = new SimpleObjectProperty<Date>(Date.valueOf("1488-01-01"));
		this.Name_of_head_of_faculty=new SimpleStringProperty("");
		this.Number_of_faculty_members=new SimpleIntegerProperty(0);
		this.Members_now=new SimpleIntegerProperty(0);
		}
	public Faculty(Integer idFaculty, String Name_of_faculty,Date Date_of_establishment_faculty, String Name_of_head_of_faculty, Integer Number_of_faculty_members, Integer Members_now) {
		this.idFaculty = new SimpleIntegerProperty(idFaculty);
		this.Name_of_faculty = new SimpleStringProperty(Name_of_faculty);
		this.Date_of_establishment_faculty = new SimpleObjectProperty<Date>(Date_of_establishment_faculty);
		this.Name_of_head_of_faculty = new SimpleStringProperty(Name_of_head_of_faculty);
		this.Number_of_faculty_members = new SimpleIntegerProperty(Number_of_faculty_members);
		this.Members_now = new SimpleIntegerProperty(Members_now);
		}
	public Integer getIdFaculty() {
		return this.idFaculty.get();
	}
	public String getName_of_faculty() {
		return this.Name_of_faculty.get();
	}
	public Date getDate_of_establishment_faculty() {
		return this.Date_of_establishment_faculty.get();
	}
	public String getName_of_head_of_faculty() {
		return this.Name_of_head_of_faculty.get();
	}
	public Integer getNumber_of_faculty_members() {
		return this.Number_of_faculty_members.get();
	}
	public Integer getMembers_now() {
		return this.Members_now.get();
	}
}
