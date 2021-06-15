package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class API {
	 public static void main(String[] args) throws IOException, ParseException {
		 Scanner sc = new Scanner(System.in);
		 System.out.print("검색어를 입력하세요:");
		 String movie_name =sc.nextLine();
		 String clientId="eijE8qHa_1hx_dP1QYcR";
		 String clientSecret ="a7iTRbDMnh";
		 
		 String text = URLEncoder.encode(movie_name,"UTF-8");
		 String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;
		 
		 URL url = new URL(apiURL);
		 HttpURLConnection con = (HttpURLConnection)url.openConnection();
		 con.setRequestMethod("GET");
		 con.setRequestProperty("X-Naver-Client-Id", clientId);
		 con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		 
		 int responseCode = con.getResponseCode();
		 
		 BufferedReader br;
		 
		 if(responseCode == 200) {
			 br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		 }else{
			 br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		 }
		 String inputLine;
		 StringBuffer response = new StringBuffer();
		 while((inputLine = br.readLine()) != null) {
			 response.append(inputLine);
		 }
		 br.close();
		 
		 
		 String text2 = response.toString();
		 JSONParser jsonParser = new JSONParser();
		 JSONObject jsonObject = (JSONObject) jsonParser.parse(text2);
		 JSONArray infoArray = (JSONArray) jsonObject.get("items");
		 for(int i=0; i<infoArray.size(); i++) {
			 System.out.println("==================(item_"+i+")=================");
			 JSONObject itemobject = (JSONObject) infoArray.get(i);
			 System.out.println("title:\t"+itemobject.get("title"));
			 System.out.println("subtitle:\t"+itemobject.get("subtitle"));
			 System.out.println("director:\t"+itemobject.get("director"));
			 System.out.println("actor:\t"+itemobject.get("actor"));
			 System.out.println("UserRating:\t"+itemobject.get("userRating")+"\n");
		 }
	}
}
