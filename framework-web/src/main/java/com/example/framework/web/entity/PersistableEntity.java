package com.example.framework.web.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author MaGuangZu
 * @since 2021-12-29
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@MappedSuperclass
public class PersistableEntity implements Persistable<String> {

    @Id
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.example.framework.web.config.jpa.id.SnowflakeIdGenerator")
    private String id;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
