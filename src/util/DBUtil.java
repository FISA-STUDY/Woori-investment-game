/* 모든 DAO 클래스들이 공통으로 사용하는 클래스
 *  - DAO 권장 설계 : TABLE당 1:1개발
 *  - DBUtil의 생성자도 
 * 
 *  24시간 365일 실행되는 서버의 실행 코드라 간주
 * - 요청 수와 무관하게 최초 단 한번만 1회성으로 공유자원 초기화하는 공통 코드로 간주
 * - db의 driver 로딩 로직 
 * - 시스템 다운 방지를 위한 리소스 최적화
 * : Connection 수는 절대 제한(유한자원임)
 * - web server 내부에서 설정으로 db server 시스템 동시 접속 수 제어 예정
 * - Connection Pool 기법 (CP)
 * - Connection 제공, 회수하는 주체 필요(javax.sql.DataSource가 할 예정)
 * 
 * 결론
 * 		- driver로딩 한번만 실행 보장
 *  	- Connection 객체 반환 로직
 *  	- DB의 설정 정보는 별도의 key로 db접속 정보 매핑해서 properties 
 *  		파일로 분리
 *		- 참고
 *			: 설정 정보들을 코드에서 분리하는 원조가 JDBC
 *			- Spring에선 default설정파일로 활용  	
 * 
 */

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
//file로부터 1byte 단위로 read하는 기능

// properties 파일에서 key로 values만 쏙쏙 뽑는 기능


import java.util.Properties;
/* DBUtil은 편집 불가능한 구조 권장
 */



public class DBUtil {
	///key로 value값 활용시만 사용하는 API
	private static Properties dbInfo = new Properties();
	
	private DBUtil() {}
	
	static {
		//존재하는 파일로부터 자바 코드로 read하는 API
		try {
			dbInfo.load(new FileInputStream("dbinfo.properties"));
			Class.forName(dbInfo.getProperty("jdbc.driver"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(dbInfo.getProperty("jdbc.url"),dbInfo.getProperty("jdbc.id"),
				dbInfo.getProperty("jdbc.pw"));
		return  conn;
	}
	
	
	// 자원 반환 필수(query)
	public static Connection close(Connection conn, Statement stmt, ResultSet rs) {
			/*	DB연동 지원은 코드로 해제 필수
			 *  자원해제 순서 - ResultSet -> Statement -> Connection 순으로 해제 
			 */
			try {
				if(rs!=null) {
					rs.close();
					rs = null;
				}
				if(stmt!= null) {
					stmt.close();
					stmt = null;
				}
				if(conn!=null) {
					conn.close();
					conn = null;
				}
				
			} catch (Exception e2) {
				e2.getStackTrace();
			}

		return null;
	}
	
	// 자원 반환 필수(insert/update/delete)
	public static void close(Connection conn, Statement stmt) {
		/*	DB연동 지원은 코드로 해제 필수
		 *  자원해제 순서 - ResultSet -> Statement -> Connection 순으로 해제 
		 */
		try {
			if(stmt!= null) {
				stmt.close();
				stmt = null;
			}
			if(conn!=null) {
				conn.close();
				conn = null;
			}
			
		} catch (Exception e2) {
			e2.getStackTrace();
		}

	}
	
	public static void main(String[] args) {
		try {
			System.out.println(dbInfo.getProperty("jdbc.url"));
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
