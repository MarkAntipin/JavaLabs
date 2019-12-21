import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumParser {
    private static WebDriver driver = new ChromeDriver();
    private static String Url = "https://hh.ru/";
    private static List<Salary> salaries = new ArrayList<>();

    private static int numberOfPagesToParse = 4;

    public static void makeRequest() {
        driver.get(Url);
        try {
            WebElement findField = driver.findElement(
                    By.className(
                            "bloko-input"
                    )
            );
            findField.sendKeys("java developer");
            WebElement but = driver.findElement(By.className(
                    "supernova-search-group__submit"
                )
            );
            but.click();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    public static Salary preprocSalary(String salary) {
        boolean isUsd = false;
        if (salary.contains("USD")) {
            isUsd = true;
        }
        String[] numbers_str = salary.split("-");
        Integer[] numbers_int = new Integer[numbers_str.length];
        for (int i = 0; i < numbers_str.length; i++) {
            numbers_str[i] = numbers_str[i].replaceAll("\\D+","");
            numbers_int[i] = Integer.parseInt(numbers_str[i]);
            if (isUsd) {
                numbers_int[i] *= 65;
            }
        }
        if (numbers_int.length == 2) {
            return new Salary(numbers_int[0], numbers_int[1]);
        } else {
            return new Salary(numbers_int[0], numbers_int[0]);
        }
    }

    public static void getSalary() {
        List<WebElement> elsements = driver.findElements(By.className(
                "vacancy-serp-item__compensation"
        ));
        for(WebElement elem: elsements) {
            salaries.add(preprocSalary(elem.getText()));
            System.out.println(elem.getText());
        }
    }

    public static void goToNextPage() {
        WebElement nextPage = driver.findElement(
                By.xpath("//a[@data-qa='pager-next']")
        );
        String nextPageLink = nextPage.getAttribute("href");
        driver.get(nextPageLink);
    }

    public static void printMedianSalary() {
        Collections.sort(salaries);
        System.out.println("---------------------MEDIAN---------------------");
        System.out.println(salaries.get(salaries.size()/2).getMiddle());
    }

    public static void main(String[]args) {
        makeRequest();
        for (int i = 0; i < numberOfPagesToParse; i++) {
            getSalary();
            goToNextPage();
        }
        printMedianSalary();
    }
}

