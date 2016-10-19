package org.khmeracademy.akd.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.akd.entities.User;
import org.khmeracademy.akd.utilities.Paging;
import org.springframework.stereotype.Repository;
import org.khmeracademy.akd.entities.Role;
import org.khmeracademy.akd.entities.forms.UserLogin;


@Repository
public interface UserRepository {
	
	@Delete("UPDATE akd_users SET status= 0 WHERE user_id = #{id}")
	boolean delete(int id);
	
	@Update(USER_SQL.UPDATE)
	boolean update(User user);
	
	
	
	@Insert(USER_SQL.INSERT)
	boolean insert(User user);
	
	@Select(USER_SQL.COUNT)
	public Long count();
	
	@Select(USER_SQL.SELECT)
	@Results({
		@Result(property="userID", column="user_id"),
		@Result(property="name", column="name"),
		@Result(property="password", column="password"),
		@Result(property="email", column="email"),
		@Result(property="phone", column="phone"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="status", column="status"),		
		@Result(property="role", column="role")		
	})
	ArrayList<User> findAll(@Param("pagination") Paging pagination);
	
	
	@Select(USER_SQL.FIND_ONE)
	@Results({
		@Result(property="userID", column="user_id"),
		@Result(property="name", column="name"),
		@Result(property="password", column="password"),
		@Result(property="email", column="email"),
		@Result(property="phone", column="phone"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="status", column="status"),		
		@Result(property="role", column="role")
	})
	User findOne(int id);
	
	@Select("SELECT COUNT(*) FROM akd_users")
	@Results({
		@Result(property="userID", column="count"),
	})
	int getUserCount();
	
	
	@Results({
		@Result(property="userID", column="user_id"),
		@Result(property="name", column="name"),
		@Result(property="password", column="password"),
		@Result(property="email", column="email"),
		@Result(property="phone", column="phone"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="status", column="status"),		
		@Result(property="role", column="role"),
		@Result(property="roles" , column="user_id" ,
			many = @Many(select = "findRolesByUserId")
		)
	})
	@Select(USER_SQL.U_ROLES_BY_EMAIL)
	User findUserByEmail(@Param("u")UserLogin userlogin);
	
	@Select(USER_SQL.R_ROLES_BY_USER_ID)
	@Results(value={
			@Result(property="roleName" , column="role")
	})
	List<Role> findRolesByUserId(@Param("userID") int userID);
	
	
	@Update("UPDATE akd_users SET profile=#{profile} WHERE user_id=#{userID}")
	boolean uploadUserProfile(@Param("profile") String profile,@Param("userID")int userID);
	
	@Select("SELECT * FROM akd_users WHERE user_hash=#{userHash}")
	@Results({
		@Result(property="userID", column="user_id"),
		@Result(property="name", column="name"),
		@Result(property="password", column="password"),
		@Result(property="email", column="email"),
		@Result(property="phone", column="phone"),
		@Result(property="createdDate", column="created_date"),
		@Result(property="remark", column="remark"),
		@Result(property="status", column="status"),		
		@Result(property="role", column="role"),
		@Result(property="profile", column="profile"),
		@Result(property="userHash", column="user_hash")
	})
	User findUserByUserHash(String userHash);
	
	
}

interface USER_SQL{
//	String SELECT="SELECT * from akd_users ORDER BY user_id ASC";
	
	String SELECT="SELECT * from akd_users WHERE status=1 ORDER BY user_id ASC LIMIT #{pagination.limit} OFFSET #{pagination.offset}";
	
	String FIND_ONE="SELECT * from akd_users WHERE user_id=#{userID}";
	
	String DELETE="UPDATE akd_users SET status=0 WHERE user_id=#{id}";
	
	String UPDATE="UPDATE akd_users SET "
			+ "name=#{name},"
			+ "password=#{password},"
			+ "email=#{email},"
			+ "phone=#{phone},"
			+ "created_date=#{createdDate}, "
			+ "remark=#{remark},"
			+ "status=#{status},"
			+ "role=#{role}"
			+ "WHERE user_id=#{userID}";
	
	String UPDATE_STATUS="UPDATE akd_users SET "
			+ "status=0,"
			+ "WHERE user_id=#{userID}";
	
	String INSERT="INSERT INTO "
			+ "akd_users("
			+ "name,"
			+ "password,email,phone,created_date,remark,status,role,user_hash,profile)"
			+ "VALUES("
			+ "#{name},"
			+ "#{password},"
			+ "#{email},"
			+ "#{phone},"
			+ "#{createdDate},"
			+ "#{remark},"
			+ "#{status},"
			+ "#{role},"
			+ "#{userHash},"
			+ "'default.png')";	
	
	String COUNT="SELECT COUNT(user_id) FROM akd_users";

	String R_ROLES_BY_USER_ID = "SELECT "
			+ "role "
			+ "FROM "
			+ "akd_users "
			+ "WHERE "
			+ "user_id=#{userID}";
	String U_ROLES_BY_EMAIL = "SELECT "
			+ " * "
			+ " FROM "
			+ " akd_users "
			+ " WHERE "
			+ " email=#{u.email}";
}


