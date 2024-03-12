package sample.repository;

import org.springframework.data.repository.CrudRepository;

import sample.model.Tsubuyaki;

public interface TsubuyakiRepository extends CrudRepository<Tsubuyaki, Long> {
  Iterable<Tsubuyaki> findAllByOrderByUpdatedTimeDesc();
}
