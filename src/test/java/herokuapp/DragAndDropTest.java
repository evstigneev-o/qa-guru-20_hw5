package herokuapp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/*
(опциональное) Запрограммируйте Drag&Drop с помощью Selenide.actions()
 - Откройте https://the-internet.herokuapp.com/drag_and_drop
 - Перенесите прямоугольник А на место В
 - Проверьте, что прямоугольники действительно поменялись
 - В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()
 */
public class DragAndDropTest {
    SelenideElement columnA = $("#column-a");
    SelenideElement columnB = $("#column-b");

    @BeforeAll
    public static void setUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        //Configuration.holdBrowserOpen = true;
        //Configuration.headless = true;
    }

    @Test
    public void rectangleShouldBeMovedByDragAndDrop() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        columnA.dragAndDrop(to(columnB));
        columnA.shouldHave(text("B"));
        columnB.shouldHave(text("A"));
    }

    @Test
    public void rectangleShouldBeMovedByDeprecatedMethod() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        columnA.dragAndDropTo(columnB);
        columnA.shouldHave(text("B"));
        columnB.shouldHave(text("A"));
    }

    // actions doesn't work :(
    @Test
    public void rectangleShouldBeMovedBySelenideActionsDragAndDrop() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        Selenide.actions().dragAndDrop(columnA, columnB).build().perform();
        columnA.shouldHave(text("B"));
        columnB.shouldHave(text("A"));
    }

    // actions doesn't work :(
    @Test
    public void rectangleShouldBeMovedBySelenideActionsMoveToElement() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        Selenide.actions().clickAndHold(columnA).moveToElement(columnB).build().perform();
        columnA.shouldHave(text("B"));
        columnB.shouldHave(text("A"));
    }
}
