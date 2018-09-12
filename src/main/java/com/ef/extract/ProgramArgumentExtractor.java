package com.ef.extract;

import static com.ef.Constant.ARGUMENT_PREFIX;
import static com.ef.Constant.DELEMETER;
import static com.ef.Constant.ILLEGAL_PROGRAM_ARGUMENT_ERROR;

import java.lang.reflect.Method;

import com.ef.entity.ProgramArgument;

public class ProgramArgumentExtractor {

	public ProgramArgument extractProgramArguments(String arguments[]) {

		ProgramArgument programArgument = new ProgramArgument();

		if (arguments.length != 4) {
			printIllegalArgumentExceptionAndExit();
		}

		for (String argument : arguments) {
			if (!argument.startsWith(ARGUMENT_PREFIX)) {
				printIllegalArgumentExceptionAndExit();
			}
			String truncatedArgument = argument.substring(2);
			String[] tokens = truncatedArgument.split(DELEMETER);
			String argumentName = tokens[0];
			String argumentValue = tokens[1];
			this.invokeSetter(programArgument, argumentName, argumentValue);
		}
		return programArgument;
	}

	private void invokeSetter(Object obj, String argumentName, Object argumentValue) {
		try {
			Method method = obj.getClass().getMethod(
					"set" + argumentName.substring(0, 1).toUpperCase() + argumentName.substring(1),
					new Class[] { argumentValue.getClass() });
			method.invoke(obj, argumentValue);
		} catch (Exception ex) {
			printIllegalArgumentExceptionAndExit();
		}
	}

	private void printIllegalArgumentExceptionAndExit() {
		System.out.println(ILLEGAL_PROGRAM_ARGUMENT_ERROR);
		System.exit(1);
	}

}
