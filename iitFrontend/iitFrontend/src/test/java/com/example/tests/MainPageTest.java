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

    @Test
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
    }

    @Test
    public void exportAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Площадки для выгула (дрессировки) собак").getExportElement().click();
        $(By.cssSelector("#dropExport > li:nth-child(1) > div > a")).click();
    }

    @Test
    public void filterAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Приюты для бродячих животных").getCaptionTextElement().click();
        registerNewTab();
        $(By.cssSelector("#rows-content > thead > tr.filter_tr > td:nth-child(4) > div > div")).click();
        $(By.cssSelector("#dropFilter-District > ul > li:nth-child(1)")).click();
    }

    @Test
    public void mapAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Площадки для выгула (дрессировки) собак").getCaptionTextElement().click();
        registerNewTab();
        $(By.xpath("//*[@id=\"rows\"]")).findElementByCssSelector("#id_272622268 > div > div.map-card-link.r1");
    }

    @Test
    public void mapVetAnimalSubCategory() {

        DataPage dataPage = page(DataPage.class);
        dataPage.navigate();
        dataPage.shouldBeOpened();

        Categories subCategories = dataPage.getSubCategories();
        subCategories.getSubCategoryByName("Ветеринарные учреждения").getCaptionTextElement().click();
        registerNewTab();
        $(By.cssSelector("#app > div:nth-child(3) > ul > li.hide-lt-500-important > a")).click();
    }

}
