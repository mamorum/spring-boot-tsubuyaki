package sbt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Tsubuyaki extends TimestampEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;

  @Column(nullable = false)
  public String txt;

  @Version
  @Column(nullable = false)
  public long version;
}
