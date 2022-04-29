package com.example.log.parse;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
public interface IParseFunction {

    default boolean executeBefore() {
        return false;
    }

    String functionName();

    String apply(String value);
}
