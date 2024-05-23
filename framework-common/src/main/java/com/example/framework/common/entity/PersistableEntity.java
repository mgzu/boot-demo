package com.example.framework.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class PersistableEntity implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	protected String id;

}
