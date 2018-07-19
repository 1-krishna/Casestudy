package doctorpatientservlets;

import java.sql.Date;

public class Details {
	
	public String p_name, description, doc_name, p_gender, p_mobile, doc_specialization, p_address;
	public int p_age;
	Date dt;
	
	public Details(String p_name, String description, String doc_name, int p_age, String p_gender, String p_mobile,
			Date dt, String doc_specialization, String p_address) {
		super();
		this.p_name = p_name;
		this.description = description;
		this.doc_name = doc_name;
		this.p_age = p_age;
		this.p_gender = p_gender;
		this.p_mobile = p_mobile;
		this.dt = dt;
		this.doc_specialization = doc_specialization;
		this.p_address = p_address;
	}
	
	

}
