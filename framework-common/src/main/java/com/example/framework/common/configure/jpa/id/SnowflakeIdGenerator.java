package com.example.framework.common.configure.jpa.id;

import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.data.id.Snowflake;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author MaGuangZu
 * @since 2022-01-04
 */
public class SnowflakeIdGenerator implements IdentifierGenerator {
    Snowflake snowflake = IdUtil.getSnowflake();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflake.nextIdStr();
    }
}
