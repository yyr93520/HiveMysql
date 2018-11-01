package com.ict.utils;

public interface Properties {

    // Kafka configure
	final static String zkConnect = "10.2.0.13:2181,10.2.0.14:2181,10.2.0.15:2181,10.2.0.16:2181,10.2.0.17:2181";
	final static String bkConnect = "10.98.201.25:9092,10.98.201.26:9092,10.98.201.27:9092";
	final static String groupId = "srj12138";
	final static String zkTimeout = "60000";
	final static String csTimeout = "-1";
	final static String commitEnable = "true";
	final static String commitInterval = "100";
	final static String offsetReset = "largest";
	final static String maxRecords = "1000";
	final static String sessionTimeout = "35000";
	final static String topic =  "b_603_t_dx_rz_jkdx";

	// Hive configure
	final static String driverName = "org.apache.hive.jdbc.HiveDriver";
	final static String url = "jdbc:hive2://hdpcomprs:10000/db_comprs";
	final static String user = "hadoop";
	final static String password = "";

    // MySQL configure
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

}
