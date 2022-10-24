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
class LogRecordDto(page: Int, size: Int, sort: Sort?) : PageRequest(page, size, sort), Serializable {
    var id: String? = null
    var tenant: String? = null
    var type: @Length(max = 200, message = "type max length is 200") String? = null
    var subType: String? = null
    var bizNo: @Length(max = 200, message = "bizNo max length is 200") String? = null
    var operator: @Length(max = 63, message = "operator max length 63") String? = null
    var action: @Length(max = 511, message = "operator max length 511") String? = null
    var fail = false
    var createTime: Date? = null
    var extra: String? = null
}
