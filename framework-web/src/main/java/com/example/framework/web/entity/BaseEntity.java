package com.example.framework.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author MaGuangZu
 * @since 2021-12-29
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity extends PersistableEntity {

	@NotNull
    @CreatedBy
	@Column(nullable = false)
    private String createdBy;

	@NotNull
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
    private LocalDateTime createdDate;

	@Nullable
    @LastModifiedBy
    private String lastModifiedBy;

	@Nullable
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

	private String remark;

	@NotNull
	@Version
	@Column(nullable = false)
	private int versionLock;

}
