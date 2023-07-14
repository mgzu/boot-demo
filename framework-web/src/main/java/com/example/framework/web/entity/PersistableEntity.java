package com.example.framework.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * @author MaGuangZu
 * @since 2021-12-29
 */
@Access(AccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@MappedSuperclass
public class PersistableEntity implements Persistable<String>, Serializable {

    @Id
    @GeneratedValue(generator = "snowflakeId")
    @GenericGenerator(name = "snowflakeId", strategy = "com.example.framework.web.configure.jpa.id.SnowflakeIdGenerator")
    protected String id;

    @Transient
    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }
}
