package org.cgiar.dapa.ccafs.tpe.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * Provides a standard implementation that supports the JPA GROUP_CONCAT of the
 * SQL
 * 
 * @author nmatovu
 *
 */
public class GroupConcat implements SQLFunction {
	// private String name;
	// private Type registeredType;

	private static final String GROUP_CONCAT = "group_concat";
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Type getReturnType(Type firstArgumentType, Mapping mapping)
			throws QueryException {
		// return StandardBasicTypes.STRING;
		return StandardBasicTypes.STRING;

		// return registeredType == null ? firstArgumentType : registeredType;
	}

	@Override
	public boolean hasArguments() {

		return true;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {

		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String render(Type firstArgumentType, List arguments,
			SessionFactoryImplementor factory) throws QueryException {

		log.info("Arguments: " + arguments);
		// If there are two arguments, the second argument will be used for
		// ordering.
		if (arguments.size() > 1) {
			final StringBuilder buf = new StringBuilder();
			buf.append(GROUP_CONCAT).append('(');
			for (int i = 0; i < arguments.size(); i++) {
				if (i == 0)
					buf.append(arguments.get(i));
				else
					buf.append(" order by ").append(arguments.get(i));

				// if (i < arguments.size() - 1) {
				// buf.append(", ");
				// }
			}
			log.info(buf.toString());
			return buf.append(')').toString();
		} else if (arguments.size() == 1) {
			return GROUP_CONCAT + "(" + arguments.get(0)
					+ " order by climate0_.month)";
		} else
			throw new QueryException(new IllegalArgumentException(
					"group_concat shoudl have one arg"));
	}

}
