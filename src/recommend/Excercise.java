package recommend;

public class Excercise {
	private String title;
	private String desc;
	private String type;
	private String body_part;
	private String equipment;
	private String level;
	private float rating;
	private int weight;
	
	public Excercise(String t, String d, String type, String b_p, String e, String l, float r, int w){
		title = t;
		desc = d;
		this.type = type;
		body_part = b_p;
		equipment = e;
		level = l;
		rating = r;
		weight = w;
	}
	
	public void set_title(String t){
		title = t;
	}
	
	public void set_desc(String d){
		desc = d;
	}
	
	public void set_type(String t){
		type = t;
	}
	
	public void set_body_part(String b_p){
		body_part = b_p;
	}

	public void set_equipment(String e) {
		equipment = e;
	}
	
	public void set_level(String l){
		level = l;
	}
	
	public void set_rating(float r){
		rating = r;
	}
	
	public void set_weight(int w) {
		weight = w;
	}
	
	public String get_title() {
		return title;
	}
	
	public String get_desc() {
		return desc;
	} 
	
	public String get_type() {
		return type;
	}
	
	public String get_body_part() {
		return body_part;
	}
	
	public String get_equipment() {
		return equipment;
	}
	
	public String get_level() {
		return level;
	}
	
	public float get_rating() {
		return rating;
	}
	
	public int get_weight() {
		return weight;
	}
}
