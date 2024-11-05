package recommend;

public class Excercise {
	String title;
	String desc;
	String type;
	String body_part;
	String equipment;
	String level;
	float rating;
	
	public Excercise(String t, String d, String type, String b_p, String e, String l, float r){
		title = t;
		desc = d;
		this.type = type;
		body_part = b_p;
		equipment = e;
		level = l;
		rating = r;
	}
	
	void set_title(String t){
		title = t;
	}
	
	void set_desc(String d){
		desc = d;
	}
	
	void set_type(String t){
		type = t;
	}
	
	void set_body_part(String b_p){
		body_part = b_p;
	}

	void set_equipment(String e) {
		equipment = e;
	}
	
	void set_level(String l){
		level = l;
	}
	
	void set_rating(float r){
		rating = r;
	}
	
	String get_title() {
		return title;
	}
	
	String get_desc() {
		return desc;
	} 
	
	String get_type() {
		return type;
	}
	
	String get_body_part() {
		return body_part;
	}
	
	String get_equipment() {
		return equipment;
	}
	
	String get_level() {
		return level;
	}
	
	float get_rating() {
		return rating;
	}
}
