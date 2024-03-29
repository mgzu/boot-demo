package com.example.framework.log.entity

import com.example.framework.web.entity.PersistableEntity
import com.mzt.logapi.beans.CodeVariableType
import jakarta.persistence.Entity
import jakarta.persistence.Transient
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import java.util.*

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@Entity(name = "t_log_record")
class LogRecord : PersistableEntity() {
	/**
	 * 租户
	 */
	var tenant: String? = null

	/**
	 * 保存的操作日志的类型，比如：订单类型、商品类型
	 *
	 * @since 2.0.0 从 prefix 修改为了type
	 */
	@NotBlank
	@Length(max = 200)
	var type: String? = null

	/**
	 * 日志的子类型，比如订单的C端日志，和订单的B端日志，type都是订单类型，但是子类型不一样
	 * @since 2.0.0 从 category 修改为 subtype
	 */
	var subType: String? = null

	/**
	 * 日志绑定的业务标识
	 */
	@NotBlank
	@Length(max = 200)
	var bizNo: String? = null

	/**
	 * 操作人
	 */
	@NotBlank
	@Length(max = 63)
	var operator: String? = null

	/**
	 * 日志内容
	 */
	@NotBlank
	@Length(max = 511)
	var action: String? = null

	/**
	 * 记录是否是操作失败的日志
	 */
	var fail = false

	/**
	 * 日志的创建时间
	 */
	var createTime: Date? = null

	/**
	 * 日志的额外信息
	 *
	 * @since 2.0.0 从detail 修改为extra
	 */
	var extra: String? = null

	/**
	 * 打印日志的代码信息
	 * CodeVariableType 日志记录的ClassName、MethodName
	 */
	@Transient
	var codeVariable: Map<CodeVariableType, Any>? = null
}
