package org.khmeracademy.akd.services.impl;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.khmeracademy.akd.entities.Document;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.social.google.api.drive.UploadParameters;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class UploadFileToGoogleServiceImpl {
	private SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Date d=null;
    private String date=null;
	public Document uploadDocument(String path,String title,String description,String parentID,int docTypeNum,int userID) throws GeneralSecurityException, IOException{
		//	CODE CONNECT WITH GOOGLE API
		String scope="https://www.googleapis.com/auth/drive";
		
		
		// ALL KHMER DOCS GOOGLE DRIVE API
		
		String serviceAccountID="all-khmer-docs@all-khmer-docs-146405.iam.gserviceaccount.com";
		String ServiceAccountPrivateKey="ALL-KHMER-DOCS-4ef8850572e9.p12";	
		
		String con = parentID.toLowerCase();
		
		if(con.equals(null) || con.equals("") || con.equals(" ")){				
			parentID="0BybKdIgWtK8tNTZUbGQwMzVpYjQ";
		}
				
		
		System.out.println("CatID for file: "+parentID);
		
		//String title="My File";
		//String description="";
		boolean viewed=true;
		boolean restricted=false;
		String embedLink=null;			//default
		String exportLink="";
		String thumbnailURL="";
		int status=1;				//default

									
		//TODO: TO SET THE SCOPE FOR ACCESSING TO OUR GOOGLE DRIVE
		Set<String> scopes = new HashSet<>();
		scopes.add(scope);
				
		//TODO: 1. AUTHENTICATION WITH GOOGLE SERVICE ACCOUNT
        GoogleCredential googleCredential = new GoogleCredential.Builder()
									        					.setTransport(new NetHttpTransport())
									        					.setJsonFactory(JacksonFactory.getDefaultInstance())
									        					.setServiceAccountId(serviceAccountID)
									        					.setServiceAccountScopes(scopes)
									        					.setServiceAccountPrivateKeyFromP12File(new File(ServiceAccountPrivateKey))
									        					.build();
        if(googleCredential.getAccessToken()==null){
			googleCredential.refreshToken();					
		}
                
       // System.out.println(googleCredential.refreshToken() + " " + googleCredential.getAccessToken() + " ");

        //TODO: 2. TO CREATE THE GOOGLE OBJECT WITH THE ACCESS TOKEN.
		Google google = new GoogleTemplate(googleCredential.getAccessToken());
		
		//TODO: 3. TO CREATE THE FOLDER IN GOOGLE DRIVE
		/*DriveFile folder = google.driveOperations().createFolder("0B4RhbtI4DXY_QWVOWkFiSTlRY1E", "AKD");*/
		
		//TODO: 4. TO GET THE FILE FROM THE SERVER TO UPLOAD (Create File in Google Drive.)
		Resource resource = new FileSystemResource(path);
		
		//TODO: 5. TO CREATE THE METADATA FOR FILE UPLOAD TO GOOGLE DRIVE
		
		String fileName=resource.getFilename();				
		DriveFile.Builder metaData = DriveFile.builder()
											.setTitle(title)		//fileName
											.setDescription(description)
											.setParents(parentID)
											.setViewed(viewed)
											.setRestricted(restricted);				
		
		if(fileName.toLowerCase().endsWith(".pptx") || fileName.toLowerCase().endsWith(".ppt")){
			 metaData.setMimeType("application/vnd.google-apps.presentation");
		}
		
		DriveFile myFile = metaData.build();
		UploadParameters parameters = new UploadParameters();
		
		//TODO: 6. TO CREATE THE FILE IN GOOGLE DRIVE
		DriveFile file1 = google.driveOperations().upload(resource, myFile, parameters);
		

	
		/*System.out.println("getEmbedLink:  "+file1.getEmbedLink());
		System.out.println("size:  "+file1.getFileSize());

		System.out.println("getMimeType:  "+file1.getMimeType());
		System.out.println("getExportLinks:  "+file1.getExportLinks());
		
		System.out.println("isFolder:  "+file1.isFolder());*/	
		
	
		
		if(fileName.toLowerCase().endsWith(".pptx") || fileName.toLowerCase().endsWith(".ppt")){
			embedLink="https://docs.google.com/presentation/d/"+ file1.getId()+"/embed?start=false&loop=false&delayms=3000";
			exportLink="https://docs.google.com/presentation/d/"+file1.getId()+"/export/pptx";
		}
		
		else if(fileName.toLowerCase().endsWith(".pdf")){
			embedLink="https://drive.google.com/file/d/"+ file1.getId()+"/preview";
			exportLink="https://drive.google.com/uc?export=download&id="+file1.getId();
		}
		else if(fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith("docx")){
			embedLink="https://drive.google.com/file/d/"+ file1.getId()+"/preview";
			exportLink="https://drive.google.com/uc?export=download&id="+file1.getId();
		}
		else{
			embedLink="";
		}

		thumbnailURL="https://drive.google.com/thumbnail?&sz=w320&id="+file1.getId();
		Document doc = new Document();
		d=new Date();
		date=sdf.format(d);
		
		System.out.println(file1.getTitle()+" Upload to Google Drive Successful!");
		
		doc.setDocID(file1.getId());
		doc.setTitle(file1.getTitle());
		doc.setDes(file1.getDescription());
		doc.setEmbedLink(embedLink);
		doc.setThumbnailURL(thumbnailURL);
		doc.setExportLink(exportLink);
		doc.setView(0);
		doc.setShare(0);
		doc.setCreatedDate(date);
		doc.setDocTypeNum(docTypeNum);
		doc.setUserID(userID);
		doc.setCatID(parentID);
		doc.setStatus(status);
		return doc;
		
	}
}
