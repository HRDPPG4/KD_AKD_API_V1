package org.khmeracademy.akd.services;
import org.springframework.web.multipart.MultipartFile;

public interface UploadToServerService {
	public String uploadDocument(MultipartFile files, String folder);
	public String uploadUserProfile(MultipartFile files, String folder);
	public String uploadDocThumbnail(MultipartFile files, String folder);
	
}
