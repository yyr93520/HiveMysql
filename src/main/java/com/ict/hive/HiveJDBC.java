package com.ict.hive;

import com.ict.utils.Properties;
import java.sql.*;

/**
 * Created by yangyaru on 2018/10/30.
 */
public class HiveJDBC {

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // 加载驱动、创建连接
    public void init() throws Exception {
        try {
            Class.forName(Properties.driverName);
            conn = DriverManager.getConnection(Properties.url,Properties.user,Properties.password);
            stmt = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // 创建数据库
    public void createDatabase() throws Exception {
        String sql = "create database hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询所有数据库
    public void showDatabases() throws Exception {
        String sql = "show databases";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // 创建表
    public void createTable() throws Exception {
        String sql = "create table emp(\n" +
                "empno int,\n" +
                "ename string,\n" +
                "job string,\n" +
                "mgr int,\n" +
                "hiredate string,\n" +
                "sal double,\n" +
                "comm double,\n" +
                "deptno int\n" +
                ")\n" +
                "row format delimited fields terminated by '\\t'";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询所有表
    public void showTables() throws Exception {
        String sql = "show tables";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // 查看表结构
    public void descTable() throws Exception {
        String sql = "desc emp";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
    }

    // 加载数据
    public void loadData() throws Exception {
        String filePath = "/home/hadoop/data/emp.txt";
        String sql = "load data local inpath '" + filePath + "' overwrite into table emp";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 查询数据
    public ResultSet select(String sql) throws Exception {
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        //while (rs.next()) {
        //    System.out.println(rs.getString("empno") + "\t\t" + rs.getString("ename") + "\t\t" + rs.getString("job"));
        //}
        return rs;
    }

    // 统计查询（会运行mapreduce作业）
    public void countData() throws Exception {
        String sql = "select count(1) from emp";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt(1) );
        }
    }

    // 删除数据库
    public void dropDatabase() throws Exception {
        String sql = "drop database if exists hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 删除数据库表
    public void deopTable() throws Exception {
        String sql = "drop table if exists emp";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 释放资源
    public void destory() throws Exception {
        if ( rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}