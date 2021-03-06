package com.example.tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.components.Categories;
import com.example.components.CategoryItemMainPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.example.pages.DataPage;
import com.example.pages.MainPage;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Test
@Report
public class MainPageTest extends BaseTest {
    private static final String CATEGORY_NAME = "Забота о животных";
    private static final String CATEGORY_ID = "201";

    @Test
    public void categoriesNumberCheck() {
        MainPage page = page(MainPage.class);

        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoriesCollection().shouldHaveSize(27);
    }

    @Test
    public void animalExistenceCheck() {
        MainPage page = page(MainPage.class);

        page.navigate();
        Categories categories = page.getCategories();
        CategoryItemMainPage animalCategory = categories.getCategoryByName(CATEGORY_NAME);
        animalCategory.getTextElement().shouldHave(text(CATEGORY_NAME))
                .shouldHave(attribute("data-id", CATEGORY_ID));

    }

    @Test
    public void openAnimalCategory() {
        MainPage page = page(MainPage.class);

        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoryByName(CATEGORY_NAME).getUnit().click();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));
    }

    @Test  //DMru-10:[FUN] Просмотр данных набора "Площадки для выгула (дрессировки) собак"
    public void openAnimalSubCategory() {
        MainPage page = page(MainPage.class);

        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoryByName(CATEGORY_NAME).getUnit().click();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Площадки для выгула (дрессировки) собак").getCaptionTextElement().click();
        SelenideElement itemDep = $(By.cssSelector("#rows-caption > thead > tr:nth-child(1) > th > div.fixedCol"));
        Assert.assertEquals(itemDep.getText(),"Ведомственная принадлежность");
        SelenideElement itemAdr = $(By.cssSelector("#rows-content > thead > tr:nth-child(1) > th:nth-child(5)"));
        Assert.assertEquals(itemAdr.getText(),"Адресный ориентир");
        SelenideElement itemElm = $(By.cssSelector("#rows-content > thead > tr:nth-child(1) > th:nth-child(7)"));
        Assert.assertEquals(itemElm.getText(),"Элементы площадки");
        SelenideElement itemLight = $(By.cssSelector("#rows-content > thead > tr:nth-child(1) > th:nth-child(8)"));
        Assert.assertEquals(itemLight.getText(),"Освещение площадки");
        SelenideElement itemOgr = $(By.cssSelector("#rows-content > thead > tr:nth-child(1) > th:nth-child(9)"));
        Assert.assertEquals(itemOgr.getText(),"Ограждение площадки");

    }

    @Test   // DMru-6:[FUN] Экспорт данных о ветеринарных учреждениях в формате json
    public void exportAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();
        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Площадки для выгула (дрессировки) собак").getExportElement().click();//нажимаем на кнопку "Экспорт"

        SelenideElement itemJson = $(By.cssSelector("#dropExport > li:nth-child(1) > div > a")); //проверяем открывшийся список
        Assert.assertEquals(itemJson.getText(),"json\n378 Kb");
        SelenideElement itemXlsx =  $(By.cssSelector("#dropExport > li:nth-child(2) > div > a"));
        Assert.assertEquals(itemXlsx.getText(),"xlsx\n90 Kb");
        SelenideElement itemXml = $(By.cssSelector("#dropExport > li:nth-child(3) > div > a"));
        Assert.assertEquals(itemXml.getText(),"xml\n576 Kb");
        itemJson.click();
    }

    @Test // DMru-24:[FUN] Фильтрация данных по значению столбца
    public void filterAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Приюты для бродячих животных").getCaptionTextElement().click();
        registerNewTab();
        SelenideElement itemFilterColunm =  $(By.cssSelector("#rows-content > thead > tr.filter_tr > td:nth-child(4) > div > div")); //нажимаем на столбец
        Assert.assertTrue(itemFilterColunm.isDisplayed());
        itemFilterColunm.click();

        SelenideElement itemFilterFirst = $(By.cssSelector("#dropFilter-District > ul > li:nth-child(1)"));
        Assert.assertTrue(itemFilterFirst.isDisplayed());
        itemFilterFirst.click();//выбираем первый пункт

        SelenideElement itemFilterButton = $(By.cssSelector("#rows-content > thead > tr.filter_tr > td:nth-child(4) > div > i.filter-reset"));
        Assert.assertTrue(itemFilterButton.isDisplayed());
        itemFilterButton.click(); //кликаем на кнопку, чтоб отфильтровалось

        SelenideElement itemFilterCount = $(By.cssSelector("#rows > div:nth-child(3) > div.pager.page-container > span"));
        Assert.assertTrue(itemFilterCount.isDisplayed());
        Assert.assertEquals(itemFilterCount.getText(),"Количество записей: 1 из 13 ");//смотрим количество отфильтрованных записей
    }

    @Test //DMru-26:[FUN] Наличие карты у выбранного пункта данных
    public void mapAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Площадки для выгула (дрессировки) собак").getCaptionTextElement().click();
        registerNewTab();
        $(By.cssSelector("#rows-caption")).findElementByCssSelector("#rows-caption > tbody > tr:nth-child(1)");//находим нужную строку и элемент "Показать на карте" в этой строке
        SelenideElement itemFirstRow = $(By.cssSelector("#rows-caption > tbody > tr:nth-child(1)")); //кликаем по элементу
        Assert.assertTrue(itemFirstRow.isDisplayed());
        itemFirstRow.click();

        SelenideElement itemCard = $(By.cssSelector("#card"));
        Assert.assertTrue(itemCard.isDisplayed());
        SelenideElement itemMap  = $(By.cssSelector("##mapCard")); //находим сам элемент карты
        Assert.assertTrue(itemMap.isDisplayed());
    }

    @Test //DMru-27:[FUN] Просмотр информации о ветеринарном учреждении из режима "Карта"
    public void mapVetAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Ветеринарные учреждения").getCaptionTextElement().click();
        registerNewTab();
        SelenideElement itemMenuMap  = $(By.cssSelector("#app > div:nth-child(3) > ul > li.hide-lt-500-important > a"));
        Assert.assertTrue(itemMenuMap.isDisplayed());
        itemMenuMap.click(); //нажимаем по селектору на меню карта

        SelenideElement itemMenuMapActive = $(By.cssSelector("#app > div:nth-child(3) > ul > li.hide-lt-500-important.selected > a"));//убеждаемся, что карта открылась и стала активна
        Assert.assertTrue(itemMenuMapActive.isDisplayed());
        // только нет возможности тыкнуть на элемент карты, ибо это вообще не часть сайта
    }


}
