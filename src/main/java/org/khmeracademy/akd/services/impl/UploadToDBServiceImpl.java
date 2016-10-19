package org.khmeracademy.akd.services.impl;
import org.khmeracademy.akd.entities.Category;
import org.khmeracademy.akd.entities.Document;
import org.khmeracademy.akd.repositories.CategoryRepository;
import org.khmeracademy.akd.repositories.DocumentRepository;
import org.khmeracademy.akd.repositories.UserRepository;
import org.khmeracademy.akd.services.UploadToDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadToDBServiceImpl implements UploadToDBService{	
	@Autowired
	private DocumentRepository documentRepository;	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public boolean uploadDocument(Document doc) {
		return documentRepository.insert(doc);		
	}

	@Override
	public boolean uploadFolder(Category cat) {
		return categoryRepository.insert(cat);
	}

	@Override
	public boolean uploadUserProfile(String profile, int userID) {
		return userRepository.uploadUserProfile(profile,userID);
	}
	
	@Override
	public boolean uploadDocThumbnail(String thumbnail,String docID) {
		return documentRepository.uploadDocThumbnail(thumbnail,docID);
	}
}



