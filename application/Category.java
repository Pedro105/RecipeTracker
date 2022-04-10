package application;

public class Category {
	
	private int CategoryID;
	private String CategoryName;
	
	public Category(int id,String n) {
		this.CategoryID = id;
		this.CategoryName = n;
		
	}
	//retuns category id
	public int getCategoryID() {
		return CategoryID;
	}
	//used to set local variable  id
	public void setCategoryID(int id){
		this.CategoryID = id;
	}
	
	//retuns category name
	public String getCategoryName() {
		return CategoryName;
	}
	//used to set local variable  name
	public void setCategoryName(String categoryName){
		this.CategoryName = categoryName;
	}

}
