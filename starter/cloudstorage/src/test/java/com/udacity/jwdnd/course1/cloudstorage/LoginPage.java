package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id="inputUsername")
    private WebElement userNameField;

    @FindBy(id="inputPassword")
    private WebElement passwordField;

    @FindBy(id="login-button")
    private WebElement submitButton;

    private WebDriver webDriver;

    public LoginPage(WebDriver driver){
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginWithUserNameAndPassword(String userName, String password){
        userNameField.clear();
        userNameField.sendKeys(userName);

        passwordField.clear();
        passwordField.sendKeys(password);

        submitButton.click();
    }


}
