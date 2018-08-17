

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LearnTestNG {

	@BeforeSuite
	public void wakeupAndMakeup() {

		System.out.println("Wake Up and Make up");

	}

	@BeforeClass
	public void signinAndChangeAttire() {
		System.out.println("signinAndChangeAttire");
	}

	@BeforeMethod

	public void takeOrder() {

		System.out.println("takeOrder");
	}
	
	
	/*@Test
	 
	public void cookAndDeliverParameter(){
		
	 System.out.println("--cookAnd Serve--");
		 
	}
	
	
	@Test(dependsOnMethods="cookAndDeliverParameter")
	 
	public void tips(){
		 System.out.println("Get the tipes from the customer");
	}*/
	
	@Test(dataProvider="orderDetails")

	public void cookAndDeliver(String customerName, String tableNo, String orderDish) {

		// System.out.println("cookAndDeliver - 3 " +parameterOne + " Age - "
		// +paramTwo);

		 

		// int size =nullValues.length();
		
		System.out.println(customerName + tableNo + orderDish);

	}
	
	/*@DataProvider(name="orderDetails")
	public Object[][] getCustomerOrderDetails(){
		
		Object[][] orderDetails = new Object[3][3];
		
		// Customer Name, Table No, Order Details
		
		orderDetails[0][0] = "Prasanth";
		orderDetails[0][1] = "Table T1";
		orderDetails[0][2] = "Medium Size Veg Pizza";
		
		
		orderDetails[1][0] = "Karim";
		orderDetails[1][1] = "Table T2";
		orderDetails[1][2] = "Medium Size Non Veg Pizza";
		
		
		orderDetails[2][0] = "Muthu";
		orderDetails[2][1] = "Table T3";
		orderDetails[2][2] = "Regualr Size Pastha";
		
		
		return orderDetails;
		
	}
	*/
	
 

	/*
	 * @Test public void cookAndDeliver1() {
	 * 
	 * System.out.println("cookAndDeliver - 2"); }
	 */

	@AfterMethod
	public void cleanTable() {

		System.out.println("cleanTable");
	}

	@AfterClass
	public void signOutAndChangeAttire() {

		System.out.println("signOutAndChangeAttire");
	}

	@AfterTest
	public void closeShop() {

		System.out.println("closeShop");
	}

	@AfterSuite
	public void refreshAndSleep() {

		System.out.println("refreshAndSleep");
	}
	
	
	@DataProvider(name="orderDetails")
	private Object[][] readExcel() {
		
		XSSFWorkbook wbook = null;
		
		Object[][] orderDetails = null;
		
		try {
			
			// Opening the file from physical path
			FileInputStream ipStream = new FileInputStream(new File("./data/Customer Order Details.xlsx"));
			
			// To read .xlsx please use XSSFWorkbook
			wbook = new XSSFWorkbook(ipStream);
			
			// Index start with 0 -> I want to read the 1st excel sheet
			// Even you can open the sheet with name of sheet
			XSSFSheet sheet = wbook.getSheetAt(0);


			int lastRowNumber = sheet.getLastRowNum();
			
			
			// Explain very clear this column size definition
			int lastColumnNumber = sheet.getRow(0).getLastCellNum();
			
			orderDetails =new Object [lastRowNumber][lastColumnNumber];

			for (int i = 1; i <= lastRowNumber; i++) {

				XSSFRow rows = sheet.getRow(i);
				
				// Get the last cell number
				lastColumnNumber = rows.getLastCellNum();

				for (int j = 0; j < lastColumnNumber; j++) {
					
					String cellValue = rows.getCell(j).getStringCellValue();
					
					// Explain why we gave i-1 here no just i
					orderDetails[i-1][j] = cellValue;
					 
				}

			}

			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				wbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orderDetails;

	}

}
