package dev.codex.redindiansnight.Common.Models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<T> implements Serializable {
    @CreatedDate
    @Column(updatable = false, name = "created_at")
    @Getter @Setter
    private Instant createdAt = Instant.now();
    @LastModifiedDate
    @Column(name = "last_modified_at")
    @Getter @Setter
    private Instant lastModifiedAt = Instant.now();

    public abstract T getId();
}
