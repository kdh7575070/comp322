package entity;

public class Rating {
	private int Account_id;
	private int Movie_id;
	private boolean Likes;
	private int Ratings;
	
	
	public int getAccount_id() {
		return Account_id;
	}
	
	public Rating(int account_id, int movie_id) {
		Account_id = account_id;
		Movie_id = movie_id;
	}
	public void setAccount_id(int account_id) {
		Account_id = account_id;
	}
	public int getMovie_id() {
		return Movie_id;
	}
	public void setMovie_id(int movie_id) {
		Movie_id = movie_id;
	}
	public boolean getLikes() {
		return Likes;
	}
	public void setLikes(boolean likes) {
		Likes = likes;
	}
	public int getRatings() {
		return Ratings;
	}
	public void setRatings(int ratings) {
		Ratings = ratings;
	}

	
	
}
