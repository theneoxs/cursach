package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Organization {
	private IntegerProperty idOrganization;
	private StringProperty Name_of_organization;
	private StringProperty Name_of_head_of_organization;
	private StringProperty Organization_direction;

	public Organization() {
		this.idOrganization=new SimpleIntegerProperty(1);
		this.Name_of_organization= new SimpleStringProperty("");
		this.Name_of_head_of_organization=new SimpleStringProperty("");
		this.Organization_direction=new SimpleStringProperty("");
		}
	public Organization(Integer idOrganization, String Name_of_organization,String Name_of_head_of_organization, String Organization_direction, String Number_of_faculty_members) {
		this.idOrganization = new SimpleIntegerProperty(idOrganization);
		this.Name_of_organization = new SimpleStringProperty(Name_of_organization);
		this.Name_of_head_of_organization = new SimpleStringProperty(Name_of_head_of_organization);
		this.Organization_direction = new SimpleStringProperty(Number_of_faculty_members);
		}
	public Integer getidOrganization() {
		return this.idOrganization.get();
	}
	public String getName_of_organization() {
		return this.Name_of_organization.get();
	}
	public String getName_of_head_of_organization() {
		return this.Name_of_head_of_organization.get();
	}
	public String getOrganization_direction() {
		return this.Organization_direction.get();
	}
}
