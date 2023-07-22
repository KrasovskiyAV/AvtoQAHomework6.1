package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferHeader = $(byText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement to = $("[data-test-id='to'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement errorMassage = $("[data-test-id='error-massage']");

    public TransferPage() {
        transferHeader.shouldBe(visible);
    }

    public void makeTransfer(String amountTo, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountTo);
        from.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(String amountTo, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountTo, cardInfo);
        return new DashboardPage();
    }

    public void findErrorMassage(String expectedText) {
        errorMassage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}