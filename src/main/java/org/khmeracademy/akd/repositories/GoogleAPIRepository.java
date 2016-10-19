package org.khmeracademy.akd.repositories;

import org.apache.ibatis.annotations.Insert;
import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.entities.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleAPIRepository {
		
	@Insert("INSERT INTO akd_documents VALUES ('doc_id','title','des','embed_link','thumbnail_url','export_link',0,0,'2016-08-11',1,2,'0B4RhbtI4DXY_QWVOWkFiSTlRY1E',1)")
	boolean uploadFile(Document doc);
	
	//@Insert("INSERT INTO tbl_user(name, gender,email) VALUES(#{name}, #{gender},#{email})")
	boolean uploadFolder(Category cat);
	
	
}
