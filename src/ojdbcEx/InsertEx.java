package ojdbcEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertEx {

  public static void main(String[] args) {
	        // 0. import java.sql.*;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            // 1. JDBC 드라이버 (Oracle) 로딩
	            Class.forName("oracle.jdbc.driver.OracleDriver");

	            // 2. Connection 얻어오기
	            String url = "jdbc:oracle:thin:@localhost:1521:xe";
	            conn = DriverManager.getConnection(url, "webdb", "webdb");
	            System.out.println("접속성공");

	         // 3. SQL문 준비 / 바인딩 / 실행
	            String query = "INSERT INTO author VALUES (seq_author_id.nextval, ?, ? )";
	            pstmt = conn.prepareStatement(query);

	            pstmt.setString(1, "강풀");
	            pstmt.setString(2, "웹툰1세대");

	            int count = pstmt.executeUpdate();

	            // 4.결과처리
	            System.out.println(count + "건 처리");

//	            // 4.결과처리
//	            while (rs.next()) {
//	                int authorId = rs.getInt("author_id");
//	                String authorName = rs.getString("name");
//	                String authorDesc = rs.getString("author_desc");
//	                System.out.println(authorId + "\t" + authorName + "\t" + authorDesc + "\t");
	            

	        } catch (ClassNotFoundException e) {
	            System.out.println("error: 드라이버 로딩 실패 - " + e);
	        } catch (SQLException e) {
	            System.out.println("error:" + e);
	        } finally {
	            // 5. 자원정리
	            try {
	                if (rs != null) { rs.close(); }                
	                if (pstmt != null) { pstmt.close(); }
	                if (conn != null) { conn.close(); }
	            } catch (SQLException e) {
	                System.out.println("error:" + e);
	            }

	        }

	    }

	}