package it;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;

public class TweetTest {

  @Test
  public void userCanPostTsubuyaki() {
    // つぶやき投稿
    open("/");
    $("#txt").setValue("こんにちは。");
    $("#create").click();
    
    // 検証
    var $p = $$(".tweet .txt p").get(0);
    $p.should(appear);
    $p.shouldHave(text("こんにちは。"));
    
    // 削除
    $$(".tweet").get(0).$(".delete").click();
    switchTo().alert().accept();
  }
}
