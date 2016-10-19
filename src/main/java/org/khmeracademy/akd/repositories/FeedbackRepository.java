package org.khmeracademy.akd.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.Feedback;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository {
	
	@Delete(SQL.DELETE)
	boolean delete(int id);
	
	@Update(SQL.UPDATE)
	boolean update(Feedback feed);
	
	@Insert(" INSERT INTO akd_feedbacks VALUES(nextval('akd_feedbacks_feed_id_seq'),#{date},#{des},#{status}) ")
	boolean insert(Feedback feed);
	
	@Select("SELECT COUNT(feed_id) from akd_feedbacks")
	public Long count();
	
	@Select(SQL.SELECT)
	@Results({
		@Result(property="feedbackID", column="feed_id"),
		@Result(property="date", column="feed_date"),
		@Result(property="des", column="feed_des"),
		@Result(property="status", column="status")
	})
	ArrayList<Feedback> findAll(@Param("pagination") Paging pagination);
	
	@Select(SQL.FIND_ONE)
	@Results({
		@Result(property="feedbackID", column="feed_id"),
		@Result(property="date", column="feed_date"),
		@Result(property="des", column="feed_des"),
		@Result(property="status", column="status")
	})
	Feedback findOne(int id);
	
}

interface SQL{
	String SELECT="SELECT * from akd_feedbacks ORDER BY feed_id ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}";
	String FIND_ONE="SELECT * from akd_feedbacks WHERE feed_id=#{feedbackID}";
	
	String DELETE="UPDATE akd_feedbacks SET status= 0 WHERE feed_id=#{feedbackID}";
	
	String UPDATE="UPDATE akd_feedbacks SET "
			+ "feed_date=#{date},"
			+ "feed_des=#{des},"
			+ "status=#{status} "
			+ "WHERE "
			+ "feed_id=#{feedbackID}";
		
}




