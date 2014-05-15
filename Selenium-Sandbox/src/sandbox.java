import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
//This JUnit script is doing sand box login test
//and navigation bar clicking test.
//after login into the sand box, the program will randomly choose one
//icon from navigation bar to click.

public class sandbox extends SeleneseTestCase{
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://127.0.0.1:8000");
		selenium.start();
	}

	@Test
	public void testTc1() throws Exception {
		login();
		Thread.sleep(7000);
		
		//trying to navigate the icons on the navigation bar randomly
		pair[] p = new pair[10];
		createPairtest(p);
		while(true){
			Random rand = new Random();
			int num = rand.nextInt(10);
			navitest(p[num].click, p[num].asser);
		}
	}
	
	
	//test navigation bar
	public void navitest(String s1, String s2) throws Exception{
		selenium.click(s1);
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent(s2));
		System.out.println(s2);
	}
	
	public void createPairtest(pair[] p){
		pair beeswax = new pair("css=#beeswaxIcon > a.nav-tooltip > img", "Query Editor");
		pair pig = new pair("css=#pigIcon > a.nav-tooltip > img", "Pig script:");
		pair HCatalog = new pair("css=#hcatalogIcon > a.nav-tooltip > img", "HCatalog: Table List");
		pair file = new pair("css=#filebrowserIcon > a.nav-tooltip > img", "File Browser");
		pair job = new pair("css=#jobbrowserIcon > a.nav-tooltip > img", "Job Browser");
		pair design = new pair("css=#jobsubIcon > a.nav-tooltip > img", "Job Designs");
		pair Dashboard = new pair("css=#oozieIcon > a.nav-tooltip > img", "Dashboard");
		pair hueshell = new pair("css=#shellIcon > a.nav-tooltip", "Select one of the available shells");
		pair admin = new pair("css=#useradminIcon > a.nav-tooltip > img", "Hue Users");
		pair help = new pair("css=#helpIcon > a.nav-tooltip > img","Introducing Hue");
		p[0] = beeswax;
		p[1] = pig;
		p[2] = HCatalog;
		p[3] = file;
		p[4] = job;
		p[5] = design;
		p[6] = Dashboard;
		p[7] = hueshell;
		p[8] = admin;
		p[9] = help;
	}
	
	//test to login 
	public void login() throws Exception{
		System.out.println("testing login.");
		//input nothing
		selenium.open("/accounts/login/");
		selenium.click("//input[@value='Sign in']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("This field is required."));
		
		//just input login
		selenium.type("id=id_username", "hue");
		selenium.click("//input[@value='Sign in']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("This field is required."));
		
		//just input password
		selenium.type("id=id_username", "");
		selenium.type("id=id_password", "1111");
		selenium.click("//input[@value='Sign in']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("This field is required."));
		
		//input wrong login
		selenium.type("id=id_username", "bbb");
		selenium.click("//input[@value='Sign in']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Error! Invalid username or password."));
		
		//currectly login
		selenium.type("id=id_username", "hue");
		selenium.click("//input[@value='Sign in']");
		selenium.waitForPageToLoad("30000");
		System.out.println("login test finished.");
	}
	
	
	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}


class pair{
	String click;
	String asser;
	public pair(String click, String asser){
		this.click = click;
		this.asser = asser;
	}
}