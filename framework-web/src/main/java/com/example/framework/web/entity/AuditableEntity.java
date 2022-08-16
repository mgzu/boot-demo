package com.example.framework.web.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @author MaGuangZu
 * @since 2021-12-29
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableEntity extends PersistableEntity {

    @CreatedBy
    @Nullable
    private String createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date createdDate;

    @LastModifiedBy
    @Nullable
    private String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date lastModifiedDate;

//    public Optional<LocalDateTime> getCreatedDate() {
//        return null == createdDate ? Optional.empty()
//                : Optional.of(LocalDateTime.ofInstant(createdDate.toInstant(), ZoneId.systemDefault()));
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
//    }
//
//    public Optional<LocalDateTime> getLastModifiedDate() {
//        return null == lastModifiedDate ? Optional.empty()
//                : Optional.of(LocalDateTime.ofInstant(lastModifiedDate.toInstant(), ZoneId.systemDefault()));
//    }
//
//    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
//        this.lastModifiedDate = Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
//    }

}
