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
	private StringProperty Message;
	private ObjectProperty<Date> date;

	public Invite() {
		this.idInvite=new SimpleIntegerProperty(1);
		this.Message= new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<Date>(Date.valueOf("1488-01-01"));
		}
	public Invite(Integer idInvite, String Message,Date date) {
		this.idInvite = new SimpleIntegerProperty(idInvite);
		this.Message = new SimpleStringProperty(Message);
		this.date = new SimpleObjectProperty<Date>(date);
		}
	public Integer getIdInvite() {
		return this.idInvite.get();
	}
	public String getMessage() {
		return this.Message.get();
	}
	public Date getDate() {
		return this.date.get();
	}
}
