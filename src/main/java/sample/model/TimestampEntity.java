package sample.model;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class TimestampEntity {

  public Timestamp updateTime;

  @Column(updatable = false)
  public Timestamp createTime;

  // INSERTの時に作成日時と更新日時を設定
  @PrePersist
  public void prePersist() {
    Timestamp ts = Timestamp.from(Instant.now());
    this.createTime = ts;
    this.updateTime = ts;
  }
 
  // UPDATEの時に更新日時を設定
  @PreUpdate
  public void preUpdate() {
    this.updateTime = Timestamp.from(Instant.now());
  }
}
