package com.ict.excute;

import com.ict.hive.HiveJDBC;
import com.ict.mysql.MysqlJDBC;

import java.sql.ResultSet;

/**
 * Created by yangyaru on 2018/10/30.
 */
public class LoadData {

    public static void main(String[] args) {
        System.out.println("Start load data from hive to mysql...");
        try {
            HiveJDBC hiveJDBC = new HiveJDBC();
            MysqlJDBC mysqlJDBC = new MysqlJDBC();
            hiveJDBC.init();
            mysqlJDBC.init();
            String selectStr = "";
            ResultSet rs = hiveJDBC.select(selectStr);
            while (rs.next()) {

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        System.out.println("Load data succeed!");
    }
}
