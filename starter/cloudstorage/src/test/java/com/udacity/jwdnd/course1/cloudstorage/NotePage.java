package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NotePage {

    @FindBy(id="noteModalOpen")
    private WebElement modalOpenButton;

    @FindBy(id="note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-id")
    private WebElement noteIdField;



    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="note-save-button")
    private WebElement noteSaveButton;

    @FindBy(id="userTable")
    private WebElement noteTable;

    private WebDriverWait wait;

    private WebDriver webDriver;

    public NotePage(WebDriver driver){
        webDriver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void addNote(String title, String description){
        wait.until(ExpectedConditions.elementToBeClickable(noteTab));
        noteTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(modalOpenButton));
        modalOpenButton.click();
        wait.until(ExpectedConditions.visibilityOf(noteTitleField));
        noteTitleField.clear();
        noteTitleField.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
        noteSaveButton.click();
    }

    public void deleteNote(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(noteTab));
        noteTab.click();

        WebElement deleteButton = element.findElement(By.cssSelector(".btn.btn-danger"));

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        deleteButton.click();
    }

    public void editNote(WebElement tableElement, String title, String description) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(noteTab));
        noteTab.click();

        WebElement titleElement = tableElement.findElement(By.tagName("th"));

        WebElement descriptionElement = tableElement.findElement(By.tagName("td"));

        WebElement editButton = tableElement.findElement(By.cssSelector(".btn.btn-success"));

        wait.until(ExpectedConditions.elementToBeClickable(editButton));

        editButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(modalOpenButton));
        Thread.sleep(2000);
        noteTitleField.clear();
        noteTitleField.sendKeys(title);

        noteDescription.clear();
        noteDescription.sendKeys(description);

        noteSaveButton.click();

        Thread.sleep(10000);

        wait.until(ExpectedConditions.elementToBeClickable(noteTab));
        noteTab.click();

    }

    public WebElement getNoteTable(){
        return noteTable;
    }

    public WebElement getNoteTitleField() {
        return noteTitleField;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }
}
