package beans;

public class Patient {
	
	protected int p_id, p_age;
	protected String p_name, p_address, p_gender;
	protected String p_mobile;
	
	public Patient(){}
	
	public Patient(int p_id){
		this.p_id = p_id;
	}
	
	public int getP_age() {
		return p_age;
	}

	public void setP_age(int p_age) {
		this.p_age = p_age;
	}

	public String getP_address() {
		return p_address;
	}

	public void setP_address(String p_address) {
		this.p_address = p_address;
	}

	public String getP_gender() {
		return p_gender;
	}

	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}

	public Patient(int p_id, int p_age, String p_name, String p_address, String p_gender, String p_mobile) {
		super();
		this.p_id = p_id;
		this.p_age = p_age;
		this.p_name = p_name;
		this.p_address = p_address;
		this.p_gender = p_gender;
		this.p_mobile = p_mobile;
	}

	public Patient(String p_name, String p_mobile, int p_age, String p_address, String p_gender) {
		super();
		this.p_name = p_name;
		this.p_mobile = p_mobile;
		this.p_age = p_age;
		this.p_address = p_address;
		this.p_gender = p_gender;
	}

	public Patient(int p_id, String p_name, String p_mobile, int p_age, String p_address, String p_gender) {
		this(p_name, p_mobile, p_age, p_address, p_gender);
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
