import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.Test;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginVerificationTest {
    @Test
    void loginPageTestWithSQL() throws SQLException, InterruptedException {
        val runner = new QueryRunner();
        DataHelper dataHelper = new DataHelper();
        val codesSQL = "SELECT * FROM auth_codes WHERE created >= (now() - interval 5 minute) ORDER BY created DESC;";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
        ) {
            dataHelper.validLogin();
            val code = runner.query(conn, codesSQL, new BeanHandler<>(VerificationCodes.class));
            dataHelper.codeField.setValue(code.getCode());
            dataHelper.verificationPage();
        }
    }
}