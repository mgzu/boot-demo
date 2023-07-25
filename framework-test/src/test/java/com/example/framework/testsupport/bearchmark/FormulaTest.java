package com.example.framework.testsupport.bearchmark;

import javax.script.*;

class FormulaTest {

    private static final int FOR_I = 100000;

    //    @Test
    void testGroovy() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
        Compilable compilable = (Compilable) engine;

        String script = "F*F1 + T*T1 +A*A1 +P*P1";
        CompiledScript compiledScript;
        compiledScript = compilable.compile(script);
        Bindings bindings = engine.createBindings();

        for (int i = 0; i < FOR_I; i++) {
            bindings.put("F", 150);
            bindings.put("T", 60000);
            bindings.put("A", 300000);
            bindings.put("P", 10);
            bindings.put("F1", 1.2);
            bindings.put("T1", 1.7);
            bindings.put("A1", 1.2);
            bindings.put("P1", 0.5);

            compiledScript.eval(bindings);
        }
    }

    //    @Test
    void testJsEngine() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Compilable compilable = (Compilable) engine;

        String script = "F*F1 + T*T1 +A*A1 +P*P1";
        CompiledScript compiledScript;
        compiledScript = compilable.compile(script);
        Bindings bindings = engine.createBindings();

        for (int i = 0; i < FOR_I; i++) {
            bindings.put("F", 150);
            bindings.put("T", 60000);
            bindings.put("A", 300000);
            bindings.put("P", 10);
            bindings.put("F1", 1.2);
            bindings.put("T1", 1.7);
            bindings.put("A1", 1.2);
            bindings.put("P1", 0.5);

            compiledScript.eval(bindings);
        }
    }
}
