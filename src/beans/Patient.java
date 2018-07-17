package beans;

public class Patient {
	
	protected int p_id;
	protected String p_name;
	protected String p_mobile;
	
	public Patient(){}
	
	public Patient(int p_id){
		this.p_id = p_id;
	}
	
	public Patient(String p_name, String p_mobile) {
		super();
		this.p_name = p_name;
		this.p_mobile = p_mobile;
	}

	public Patient(int p_id, String p_name, String p_mobile) {
		this(p_name, p_mobile);
		this.p_id = p_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_mobile() {
		return p_mobile;
	}

	public void setP_mobile(String p_mobile) {
		this.p_mobile = p_mobile;
	}
	
	
	
	
	
	

}
