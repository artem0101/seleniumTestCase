package pages;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Форма калькулятора.
 */
public class CalculateForm {

    private final WebDriver webDriver;

    public CalculateForm(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    /**
     * Производит рассчёт в соответствии с введёнными символами.
     *
     * @param expressions
     *        Переменное число элементов, обозначающих клавиши калькулятора.
     */
    public void calculateExpression(String... expressions) {
        Arrays.stream(expressions).collect(Collectors.toList()).forEach(
                this::clickByCalculateButton
        );
        clickByCalculateButton("=");
    }

    /**
     * Производит рассчёт в соответствии с введённым выражением.
     *
     * @param expression
     *        Выражение по которому необходимо выполнить рассчёт.
     */
    public void calculateExpression(String expression) {
        var expressions = Arrays.asList(expression.split(" "));
        expressions.removeIf(e -> e.equals(" "));

        expressions.forEach(this::clickByCalculateButton);
        clickByCalculateButton("=");
    }

    /**
     * Предоставляет доступ к содержимому поля ввода.
     *
     * @return Возвращает содержимое поля ввода.
     */
    public String getInputFieldContent() {
        return webDriver.findElement(By.id("cwos")).getText();
    }

    /**
     * Предоставляет доступ к элементу содержащещему предыдущее значение поля ввода.
     *
     * @return Возвращает значение элемента содержащещему предыдущее значение поля ввода.
     */
    public String getPreviousFieldContent() {
        return webDriver.findElement(By.className("vUGUtc")).getText();
    }

    private void clickByCalculateButton(String button) {
        String CALCULATE_BUTTON_TEMPLATE = "//*[@class='card-section']//div[text() = '%s']";
        var elementLocator = String.format(CALCULATE_BUTTON_TEMPLATE, button);
        webDriver.findElement(By.xpath(elementLocator)).click();
    }

}
