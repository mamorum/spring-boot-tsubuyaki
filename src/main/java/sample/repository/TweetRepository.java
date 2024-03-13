package sample.repository;

import org.springframework.data.repository.CrudRepository;

import sample.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet, Long> {
  Iterable<Tweet> findAllByOrderByUpdatedTimeDesc();
}
