package org.khmeracademy.akd.services.impl;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.khmeracademy.akd.entities.Category;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.stereotype.Service;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.extensions;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class UploadFolderToGoogleServiceImpl {
	private SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Date d=null;
    private String date=null;
	public Category upload(String parentID,String folderName,String folderDes,String status,String catIcon,int catLevel,int catNumOrder) throws GeneralSecurityException, IOException{
		//	CODE CONNECT WITH GOOGLE API
		String scope="https://www.googleapis.com/auth/drive";

		
		System.out.println("ParentID: "+parentID);
		
		//	ALL KHMER DOCS GOOGLE DRIVE API
		String serviceAccountID="all-khmer-docs@all-khmer-docs-146405.iam.gserviceaccount.com";
		String ServiceAccountPrivateKey="ALL-KHMER-DOCS-4ef8850572e9.p12";	
		
		String con = parentID.toLowerCase();
		
		if(con.equals(null) || con.equals("") || con.equals(" ")){				
			parentID="0BybKdIgWtK8tNTZUbGQwMzVpYjQ";
		}
		
		System.out.println("ParentID2: "+parentID);
		
		Set<String> scopes = new HashSet<>();
		scopes.add(scope);		
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
                
		Google google = new GoogleTemplate(googleCredential.getAccessToken());
		DriveFile folder = google.driveOperations().createFolder(parentID, folderName);
		
		if(con.equals(null) || con.equals("") || con.equals(" ")){				
			parentID="0";	//	SET PARENT_ID TO 0 BECAUSE DEFAULT VALUE OF PARENT ID IN DATABASE IS 0 BUT DEFAILT VALUE OF PARENT_ID IN GOOGLE DRIVE IS NOT.
							//	IF WE WANT TO USE THE SAME PARENT_ID IN DB AND GOOGLE DRIVE WE SHOULD CREATE ONE CATEGORY IN DB HAS ID LIKE IN DEFAULT FOLDER IN GOOGLE DRIVE.
		}
		
		Category cat=null;
		
		System.out.println("Cat Level in Upload to google service: "+catLevel);
		if(folder.getId()!=null && folder.getTitle()!=null){
			d=new Date();
			date=sdf.format(d);
			cat=new Category();
			cat.setCatID(folder.getId());
			cat.setCatName(folder.getTitle());
			cat.setCreatedDate(date);
			cat.setParentID(parentID);
			cat.setRemark(folderDes);
			cat.setStatus(Integer.valueOf(status));
			cat.setIcon(catIcon);
			cat.setLevel(catLevel);
			cat.setOrder(catNumOrder);
			
		}
		
		return cat;		
	}
}
