package beans;

public class Doctor {
	
	protected int doc_id;
	protected String doc_name;
	protected String doc_mobile;
	protected String doc_specialization;
	
	public Doctor(){}
	
	public Doctor(int doc_id){
		this.doc_id = doc_id;
	}
	
	public Doctor(String doc_name, String doc_mobile, String doc_specialization) {
		super();
		this.doc_name = doc_name;
		this.doc_mobile = doc_mobile;
		this.doc_specialization = doc_specialization;
	}

	public Doctor(int doc_id, String doc_name, String doc_mobile, String doc_specialization) {
		this(doc_name, doc_mobile, doc_specialization);
		this.doc_id = doc_id;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getDoc_mobile() {
		return doc_mobile;
	}

	public void setDoc_mobile(String doc_mobile) {
		this.doc_mobile = doc_mobile;
	}

	public String getDoc_specialization() {
		return doc_specialization;
	}

	public void setDoc_specialization(String doc_specialization) {
		this.doc_specialization = doc_specialization;
	}
	
	
	
	

}
