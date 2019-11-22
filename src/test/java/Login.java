import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.Test;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Login {
    @Test
    void setUp() throws SQLException {

        val runner = new QueryRunner();
        val usersSQL = "SELECT * FROM users;";

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
            $("[data-test-id=code] input").shouldBe(visible);
        }
    }
}