package com.example.framework.testsupport.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonEntity {

	@JsonAlias({"ID", "iD", "Id"})
	private String id;
}
