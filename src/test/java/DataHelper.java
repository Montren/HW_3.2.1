import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DataHelper {
    User user = new User();
    public void validLogin() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
        Thread.sleep(2000);
    }

    SelenideElement codeField = $("[data-test-id=code] input");

    public void verificationPage(){
        $("[data-test-id=action-verify]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
    }
}
