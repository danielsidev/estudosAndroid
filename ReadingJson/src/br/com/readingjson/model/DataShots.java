package br.com.readingjson.model;

public class DataShots {
	
	private String  title;
	private String  imageUrl;
	private String  playerName;
	private String  playerAvatar;
	private Integer shotViews;
	
	public DataShots(){}
	
	public DataShots(String title, String imageUrl, String playerName,
			String playerAvatar, Integer shotViews) {
		this.title = title;
		this.imageUrl = imageUrl;
		this.playerName = playerName;
		this.playerAvatar = playerAvatar;
		this.shotViews = shotViews;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerAvatar() {
		return playerAvatar;
	}
	public void setPlayerAvatar(String playerAvatar) {
		this.playerAvatar = playerAvatar;
	}
	public Integer getShotViews() {
		return shotViews;
	}
	public void setShotViews(Integer shotViews) {
		this.shotViews = shotViews;
	}
	
	

}
