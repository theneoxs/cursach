package application;

import java.sql.Date;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Economy {
	private StringProperty Type;
	private FloatProperty Student_scholarship;
	public Economy() {
			this.Type=new SimpleStringProperty("");
			this.Student_scholarship = new SimpleFloatProperty(1);
			}
	public Economy(String Type, Float Student_scholarship) {
			this.Type = new SimpleStringProperty(Type);
			this.Student_scholarship = new SimpleFloatProperty(Student_scholarship);
			}
	public String getType() {
		return this.Type.get();
		}
	public Float getStudent_scholarship() {
		return this.Student_scholarship.get();
		}
}
