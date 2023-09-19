package mytest.maven.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

public class HttpUtil {
	
	private String value;
	
	public HttpUtil(String value) {
		this.value = value;
	}
	
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(this.value, tClass);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	không đọc được tiếng việt
//	public static HttpUtil readJson (BufferedReader reader) {
//		StringBuilder jsonReaded = new StringBuilder();
//		String line;
//		try {
//			while((line = reader.readLine()) != null) {
//				jsonReaded.append(line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//		return new HttpUtil(jsonReaded.toString());
//	}
	
	//đọc tiếng việt
	public static HttpUtil readJson (HttpServletRequest req) {
		StringBuilder jsonReaded = new StringBuilder();
		String line;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
			while((line = reader.readLine()) != null) {
				jsonReaded.append(line);
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return new HttpUtil(jsonReaded.toString());
	}
}
