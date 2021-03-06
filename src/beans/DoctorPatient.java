package beans;

import java.sql.Date;

public class DoctorPatient {
	//We have to set date automatically from sql using trigger
	
	protected int record_no;
	protected int doc_id;
	protected int p_id;
	protected String description;
	protected Date dt;
	
	public DoctorPatient(){}
	
	public DoctorPatient(int record_no){
		this.record_no = record_no;
	}
	
	public DoctorPatient(int doc_id, int p_id, String description) {
		super();
		this.doc_id = doc_id;
		this.p_id = p_id;
		this.description = description;
	}

	
	
	public DoctorPatient(int record_no, int doc_id, int p_id, String description, Date dt) {
		this(doc_id, p_id, description);
		this.record_no = record_no;
		this.dt = dt;
	}

	public DoctorPatient(int record_no, int doc_id, int p_id, String description) {
		this(doc_id, p_id, description);
		this.record_no = record_no;	
	}


	public int getRecord_no() {
		return record_no;
	}

	public void setRecord_no(int record_no) {
		this.record_no = record_no;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

}
