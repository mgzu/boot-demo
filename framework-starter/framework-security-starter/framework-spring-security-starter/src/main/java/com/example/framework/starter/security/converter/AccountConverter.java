package com.example.framework.starter.security.converter;

import com.example.framework.starter.security.entity.AccountUserDetails;
import com.example.framework.system.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author MaGuangZu
 * @since 2024-05-24
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountConverter {

	AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

	AccountUserDetails toUserDetails(Account account);

}
