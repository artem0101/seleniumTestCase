package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Страница поиска.
 */
public class SearchPage {

    private final WebDriver webDriver;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Производит поиск по запросу.
     *
     * @param request
     *        Запрос
     */
    public void searchByRequest(String request) {
        var elementInputSearchField = webDriver.findElement(By.xpath("//div/div/input[1]"));
        elementInputSearchField.sendKeys(request);
        elementInputSearchField.sendKeys(Keys.ENTER);
    }

}
