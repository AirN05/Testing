package com.example.components;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class CategoryItemDataPage extends ElementsContainer {
    public SelenideElement getIcon() {
        return getSelf().find(".category-icon");
    }

    public SelenideElement getTextElement() {
        return getSelf().$(".category-caption");
    }

    public SelenideElement getCaptionTextElement() {
        return getSelf().$(".ds-caption-text");
    }

    public SelenideElement getExportElement() {
        return getSelf().$("#dropDepartmentsLink");
    }

    public SelenideElement getSizeElement() {
        return getSelf().$("#dropExport > li:nth-child(1) > div > a");
    }



    public CategoryItemDataPage getFormat() {
        CategoryItemDataPage category = new CategoryItemDataPage();
        category.getSelf().find(By.xpath(String.format("//*[@id=\"dropExport\"]/li[1]/div/a")));
        return category;
    }
}
