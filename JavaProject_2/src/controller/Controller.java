package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class Controller {
	
	static JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	
	public static void main(String[] args) {
		/*
		 * 회원 전체 리스트 출력하기.
		 */
		
		/*
		 * 출력예시
		 * 회원아이디	회원이름	폰번호	가입일
		 * tttt			회원1		-		2023.10.29 15:37
		 * rrrr			회원2				2023.10.30 15:37
		 * eeee			회원3				2023.10.31 15:37
		 * 
		 */
		
		//step 1. 쿼리 작성
		String sql = "SELECT ID, NAME, PHONE, JOINDATE "
				+ "FROM MB "
				+ "WHERE ID IN('tttt','rrrr','eeee') AND DELYN IS NULL";
		
		//step 2. 리스트 전체 출력해보기
		List<Map<String, Object>> list = jdbc.selectList(sql);
//		System.out.println(list);
		
		//step 3. 원하는 데이터만 출력해보기
		String id, name, phone, joindate;
		System.out.print("회원아이디\t회원이름\t전화번호\t가입일\n");
		for(Map<String, Object> map : list) {
			id = (String) map.get("ID");
			name = (String) map.get("NAME");
			phone = (String) map.get("PHONE");
			joindate = map.get("JOINDATE").toString();
		
		//step 4. 날짜 형식 맞추기
		//simpledateformat 사용
		//2023-10-31 16:08:48.0 -> 2023.10.31 15:37
		
		//2023-10-31 16:08:48.0
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//2023.10.31 15:37
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		
		try {
			Date d = sdf.parse(joindate);
			String result = sdf2.format(d);
			joindate = result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.print(id+"\t\t"+name+"\t\t"+phone+"\t"+joindate+"\n");
		
		
//		String dateStr = sdf.format(joindate);
//		System.out.println(dateStr);
//			String jd = (String) map.get("JOINDATE");
//			jd = sdf.format(d);
//			System.out.println(jd);
	
//		String obj = list.get().toString();
//		System.out.println(obj);
		
		}
		
		
	}
}

