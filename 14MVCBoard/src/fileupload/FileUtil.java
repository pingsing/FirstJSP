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
	// 파일 업로드(multipart/form-data 요청) 처리
	public static MultipartRequest uploadFile(HttpServletRequest request,
			String saveDirectory, int maxPostSize) {
		try {
			// 파일 업로드
			return new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8");
		} catch (Exception e) {
			// 업로드 실패
			e.printStackTrace();
			return null;
		}
	}
	
	// 명시한 파일을 찾아 다운로드합니다.
	public static void download(HttpServletRequest request, HttpServletResponse response,
					String directory, String sfileName, String ofileName) {
		String sDirectory = request.getServletContext().getRealPath(directory);
		
		try {
			// 파일을 찾아 입력 스트림 생성
			File file = new File(sDirectory, sfileName);
			InputStream inStream = new FileInputStream(file);
			
			// 한글 파일명 깨짐 방지
			String client = request.getHeader("User-Agent");	// 사용자의 브라우저 타입을 반환
			if (client.indexOf("WOW64") == 1) {	
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			} else {
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			// 파일 다운로드용 응답 헤더 설정
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
								"attachment; filename=\"" + ofileName +"\"");
			response.setHeader("Content-Length", "" + file.length() );
			
			// 출력 스트림 초기화
			// out.clear();
			
			// response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream outStream = response.getOutputStream();
			
			// 출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while ( (readBuffer = inStream.read(b)) > 0) {
				outStream.write(b, 0, readBuffer);
			}
			
			// 입/출력 스트림 닫음
			inStream.close();
			outStream.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace();
		}
	}
}
