package service;

import java.sql.SQLException;
import java.util.function.Function;

/**
 * Behaves similarly to {@link Function} except that it can throw
 * {@link SQLException}
 */
interface SQLFunction<T, R> {

	R apply(T t) throws SQLException;

}
