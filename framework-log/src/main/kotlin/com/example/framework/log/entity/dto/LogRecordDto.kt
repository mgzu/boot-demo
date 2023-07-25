package com.example.framework.log.entity.dto

import org.hibernate.validator.constraints.Length
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.io.Serializable
import java.util.*

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
class LogRecordDto(page: Int, size: Int, sort: Sort) : PageRequest(page, size, sort), Serializable {
	var id: String? = null
	var tenant: String? = null

	@Length(max = 200)
	var type: String? = null
	var subType: String? = null

	@Length(max = 200)
	var bizNo: String? = null

	@Length(max = 63)
	var operator: String? = null

	@Length(max = 511)
	var action: String? = null
	var fail = false
	var createTime: Date? = null
	var extra: String? = null
}
