package com.example.pages;

import com.example.components.Categories;
import com.example.components.CategoryItemDataPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DataPage extends AbstractPage {
    public DataPage() {
        super();
        this.url = "https://data.mos.ru/opendata?categoryId=201&IsActual=true";
    }

    public AbstractPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public AbstractPage waitPageLoaded() {
        $(".loader-block").waitWhile(visible, 30000);
        return this;
    }

    public CategoryItemDataPage getSelectedItem() {
        CategoryItemDataPage category = new CategoryItemDataPage();
        category.setSelf($(".items-list .selected"));
        return category;
    }

//    public CategoryItemDataPage getSelectedDropdown() {
//        CategoryItemDataPage category = new CategoryItemDataPage();
//        category.setSelf($("#dropDepartmentsLink .selected"));
//        return category;
//    }


    public Categories getSubCategories() {
        Categories categories = new Categories();
        categories.setSelf($("#datasets"));
        return categories;
    }


}
