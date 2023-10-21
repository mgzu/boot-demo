package com.example.framework.log.entity;

import com.example.framework.common.entity.PersistableEntity;
import com.mzt.logapi.beans.CodeVariableType;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Map;

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@Setter
@Getter
@Entity(name = "t_log_record")
public class LogRecord extends PersistableEntity {
	/**
	 * 租户
	 */
	private String tenant;

	/**
	 * 保存的操作日志的类型，比如：订单类型、商品类型
	 *
	 * @since 2.0.0 从 prefix 修改为了type
	 */
	@NotBlank
	@Length(max = 200)
	private String type;

	/**
	 * 日志的子类型，比如订单的C端日志，和订单的B端日志，type都是订单类型，但是子类型不一样
	 *
	 * @since 2.0.0 从 category 修改为 subtype
	 */
	private String subType;

	/**
	 * 日志绑定的业务标识
	 */
	@NotBlank
	@Length(max = 200)
	private String bizNo;

	/**
	 * 操作人
	 */
	@NotBlank
	@Length(max = 63)
	private String operator;

	/**
	 * 日志内容
	 */
	@NotBlank
	@Length(max = 511)
	private String action;

	/**
	 * 记录是否是操作失败的日志
	 */
	private boolean fail;

	/**
	 * 日志的创建时间
	 */
	private Date createTime;

	/**
	 * 日志的额外信息
	 *
	 * @since 2.0.0 从detail 修改为extra
	 */
	private String extra;

	/**
	 * 打印日志的代码信息
	 * CodeVariableType 日志记录的ClassName、MethodName
	 */
	@Transient
	private Map<CodeVariableType, Object> codeVariable;
}
