package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import vo.MemberVO;

public class UpdateImgAction implements Action {
	private static UpdateImgAction instance;

	private UpdateImgAction() {
	}

	public static UpdateImgAction getUpdateImgAction() {
		if (instance == null) {
			instance = new UpdateImgAction();
		}
		return instance;
	}

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String rootPath = "/Users/andaegeun/Desktop/95main/dditBoard";
		//String rootPath = "/tomcat/webapps/ROOT";
		//String root = rootPath + "/src/main/webapp/storage";
		String root = "/adg0807/tomcat/webapps/ROOT/storage";
		String encType = "utf-8";
		PrintWriter out = response.getWriter();
		MultipartRequest multi = null;
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("user");
		int maxSize = 5 * 1024 * 1024;
		
		File dir = new File("/adg0807/tomcat/webapps/ROOT/storage");
	    File[] fileList = dir.listFiles();

	    if(fileList != null) {
	    	for(int j = 0; j < fileList.length; j++) {
	    		File file = fileList[j];
	    		if(file.isFile()){
	    			int dot = file.getName().lastIndexOf(".");
	    			String fileName = file.getName().substring(0, dot);
	    			if(fileName.equals(user.getMemId())) {
	    				file.delete();
	    				break;
	    			}
	    		}
	    	}		    	
	    } 
		
		multi = new MultipartRequest(request, root, maxSize, encType, new DefaultFileRenamePolicy());
		
		Enumeration<?> files = multi.getFileNames();
		String name = "";
		while(files.hasMoreElements()) {
			name = files.nextElement() + "";
		}
		
		String originalFile = multi.getOriginalFileName(name);
		File currentFile = new File(root + "/" + originalFile);
		
		int dot = originalFile.indexOf(".");
		String extention = originalFile.substring(dot, originalFile.length());
		
		File newFile = new File(root + "/" + user.getMemId() + extention);
		
		FileInputStream input = new FileInputStream(currentFile);
		FileOutputStream output = new FileOutputStream(newFile);
		
		int c;
		byte[] buf = new byte[1024];
		
		while((c = input.read(buf)) != -1) {
			output.write(buf, 0, c);
		}
		
		currentFile.delete();
		
		input.close();
		output.close();
		
		out.println("<script>");
		out.println("alert('업로드 완료!')");
		out.println("location.replace('/member?cmd=index')");
		out.println("</script>");
		
	}
}
