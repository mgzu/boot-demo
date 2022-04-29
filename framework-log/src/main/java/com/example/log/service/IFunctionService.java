package com.example.log.service;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
public interface IFunctionService {
    String apply(String functionName, String value);

    boolean beforeFunction(String functionName);
}
