package com.example.framework.log.entity.bo;

import com.mzt.logapi.beans.Operator;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@Setter
@Getter
public class OperatorBO extends Operator {
	public OperatorBO(String operator) {
		super(operator);
	}
}
