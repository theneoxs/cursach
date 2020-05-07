package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Group {
	private IntegerProperty idGroup;
	private IntegerProperty Number_of_group;
	private StringProperty Name_of_head_of_group;
	private IntegerProperty Number_of_group_members;

	public Group() {
		this.idGroup=new SimpleIntegerProperty(1);
		this.Number_of_group=new SimpleIntegerProperty(0);
		this.Name_of_head_of_group=new SimpleStringProperty("");
		this.Number_of_group_members= new SimpleIntegerProperty(0);
		}
	public Group(Integer idGroup,Integer Number_of_group, String Name_of_head_of_group,  Integer Number_of_group_members) {
		this.idGroup = new SimpleIntegerProperty(idGroup);
		this.Number_of_group = new SimpleIntegerProperty(Number_of_group);
		this.Name_of_head_of_group = new SimpleStringProperty(Name_of_head_of_group);
		this.Number_of_group_members = new SimpleIntegerProperty(Number_of_group_members);
		}


	public Integer getIdFaculty() {
		return this.idGroup.get();
	}
	public Integer getNumber_of_group() {
		return this.Number_of_group.get();
	}
	public String getName_of_head_of_group() {
		return this.Name_of_head_of_group.get();
	}
	public Integer getNumber_of_group_members() {
		return this.Number_of_group_members.get();
	}
}
