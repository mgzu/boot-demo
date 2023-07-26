package com.example.framework.testsupport.benchmark;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.script.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class FormulaBenchmark {

	ScriptEngine engine = new ScriptEngineManager().getEngineByName("kotlin");
	Compilable compilable = (Compilable) engine;

	String kotlinScript = """
		bindings["F"].toString().toBigDecimal() * bindings["F1"].toString().toBigDecimal() +
		bindings["T"].toString().toBigDecimal() * bindings["T1"].toString().toBigDecimal() +
		bindings["A"].toString().toBigDecimal() * bindings["A1"].toString().toBigDecimal() +
		bindings["P"].toString().toBigDecimal() * bindings["P1"].toString().toBigDecimal()
		""";
	String spElScript = "#F*#F1 + #T*#T1 + #A*#A1 + #P*#P1";
	CompiledScript compiledScript;
	Expression expression;

	@Setup
	public void setUp() throws ScriptException {
		compiledScript = compilable.compile(kotlinScript);
		ExpressionParser parser = new SpelExpressionParser();
		expression = parser.parseExpression(spElScript);
	}

	@Benchmark
	public void testKotlinEngine(Blackhole bh) throws ScriptException {
		Bindings bindings = engine.createBindings();

		bindings.put("F", 150);
		bindings.put("T", 60000);
		bindings.put("A", 300000);
		bindings.put("P", 10);
		bindings.put("F1", 1.2);
		bindings.put("T1", 1.7);
		bindings.put("A1", 1.2);
		bindings.put("P1", 0.5);

		Object eval = compiledScript.eval(bindings);
		bh.consume(eval);
	}

	@Benchmark
	public void testSpEL(Blackhole bh) {
		EvaluationContext context = new StandardEvaluationContext();

		context.setVariable("F", 150);
		context.setVariable("T", 60000);
		context.setVariable("A", 300000);
		context.setVariable("P", 10);
		context.setVariable("F1", 1.2);
		context.setVariable("T1", 1.7);
		context.setVariable("A1", 1.2);
		context.setVariable("P1", 0.5);

		String value = expression.getValue(context, String.class);
		bh.consume(value);
	}
}
