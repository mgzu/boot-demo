package com.example.framework.web.configure.jpa.id;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
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
