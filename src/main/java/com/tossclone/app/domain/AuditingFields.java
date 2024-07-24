package com.tossclone.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@ToString
public abstract class AuditingFields {

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false,
            updatable = false)
    protected LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false,
            updatable = false,
            length = 100)
    protected String createdBy;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    protected LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(nullable = false,
            length = 100)
    protected String modifiedBy;
}
