package org.indigo.cloudproviderranker.utils.exceptions;

import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;

import static org.junit.Assert.*;

import org.drools.compiler.commons.jci.compilers.EclipseCompilationProblem;
import org.drools.compiler.commons.jci.problems.CompilationProblem;
import org.drools.compiler.kie.builder.impl.MessageImpl;
import org.drools.compiler.kie.builder.impl.ResultsImpl;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.indigo.cloudproviderranker.utils.exceptions.RulesBuildException;

public class RulesBuildExceptionTest {

	@Test
	public void test() {

		DefaultProblemFactory pf = new DefaultProblemFactory();
		IProblem pProblem = pf.createProblem("error_file.java".toCharArray(), 1, null, 1, null, 1, 1, 10, 100, 2);
		CompilationProblem cp = new EclipseCompilationProblem(pProblem);
		Message m = new MessageImpl(1, cp);
		Results results = new ResultsImpl();
		results.getMessages().add(m);
		RulesBuildException rbe = new RulesBuildException(results);

		assertTrue(rbe != null);
		assertTrue(rbe.getResults().getMessages().get(0).getPath().equals("error_file.java"));
		assertTrue(rbe.getResults().getMessages().get(0).getId() == 1);
	}
}
