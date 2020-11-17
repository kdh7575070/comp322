package entity;

import java.sql.Date;

public class Movie {
	private String Movie_title; 
	private String Type;
	private int Parent_id;
	private int Series_number;
	private boolean Is_adult;
	private String Director_first_name;
	private String Director_last_name;
	private Date Start_year;
	private int Runtime;
	
	public Movie(String movie_title, String type, int parent_id, int series_number, boolean is_adult,
			String director_first_name, String director_last_name, Date start_year, int runtime) {
		Movie_title = movie_title;
		Type = type;
		Parent_id = parent_id;
		Series_number = series_number;
		Is_adult = is_adult;
		Director_first_name = director_first_name;
		Director_last_name = director_last_name;
		Start_year = start_year;
		Runtime = runtime;
	}

	public String getMovie_title() {
		return Movie_title;
	}

	public void setMovie_title(String movie_title) {
		Movie_title = movie_title;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getParent_id() {
		return Parent_id;
	}

	public void setParent_id(int parent_id) {
		Parent_id = parent_id;
	}

	public int getSeries_number() {
		return Series_number;
	}

	public void setSeries_number(int series_number) {
		Series_number = series_number;
	}

	public boolean isIs_adult() {
		return Is_adult;
	}

	public void setIs_adult(boolean is_adult) {
		Is_adult = is_adult;
	}

	public String getDirector_first_name() {
		return Director_first_name;
	}

	public void setDirector_first_name(String director_first_name) {
		Director_first_name = director_first_name;
	}

	public String getDirector_last_name() {
		return Director_last_name;
	}

	public void setDirector_last_name(String director_last_name) {
		Director_last_name = director_last_name;
	}

	public Date getStart_year() {
		return Start_year;
	}

	public void setStart_year(Date start_year) {
		Start_year = start_year;
	}

	public int getRuntime() {
		return Runtime;
	}

	public void setRuntime(int runtime) {
		Runtime = runtime;
	}	
}
