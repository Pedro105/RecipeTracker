package application;

public class Friend  {
	
	private int FriendID;
	private String FriendName;
	
	
	public Friend(int id,String n) {
		
		this.FriendID = id;
		this.FriendName = n;
		
	}
	//retuns friend id
	public int getFriendID() {
		return FriendID;
	}
	//used to set local variable  id
	public void setFriendID(int id){
		this.FriendID = id;
	}
	
	//retuns friend name
	public String getFriendName() {
		return FriendName;
	}
	//used to set local variable  name
	public void setFriendName(String FriendName){
		this.FriendName = FriendName;
	}

}
