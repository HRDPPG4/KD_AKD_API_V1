package org.khmeracademy.akd.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.entities.Log;
import org.khmeracademy.akd.entities.Report;
import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository {
	@Delete("UPDATE akd_reports SET status = 0 WHERE report_id =#{id}")
	boolean delete(int id);
	
	@Update(REPORT_SQL.UPDATE)
	boolean update(Report rep);
	
	@Select("SELECT COUNT(report_id) from akd_reports")
	public Long count();
	
	@Insert(REPORT_SQL.INSERT)
	boolean insert(Report rep);
	
	
	@Select(REPORT_SQL.SELECT)
	@Results({
		@Result(property="reportID", column="report_id"),
		@Result(property="date", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status"),
		@Result(property="users", column="user_id", one = @One(select = "getUser")),
		@Result(property="documents", column="doc_id", one = @One(select = "getDocument"))
	})
	ArrayList<Report> findAll(@Param("pagination") Paging pagination);

	
	@Select("SELECT * FROM akd_users WHERE user_id=#{userID}")
	@Results({
		@Result(property="userID", column="user_id"),
		@Result(property="name", column="name")	
	})
	ArrayList<User> getUser();
	
	@Select("SELECT * FROM akd_documents WHERE doc_id=#{docID}")
	@Results({
		@Result(property="docID", column="doc_id"),
		@Result(property="title", column="title")	
	})
	ArrayList<Document> getDocument();
	
	
	@Select(REPORT_SQL.FIND_ONE)
	@Results({
		@Result(property="reportID", column="report_id"),
		@Result(property="date", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="userID", column="user_id"),
		@Result(property="docID", column="doc_id"),
		@Result(property="status", column="status")	
	})
	Report findOne(int id);
	
}


interface REPORT_SQL{
	String SELECT="SELECT * from akd_reports WHERE status = 1 ORDER BY report_id ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}";
	
	String FIND_ONE="SELECT * from akd_reports WHERE report_id=#{reportID}";
	
	String DELETE="DELETE FROM akd_reports WHERE report_id=#{reportID}";
	
	String UPDATE="UPDATE akd_reports SET "
			+ "created_date=#{date},"
			+ "remark=#{remark},"
			+ "user_id=#{userID},"
			+ "doc_id=#{docID},"
			+ "status=#{status} "
			+ "WHERE report_id=#{reportID}";
	
	String INSERT="INSERT INTO "
			+ "akd_reports("
			+ "created_date,"
			+ "remark,"
			+ "user_id,"
			+ "doc_id,status)"
			+ "VALUES"
			+ "(#{date},"
			+ "#{remark},"
			+ "#{userID},"
			+ "#{docID},"
			+ "#{status})";	
}




