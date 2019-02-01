package maven.testng;

import org.testng.annotations.Test;

import Frameworks.CaseStudyNine;
import Frameworks.ExcelFile;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class CaseStudyNineTest {

	CaseStudyNine testngCS;

	@BeforeSuite
	public void beforeSuite() {
		testngCS = new CaseStudyNine();
		testngCS.setupSelenium("chrome", "https://www.edureka.co");
	}

	@AfterSuite
	public void afterSuite() {
		testngCS.quitBrowser();
	}

	@Test(priority = 0)
	public void testLogin() {
		try {
			ExcelFile excelFile = new ExcelFile();
			 String filePath = System.getProperty("user.dir");
			String[][] data = excelFile.readExcel(filePath+"\\src\\test\\resources\\selenium-test-data.xlsx","test-data");
			String email = data[0][0];
            String password = data[0][1];			
			testngCS.login(email, password);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
		}
	}
	
	@Test(priority = 1)
	public void testBrowseBlog() {
		try {
			testngCS.browseBlog();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
		}
	}
	
	@Test(priority = 2)
	public void testLogout() {
		try {
			testngCS.logout();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
		}
	}
}
