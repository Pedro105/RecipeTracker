package application;

public class Recipe extends Category{

	private int RecipeID, RecipeCategory;
	private String RecipeName, RecipeMethod, RecipeIngredients,CategoryName;

	public Recipe(int id, String name, String method, String ingredients, int RecipeCategory,String CategoryName) {
		super(RecipeCategory,CategoryName);
		this.RecipeID = id;
		this.RecipeName = name;
		this.RecipeMethod = method;
		this.RecipeIngredients = ingredients;
		this.RecipeCategory = RecipeCategory;
		this.CategoryName = CategoryName;

	}
	public String getCategoryName() {
		return CategoryName;
	}

	// used to set local variable Category name
	public void setCategoryName(String CategoryName) {
		this.CategoryName = CategoryName;
	}

	// retuns Recipe id
	public int getRecipeID() {
		return RecipeID;
	}

	// used to set local variable id
	public void setRecipeID(int id) {
		this.RecipeID = id;
	}

	// retuns Recipe name
	public String getRecipeName() {
		return RecipeName;
	}

	// used to set local variable name
	public void setRecipeName(String RecipeName) {
		this.RecipeName = RecipeName;
	}

	// retuns Recipe name
	public String getRecipeMethod() {
		return RecipeMethod;
	}

	// used to set local variable name
	public void setRecipeMethod(String RecipeMethod) {
		this.RecipeMethod = RecipeMethod;
	}

	// retuns Recipe name
	public String getRecipeIngredients() {
		return RecipeIngredients;
	}

	// used to set local variable name
	public void setRecipeIngredients(String RecipeIngredients) {
		this.RecipeIngredients = RecipeIngredients;
	}

	// retuns category id
	public int getRecipeCategory() {
		return RecipeCategory;
	}

	// used to set local variable Categoryid
	public void setRecipeCategory(int id) {
		this.RecipeCategory = id;
	}

}
