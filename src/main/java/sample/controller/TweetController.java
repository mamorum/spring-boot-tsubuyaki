package sample.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sample.model.Tweet;
import sample.repository.TweetRepository;

@RestController
@RequestMapping("/tweet")
public class TweetController {

  @Autowired
  TweetRepository repo;

  @PostMapping
  public Map<String, Tweet> create(
    @RequestBody Tweet tweet
  ) {
    return Collections.singletonMap(
      "tweet", repo.save(tweet)
    );
  }

  @GetMapping
  public Map<String, Iterable<Tweet>> read() {
    return Collections.singletonMap(
      "tweet", repo.findAllByOrderByUpdateTimeDesc()
    );
  }

  @PutMapping("/{id}")
  public void update(
    @PathVariable Long id, @RequestParam String txt
  ) {
    Tweet tweet = repo.findById(id).get();
    tweet.txt = txt;
    repo.save(tweet);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repo.deleteById(id);
  }
}
