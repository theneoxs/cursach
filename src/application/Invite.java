package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Invite {
	private IntegerProperty idInvite;
	private IntegerProperty idStudent;
	private IntegerProperty idOrganization;
	private StringProperty Message;
	private ObjectProperty<Date> date;

	public Invite() {
		this.idInvite=new SimpleIntegerProperty(1);
		this.idStudent=new SimpleIntegerProperty(1);
		this.idOrganization=new SimpleIntegerProperty(1);
		this.Message= new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<Date>(Date.valueOf("1488-01-01"));
		}
	public Invite(Integer idInvite,Integer idStudent,Integer idOrganization, String Message,Date date) {
		this.idInvite = new SimpleIntegerProperty(idInvite);
		this.idStudent = new SimpleIntegerProperty(idStudent);
		this.idOrganization = new SimpleIntegerProperty(idOrganization);
		this.Message = new SimpleStringProperty(Message);
		this.date = new SimpleObjectProperty<Date>(date);
		}
	public Integer getIdInvite() {
		return this.idInvite.get();
	}
	public Integer getIdStudent() {
		return this.idStudent.get();
	}
	public Integer getIdOrganization() {
		return this.idOrganization.get();
	}
	public String getMessage() {
		return this.Message.get();
	}
	public Date getDate() {
		return this.date.get();
	}
}
