package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class client {

	// Создаем переменные класса Client
	private IntegerProperty idclient;
	private StringProperty Name;
	private StringProperty Surname;
	private ObjectProperty<Date> Date_of_autorization;
	private IntegerProperty Access_level;
	private StringProperty Info;
	// Конструктор пустой
	public client() {
		this.idclient = new SimpleIntegerProperty(0);
		this.Name = new SimpleStringProperty("");
		this.Surname = new SimpleStringProperty("");
		this.Date_of_autorization = new SimpleObjectProperty<Date>(Date.valueOf("2020-01-01"));
		this.Access_level = new SimpleIntegerProperty(0);
		this.Info = new SimpleStringProperty("");
	}
	// Конструктор со всеми переменными
	public client (Integer idclient, String Name, String Surname, Date Date_of_autorization, Integer Access_level, String Info){
		this.idclient = new SimpleIntegerProperty(idclient);
		this.Name = new SimpleStringProperty(Name);
		this.Surname = new SimpleStringProperty(Surname);
		this.Date_of_autorization = new SimpleObjectProperty<Date>(Date_of_autorization);
		this.Access_level = new SimpleIntegerProperty(Access_level);
		this.Info = new SimpleStringProperty(Info);
	}
	// Геттеры переменных
	public Integer getIdclient() {
		return this.idclient.get();
	}

	public String getName() {
		return this.Name.get();
	}

	public String getSurname() {
		return this.Surname.get();
	}

	public Date getDate_of_autorization() {
		return this.Date_of_autorization.get();
	}
	public Integer getAccess_level() {
		return this.Access_level.get();
	}
	public String getInfo() {
		return this.Info.get();
	}
}
