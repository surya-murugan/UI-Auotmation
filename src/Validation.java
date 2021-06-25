
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Validation {

	
	public static void main(String [] args){
		// TODO Auto-generated method stub
		 
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		WebDriver ui = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(ui,10);
		ui.get("http://jt-dev.azurewebsites.net/#/SignUp");
		
		String[] exp = {"English","Dutch"};
		
		ui.findElement(By.xpath("//div[@id='language']/div[1]/span")).click();
		List<WebElement> elements = ui.findElements(By.cssSelector("div[ng-bind-html='language']"));  
		
		boolean match = false;
		try {
		   for(WebElement e : elements)
		   {			 
			 
			 for (int i=0; i<exp.length; i++)	 
			 {
                if(e.getText().equalsIgnoreCase(exp[i])) 
                {  
           	       match = true;
                }      
			 }

		   } 
		} 
		catch (NoSuchElementException e) {
		   match = false;
		}
		
		ui.findElement(By.xpath("//div[@id='ui-select-choices-row-1-0']/a")).click();
		ui.findElement(By.id("name")).sendKeys("Surya");
		ui.findElement(By.id("orgName")).sendKeys("Surya");
		ui.findElement(By.id("singUpEmail")).sendKeys("yourmailid@outlook.com");
		ui.findElement(By.xpath("//span[contains(text(), 'agree')]")).click();
		ui.findElement(By.cssSelector("button[type = 'submit']")).click();
		ui.get("https://outlook.office.com/mail/");
		w.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Email or phone']")));
		ui.findElement(By.cssSelector("input[placeholder='Email or phone']")).sendKeys("yourmailid@outlook.com");
		ui.findElement(By.id("idSIButton9")).click();
		
		w.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Password']")));
		ui.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("YourPassword");
		ui.findElement(By.id("idSIButton9")).click();

		w.until(ExpectedConditions.urlContains("common"));
		ui.findElement(By.id("idSIButton9")).click();
		w.until(ExpectedConditions.urlContains("inbox"));
		ui.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<WebElement> unreademails = ui.findElements(By.xpath("//*[contains(text(), 'JabaTalks Development')]"));
		
			if(unreademails.isEmpty()){
				System.out.println("Email Not Found");
				
			}
			else
			{
				System.out.println("Found Email");
			}
			ui.close();
	}
	
}
