package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {
    @FindBy(id="credentialOpenButton")
    private WebElement modalOpenButton;
    

    @FindBy(id="credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement userNameField;

    @FindBy(id = "credential-password")
    private WebElement passwordField;



    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="credenital-save-button")
    private WebElement credentialSaveButton;

    @FindBy(id="credentialTable")
    private WebElement credentialTable;

    private WebDriverWait wait;

    private WebDriver webDriver;

    public CredentialPage(WebDriver driver){
        webDriver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void addCredential(String url, String username, String password){
        wait.until(ExpectedConditions.elementToBeClickable(credentialTab));
        credentialTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(modalOpenButton));
        modalOpenButton.click();
        wait.until(ExpectedConditions.visibilityOf(credentialUrlField));
        credentialUrlField.clear();
        credentialUrlField.sendKeys(url);

        userNameField.clear();
        userNameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);
        credentialSaveButton.click();
    }

    public void deleteCredential(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(credentialTab));
        credentialTab.click();

        WebElement deleteButton = element.findElement(By.cssSelector(".btn.btn-danger"));

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        deleteButton.click();
    }

    public void editNote(WebElement tableElement, String title, String description) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(credentialTab));
        credentialTab.click();

        WebElement titleElement = tableElement.findElement(By.tagName("th"));

        WebElement descriptionElement = tableElement.findElement(By.tagName("td"));

        wait.until(ExpectedConditions.elementToBeClickable(modalOpenButton));
        modalOpenButton.click();

        wait.until(ExpectedConditions.visibilityOf(credentialUrlField));

        JavascriptExecutor jse = (JavascriptExecutor)webDriver;

        jse.executeScript("document.querySelector('#note-id').setAttribute('type', 'text');");

        userNameField.sendKeys("1");

        credentialUrlField.clear();
        credentialUrlField.sendKeys(titleElement.getText());

        credentialUrlField.clear();
        credentialUrlField.sendKeys(title);

        noteDescription.clear();
        noteDescription.sendKeys(descriptionElement.getText());

        noteDescription.clear();
        noteDescription.sendKeys(description);

        credentialSaveButton.click();

        Thread.sleep(10000);

        wait.until(ExpectedConditions.elementToBeClickable(credentialTab));
        credentialTab.click();



    }

    public WebElement getCredentialTable(){
        return credentialTable;
    }

    public WebElement getNoteTitleField() {
        return credentialUrlField;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }
}
