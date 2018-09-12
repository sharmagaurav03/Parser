package com.ef;

public class Constant {
	public static final String ILLEGAL_PROGRAM_ARGUMENT_ERROR = "Illegal argument provided. Usage --startDate=<<yyyy-MM-dd.HH:mm:ss>> --duration=daily --threshold=<<numberic value of threshold>>";
	public static final String ARGUMENT_PREFIX="--";
	public static final String DELEMETER="=";
	
	public static final String MYSQL_DRIVER= "com.mysql.cj.jdbc.Driver";
	public static final String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/solution?" + "user=db_user&password=db_password_08_11";
	
	public static final String INPUT_DATE_FORMAT = "yyyy-MM-dd.HH:mm:ss";
	public static final String LOG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DAILY = "daily";
	public static final String HOURLY = "hourly";
	
	public static final String LOG_ENTRY_DELEMETER = "\\|";
	
	
	public static final String THREASHOLD_REACHED = "Threashold reached.";


}
