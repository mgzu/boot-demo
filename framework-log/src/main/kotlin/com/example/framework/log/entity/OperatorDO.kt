package com.example.framework.log.entity

import com.mzt.logapi.beans.Operator

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
class OperatorDO(operator: String) : Operator() {
    init {
        operatorId = operator
    }
}
