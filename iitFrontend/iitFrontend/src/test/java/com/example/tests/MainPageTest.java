package com.example.tests;

import com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.components.Categories;
import com.example.components.CategoryItemMainPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
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
        $(By.cssSelector("#rows-caption")).find("#"); //смотрим наличие всех столбцов в таблице
        $(By.cssSelector("#rows-caption")).find("Ведомственная принадлежность");
        $(By.cssSelector("#rows-content")).find("Адресный ориентир");
        $(By.cssSelector("#rows-content")).find("Элементы площадки");
        $(By.cssSelector("#rows-content")).find("Освещение площадки");
        $(By.cssSelector("#rows-content")).find("Ограждение площадки");

    }

    @Test   // DMru-6:[FUN] Экспорт данных о ветеринарных учреждениях в формате json
    public void exportAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();
        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Площадки для выгула (дрессировки) собак").getExportElement().click();//нажимаем на кнопку "Экспорт"
        $(By.cssSelector("#dropExport > li:nth-child(1) > div > a")).find("json"); //проверяем открывшийся список
        $(By.cssSelector("#dropExport > li:nth-child(2) > div > a")).find("xlsx");
        $(By.cssSelector("#dropExport > li:nth-child(3) > div > a")).find("xml");
        $(By.cssSelector("#dropExport > li:nth-child(4) > a")).find("geojson");
        $(By.cssSelector("#dropExport > li:nth-child(1) > div > a")).click(); //Нажимаем на json
    }

    @Test // DMru-24:[FUN] Фильтрация данных по значению столбца
    public void filterAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Приюты для бродячих животных").getCaptionTextElement().click();
        registerNewTab();
        $(By.cssSelector("#rows-content > thead > tr.filter_tr > td:nth-child(4) > div > div")).click(); //нажимаем на столбец
        $(By.cssSelector("#dropFilter-District > ul > li:nth-child(1)")).click(); //выбираем первый пункт
        $(By.cssSelector("#rows-content > thead > tr.filter_tr > td:nth-child(4) > div > i.filter-reset")).click(); //кликаем на кнопку, чтоб отфильтровалось
        $(By.cssSelector("#rows > div:nth-child(3) > div.pager.page-container > span")).find("1 из 13"); //смотрим количество отфильтрованных записей
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
        $(By.cssSelector("#rows-caption > tbody > tr:nth-child(1)")).click(); //кликаем по элементу
        $(By.cssSelector("#card")).find("Департамент культуры города Москвы"); //находим карточку с описанием
        $(By.cssSelector("##mapCard")).find("Карта"); //находим сам элемент карты
    }

    @Test //DMru-27:[FUN] Просмотр информации о ветеринарном учреждении из режима "Карта"
    public void mapVetAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Ветеринарные учреждения").getCaptionTextElement().click();
        registerNewTab();
        $(By.cssSelector("#app > div:nth-child(3) > ul > li.hide-lt-500-important > a")).click(); //нажимаем по селектору на меню карта
        $(By.cssSelector("#app > div:nth-child(3) > ul")).findElementByCssSelector("#app > div:nth-child(3) > ul > li.hide-lt-500-important.selected > a");//убеждаемся, что карта открылась и стала активна
        // только нет возможности тыкнуть на элемент карты, ибо это вообще не часть сайта
    }


}
