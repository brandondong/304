package core;

interface IDatabaseCredentials {

	/**
	 * CSID preceded by "ora_"
	 */
	String getUsername();

	/**
	 * Student # preceded by "a"
	 */
	String getPassword();

}
