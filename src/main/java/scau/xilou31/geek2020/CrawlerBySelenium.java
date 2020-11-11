package scau.xilou31.geek2020;

public class CrawlerBySelenium {

    private String studentNumber;
    private String password;

    public CrawlerBySelenium(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }

//    public static void main(String[] args) throws InterruptedException {
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("https://jwxt.scau.edu.cn/");
//
//        System.out.println(driver.getWindowHandle());
//
//        WebDriverWait wait = new WebDriverWait(driver,5);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/form/div[5]/div/button")));
//
//        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/form/div[2]/div/div/input")).sendKeys("201825010510");
//
//        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/form/div[3]/div/div/input")).sendKeys("iamready123");
//
//        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/form/div[5]/div/button")).click();
////        driver.findElement(By.linkText("登 录")).click();
//
//        String handleBefore = driver.getWindowHandle();
//        for (String winHandle:driver.getWindowHandles()){
//            if(winHandle.equals(handleBefore)){
//                continue;
//            }
//            driver.switchTo().window(winHandle);
//            break;
//        }
//
//        System.out.println(driver.getWindowHandle());
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-user")));
//
//        String studentName = driver.findElement(By.className("fa-user")).getText();
//
//        System.out.println(studentName);
//
//        driver.close();
//    }
}
