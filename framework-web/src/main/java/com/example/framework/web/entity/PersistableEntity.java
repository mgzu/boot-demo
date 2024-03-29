package com.example.framework.web.entity;

import com.example.framework.web.configure.jpa.id.SnowflakeIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * @author MaGuangZu
 * @since 2021-12-29
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@MappedSuperclass
public class PersistableEntity implements Persistable<String>, Serializable {

	@Id
	@GeneratedValue(generator = "snowflakeId")
	@GenericGenerator(name = "snowflakeId", type = SnowflakeIdGenerator.class)
	protected String id;

	@JsonIgnore
	@Transient
	@Override
	public boolean isNew() {
		return StringUtils.isNotBlank(id);
	}

}
