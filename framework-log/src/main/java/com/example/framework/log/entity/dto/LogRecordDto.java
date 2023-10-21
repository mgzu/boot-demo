package com.example.framework.log.entity.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
public class LogRecordDto extends PageRequest implements Serializable {

	public LogRecordDto(int page, int size, Sort sort) {
		super(page, size, sort);
	}

	private String id;
	private String tenant;

	@Length(max = 200)
	private String type;
	private String subType;

	@Length(max = 200)
	private String bizNo;

	@Length(max = 63)
	private String operator;

	@Length(max = 511)
	private String action;
	private boolean fail = false;
	private Date createTime;
	private String extra;
}
