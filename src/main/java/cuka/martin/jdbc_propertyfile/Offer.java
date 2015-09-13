package cuka.martin.jdbc_propertyfile;

public class Offer {

	private int id;
	private String name;
	private String email;
	private String text;
	
	public Offer(){
		
	}
	
	// konstruktor potrebny pre OfferDao.create(...) - ID netreba do automaticky generuje MySQL
	public Offer(String name, String email, String text){
		this.name = name;
		this.email = email;
		this.text = text;
	}
	
	// tento konstruktor je vygenerovany eclipsom momentalne nema vyuzitie...
	public Offer(int id, String name, String email, String text){
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", email=" + email + ", text=" + text + "]";
	}

}
