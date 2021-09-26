package com.example.demo.bearchmark;

import org.junit.jupiter.api.Test;

import javax.script.*;

public class FormulaTest {
    @Test
    public void testGroovy() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
        Compilable compilable = (Compilable) engine;
        for (int i = 0; i < 1000; i++) {
            String script = "F*F1 + T*T1 +A*A1 +P*P1"; // 定义函数并调用
            CompiledScript JSFunction; // 解析编译脚本函数
            JSFunction = compilable.compile(script);
            Bindings bindings = engine.createBindings(); // Local级别的Binding
            bindings.put("F", 150);
            bindings.put("T", 60000);
            bindings.put("A", 300000);
            bindings.put("P", 10);
            // 系数
            bindings.put("F1", 1.2);
            bindings.put("T1", 1.7);
            bindings.put("A1", 1.2);
            bindings.put("P1", 0.5);

            JSFunction.eval(bindings);
        }
    }


    @Test
    public void testJsEngine() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Compilable compilable = (Compilable) engine;

        for (int i = 0; i < 1000; i++) {
            String script = "F*F1 + T*T1 +A*A1 +P*P1"; // 定义函数并调用
            CompiledScript JSFunction; // 解析编译脚本函数
            JSFunction = compilable.compile(script);
            Bindings bindings = engine.createBindings(); // Local级别的Binding
            bindings.put("F", 150);
            bindings.put("T", 60000);
            bindings.put("A", 300000);
            bindings.put("P", 10);
            // 系数
            bindings.put("F1", 1.2);
            bindings.put("T1", 1.7);
            bindings.put("A1", 1.2);
            bindings.put("P1", 0.5);

            JSFunction.eval(bindings);
        }
    }
}
