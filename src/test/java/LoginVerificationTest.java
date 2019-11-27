import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginVerificationTest {
    @Test
    void loginPageTestWithSQL() throws SQLException, InterruptedException {

        val runner = new QueryRunner();
        val usersSQL = "SELECT * FROM users;";
        val codesSQL = "SELECT * FROM auth_codes WHERE created >= (now() - interval 5 minute) ORDER BY created DESC;";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
        ) {
            val user = runner.query(conn, usersSQL, new BeanHandler<>(User.class));

            open("http://localhost:9999");
            $("[data-test-id=login] input").setValue(user.getLogin());
            $("[data-test-id=password] input").setValue(user.getCol());
            $("[data-test-id=action-login]").click();
            Thread.sleep(2000);
            val code = runner.query(conn, codesSQL, new BeanHandler<>(VerificationCodes.class));
            $("[data-test-id=code] input").setValue(code.getCode());
            $("[data-test-id=action-verify]").click();
            $("[data-test-id=dashboard]").shouldBe(visible);
        }
    }
}