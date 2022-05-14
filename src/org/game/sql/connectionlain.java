package org.game.sql;
import com.sun.xml.internal.ws.encoding.xml.XMLMessage;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class connectionlain {


        public Connection getConnect() {
        Connection conn=null;
        String url="jdbc:mysql://127.0.0.1:3306/user";
        String user="root";
        String password="1234";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url, user, password);
            return conn;
        }catch(ClassNotFoundException e){
            System.out.println("错误");
            e.printStackTrace();
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }


}



