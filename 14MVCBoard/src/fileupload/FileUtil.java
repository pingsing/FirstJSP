package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {
	// ���� ���ε�(multipart/form-data ��û) ó��
	public static MultipartRequest uploadFile(HttpServletRequest request,
			String saveDirectory, int maxPostSize) {
		try {
			// ���� ���ε�
			return new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8");
		} catch (Exception e) {
			// ���ε� ����
			e.printStackTrace();
			return null;
		}
	}
	
	// ����� ������ ã�� �ٿ�ε��մϴ�.
	public static void download(HttpServletRequest request, HttpServletResponse response,
					String directory, String sfileName, String ofileName) {
		String sDirectory = request.getServletContext().getRealPath(directory);
		
		try {
			// ������ ã�� �Է� ��Ʈ�� ����
			File file = new File(sDirectory, sfileName);
			InputStream inStream = new FileInputStream(file);
			
			// �ѱ� ���ϸ� ���� ����
			String client = request.getHeader("User-Agent");	// ������� ������ Ÿ���� ��ȯ
			if (client.indexOf("WOW64") == 1) {	
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			// ���� �ٿ�ε�� ���� ��� ����
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
								"attachment; filename=\"" + ofileName +"\"");
			response.setHeader("Content-Length", "" + file.length() );
			
			// ��� ��Ʈ�� �ʱ�ȭ
			// out.clear();
			
			// response ���� ��ü�κ��� ���ο� ��� ��Ʈ�� ����
			OutputStream outStream = response.getOutputStream();
			
			// ��� ��Ʈ���� ���� ���� ���
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while ( (readBuffer = inStream.read(b)) > 0) {
				outStream.write(b, 0, readBuffer);
			}
			
			// ��/��� ��Ʈ�� ����
			inStream.close();
			outStream.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("���ܰ� �߻��Ͽ����ϴ�.");
			e.printStackTrace();
		}
	}
}
