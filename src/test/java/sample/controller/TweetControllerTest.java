package sample.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;

import sample.model.Tweet;
import sample.repository.TweetRepository;

public class TweetControllerTest {

  @Test
  public void testCreate() {

    // 準備：テストデータ
    Tweet data = new Tweet();
    data.txt = "メッセージ";

    // 準備：モック（リポジトリ）
    TweetRepository mock = mock(TweetRepository.class);
    when(mock.save(data)).thenReturn(data);

    // 準備：テスト対象クラス
    TweetController target = new TweetController();
    target.repo = mock;

    // 実行：テスト対象メソッド
    Map<String, Tweet> result = target.create(data);

    // 検証：ツイートが等しいこと
    Tweet tweet = result.get("tweet");
    assertEquals("メッセージ", tweet.txt);
  }
}
