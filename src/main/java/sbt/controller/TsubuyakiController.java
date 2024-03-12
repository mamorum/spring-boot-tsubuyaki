package sbt.controller;

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

import sbt.model.Tsubuyaki;
import sbt.repository.TsubuyakiRepository;

@RestController
@RequestMapping("/tsubuyaki")
public class TsubuyakiController {

  @Autowired
  TsubuyakiRepository repo;

  @PostMapping
  public Map<String, Tsubuyaki> create(
    @RequestBody Tsubuyaki tsubuyaki
  ) {
    return Collections.singletonMap(
      "tsubuyaki", repo.save(tsubuyaki)
    );
  }

  @GetMapping
  public Map<String, Iterable<Tsubuyaki>> read() {
    return Collections.singletonMap(
      "tsubuyaki", repo.findAllByOrderByUpdatedTimeDesc()
    );
  }

  @PutMapping("/{id}")
  public void update(
    @PathVariable Long id, @RequestParam String txt
  ) {
    Tsubuyaki tsubuyaki = repo.findById(id).get();
    tsubuyaki.txt = txt;
    repo.save(tsubuyaki);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repo.deleteById(id);
  }
}
