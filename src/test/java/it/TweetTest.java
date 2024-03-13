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
    $$(".tsubuyaki .txt p").get(0).should(appear);
    $$(".tsubuyaki .txt p").get(0).shouldHave(text("こんにちは。"));
  }
}
