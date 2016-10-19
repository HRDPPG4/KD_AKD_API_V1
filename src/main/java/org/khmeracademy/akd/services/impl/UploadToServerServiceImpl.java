package org.khmeracademy.akd.services.impl;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.khmeracademy.akd.services.UploadToDBService;
import org.khmeracademy.akd.services.UploadToServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadToServerServiceImpl implements UploadToServerService{
	
	@Autowired
	private UploadToDBService uploadToDBService;

	@Override
	public String uploadDocument(MultipartFile files, String folder) {
		String filePath = null;
		if(files==null){
			System.out.println("file empty");
			return null;
		}
		if(folder=="" || folder==null)
			folder = "D:/KSHRD/ALL KHMER DOCS/AKD File";
		
	//	String UPLOAD_PATH = "file" + folder;
		String UPLOAD_PATH =folder;
		
		java.io.File path = new java.io.File(UPLOAD_PATH);
		if(!path.exists())
			path.mkdirs();
		
		String fileName = files.getOriginalFilename();
		fileName = UUID.randomUUID().toString() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println("File Type: "+fileName.substring(fileName.lastIndexOf('.')+1,fileName.length() ));
		
		//fileName = fileName + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		
		
		try {
			Files.copy(files.getInputStream(), Paths.get(UPLOAD_PATH, fileName));
			filePath = UPLOAD_PATH + "/" + fileName;
			System.out.println(filePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return filePath;
	}

	@Override
	public String uploadUserProfile(MultipartFile files, String folder) {
		String filePath = null;
		if(files==null){
			System.out.println("file empty");
			return null;
		}
		if(folder=="" || folder==null)
			folder = "src/main/webapp/resources/img/user-profile";
		
	//	String UPLOAD_PATH = "file" + folder;
		String UPLOAD_PATH =folder;
		
		java.io.File path = new java.io.File(UPLOAD_PATH);
		if(!path.exists())
			path.mkdirs();
		
		String fileName = files.getOriginalFilename();
		fileName = UUID.randomUUID().toString() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println("Original Name: "+fileName);
		System.out.println("File Type: "+fileName.substring(fileName.lastIndexOf('.')+1,fileName.length() ));
		
		//fileName = fileName + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		
		
		try {
			Files.copy(files.getInputStream(), Paths.get(UPLOAD_PATH, fileName));
			filePath = UPLOAD_PATH + "/" + fileName;
			System.out.println(filePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return filePath;
	}

	@Override
	public String uploadDocThumbnail(MultipartFile files, String folder) {
		String filePath = null;
		if(files==null){
			System.out.println("file empty");
			return null;
		}
		if(folder=="" || folder==null)
			folder = "src/main/webapp/resources/img/doc-thumbnail";
		
	//	String UPLOAD_PATH = "file" + folder;
		String UPLOAD_PATH =folder;
		
		java.io.File path = new java.io.File(UPLOAD_PATH);
		if(!path.exists())
			path.mkdirs();
		
		String fileName = files.getOriginalFilename();
		fileName = UUID.randomUUID().toString() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println("Original Name: "+fileName);
		System.out.println("File Type: "+fileName.substring(fileName.lastIndexOf('.')+1,fileName.length() ));
		
		//fileName = fileName + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		
		
		try {
			Files.copy(files.getInputStream(), Paths.get(UPLOAD_PATH, fileName));
			filePath = UPLOAD_PATH + "/" + fileName;
			System.out.println(filePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return filePath;
	}	

}



