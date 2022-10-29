import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.CalculateForm;
import pages.SearchPage;


public class CalculateNumbersTest {

    private CalculateForm calculateForm;
    private SearchPage searchPage;
    private WebDriver driver;

    @BeforeEach
    public void precondition() {
        WebDriverManager.chromedriver().setup();
        String testUrl = "http://google.com";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(testUrl);
        searchPage = new SearchPage(driver);
        calculateForm = new CalculateForm(driver);
    }

    @AfterEach
    public void postCondition() {
        driver.quit();
    }

    @Test
    public void calculateTest() {
        searchCalculate();
        calculate();
    }


    private void searchCalculate() {
        searchPage.searchByRequest("Калькулятор");
        Assertions.assertTrue(
                driver.findElement(By.className("card-section")).isDisplayed(),
                "Не отображена форма калькулятора."
        );
    }

    private void calculate() {
        var expression = "1 * 2 - 3 + 1";
        var convertExpression = expression.replace("*", "×")
                .replace("-" ,"−");
        calculateForm.calculateExpression(convertExpression);

        Assertions.assertEquals(
                0,
                Integer.parseInt(calculateForm.getInputFieldContent()),
                "Неверный результат рассчёта"
        );
        Assertions.assertEquals(
                convertExpression.replace("−", "-").concat(" ="),
                calculateForm.getPreviousFieldContent(),
                "Содержимое форм не совпадает"
        );
    }

}
