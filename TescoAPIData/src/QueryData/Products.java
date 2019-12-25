package QueryData;

import java.sql.Date;

public class Products {

	public int Product_ID;
	public String Product_Name;
	public String Super_Department;
	public String Department;
	public String Description;
	public int Content_Quantity;
	public double Price; 
	public Double Unit_Price;
	public java.util.Date Retrieved_Date;
	
	public Products (int Product_ID, String Product_Name, String Super_Department, String Department, String Description,
			int Content_Quantity, double Price, Double Unit_Price, java.util.Date Retrieved_Date) {
		this.Product_ID = Product_ID;
		this.Product_Name = Product_Name;
		this.Super_Department = Super_Department;
		this.Department = Department;
		this.Description = Description;
		this.Content_Quantity = Content_Quantity;
		this.Price = Price;
		this.Unit_Price = Unit_Price;
		this.Retrieved_Date = Retrieved_Date;

	}
	public void print(){
		System.out.println(Product_ID + "-" + Product_Name + "-" + Super_Department + "-" + Department + "-" + Description + "-" + Content_Quantity
				+ "-" + Price + "-" + Unit_Price + "-" + Retrieved_Date);
			}
}
