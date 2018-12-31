package nrw.bieker.resthibernatetutorial.io;

import org.springframework.stereotype.Component;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

@Component
public class JcfisFileWriter {
	
	public boolean createFileInNetworkFolder() {
		 boolean successful = false;
		 try {
		       
		     NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("securess","jb","Pojo6Mojo9!");
		      String newFileName = "sample.txt";
		      String fileContent = "Just a Test File";
		        String path = "smb://10.50.20.18/edv/" + newFileName;
		        SmbFile sFile = new SmbFile(path, auth);
		        SmbFileOutputStream sfos = new SmbFileOutputStream(sFile);
		      sfos.write(fileContent.getBytes());
		     successful = true;
		      sfos.close();
		   } catch (Exception e) {
		     successful = false;
		     System.out.println("Problem in Creating New File :" + e);
		   }
		   return successful;
		}
}