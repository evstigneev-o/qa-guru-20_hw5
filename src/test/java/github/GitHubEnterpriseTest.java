package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


/*
На главной странице GitHub выберите меню Solutions -> Enterprize с помощью команды hover для Solutions.
Убедитесь что загрузилась нужная страница (например что заголовок - "Build like the best."
 */
public class GitHubEnterpriseTest {
    @BeforeAll
    public static void setUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true;
        Configuration.headless = true;
    }

    @Test
    public void githubEnterpriseShouldOpen(){
        open("https://github.com");
        $(".header-menu-wrapper").$(byText("Solutions")).hover();
        $("[href='/enterprise']").click();
        $(".enterprise-hero").shouldHave(Condition.text("Build like the best"));
    }
}
