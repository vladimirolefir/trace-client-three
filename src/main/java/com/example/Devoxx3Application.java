package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

// posting data

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import java.net.URLEncoder;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import java.lang.Object;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@RestController
public class Devoxx3Application {

	private static final Logger LOG = Logger.getLogger(Devoxx3Application.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpServletRequest request;
	
	public static void main(String[] args) {
		SpringApplication.run(Devoxx3Application.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

	@RequestMapping("/")
	public String home() {

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			//LOG.log(Level.INFO, "KEY: " + key + " VALUE: " + value);
		}

		//LOG.log(Level.INFO, "you called three home");
		return "Hello World Three";
	}

	@RequestMapping("/callhome")
	public String callHome() {
		//LOG.log(Level.INFO, "calling three home");
		return restTemplate.getForObject("https://trace-client-three.herokuapp.com", String.class);
	}
	
	@RequestMapping("/reqthree")
	public String reqThree() {
		//LOG.log(Level.INFO, "you called three reqthree");
		
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			if(key.startsWith("X-")){
				//LOG.log(Level.INFO, "KEY: " + key + " VALUE: " + value);
			}
		}		
		
		return "Hello World from Req Three";
	}	
	
	@RequestMapping("/znext")
	public String zNext() throws Exception {
		
		String acctName = URLEncoder.encode("olefir@olehome.com", "UTF-8"); // "YOUR DEVORG USERNAME";
		String acctPw2 = URLEncoder.encode("321677533vvoV4UytQ6I06rGKu5iweKh7yPGK", "UTF-8"); // "YOUR DEVORG PASSWORD AND SECURITY TOKEN";
		String acctPw = URLEncoder.encode("321677533vvo8m07wXPLMHN9RLKG707aCypo", "UTF-8"); // "YOUR DEVORG PASSWORD AND SECURITY TOKEN";
		String consumerKey2 = URLEncoder.encode("3MVG98_Psg5cppyZ5EajwL0Jia2Z5wU9L2WfsR7e.4_7wH0MSxdt7Pk5rFZPcO.cXil2ZKISM3Ud..YUULMVE", "UTF-8"); // "YOUR OAUTH CONSUMER KEY"; redirect / callback url = https://trace-client-three.herokuapp.com/_auth
		String consumerKey3 = URLEncoder.encode("3MVG98_Psg5cppyZ5EajwL0Jia0Pe85ozHEWKITt6ejZ4Wl0DayXywtu4aveirmZA_27LyUbhLmf1dpViH_RG", "UTF-8");
		String consumerKeyLocal = URLEncoder.encode("3MVG9HxRZv05HarSQo4dTHJAgTpkNwnCL5keoZE0m6iiEcdQzhXLUxMKhzgR.oH8bXElcQdce0RNvsmb4GtA2", "UTF-8");
		String consumerKey = URLEncoder.encode("3MVG9HxRZv05HarSQo4dTHJAgTgbbDnRUEhfk_hQ9yFgu7qg9e7kneM8NUs0QMjmp3xjjqMFc4UdGKtRlYkmR", "UTF-8");
		String consumerSecret2 = URLEncoder.encode("4692002168018118552", "UTF-8"); // "YOUR OAUTH CONSUMER SECRET";
		String consumerSecret3 = URLEncoder.encode("6935358010131804696", "UTF-8");
		String consumerSecretLocal = URLEncoder.encode("2635175488927356252", "UTF-8");
		String consumerSecret = URLEncoder.encode("844669557905966835", "UTF-8");
		
		String urlParameters =  "grant_type=password" +
								"&client_id=" + consumerKey +
								"&client_secret=" + consumerSecret +
								"&username=" + acctName +
								"&password=" + acctPw;
		 
		String url = "https://login.salesforce.com/services/oauth2/token";		
		
		HttpURLConnection con = null;
		OutputStream dos = null;
		
		String accessToken = "";
		String instanceUrl = "";
		String id = "";
		String tokenType = "";
		String issuedAt = "";
		String signature = "";			
		
				
		
		try{
			
			URL obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
			//con.setRequestProperty("User-Agent", "Mozilla/5.0");
			//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// Send post request
			con.setDoInput(true);
			con.setDoOutput(true);
			//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			//wr.writeBytes(urlParameters);
			//wr.flush();
			//wr.close();
			dos = con.getOutputStream();
			dos.write(urlParameters.getBytes("UTF-8"));
			dos.flush();		

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());	

			JsonReader reader = Json.createReader(new StringReader(response.toString()));         
			JsonObject personObject = reader.readObject();          

			accessToken = personObject.getString("access_token");
			instanceUrl = personObject.getString("instance_url");
			id = personObject.getString("id");
			tokenType = personObject.getString("token_type");
			issuedAt = personObject.getString("issued_at");
			signature = personObject.getString("signature");	 

			reader.close();    		
			
		} catch (Exception e) {
			String msg = "Failed to get access token when executing...";
			throw e;
			//System.out.println(e.toString());
		} finally {
            if (con != null) {
                con.disconnect();
            }

            if (dos != null) {
                // close the stream
                try {
					dos.close();
				} catch (IOException e) {
					// NOTHING TO DO
				}
            }
        }
		
		sengGet(accessToken, instanceUrl, id, tokenType, issuedAt, signature);
		
		/*
		//URL url = new URL("http://localhost:8080/resttest/services/Order/3");

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			//LOG.log(Level.INFO, "KEY: " + key + " VALUE: " + value);
		}
		*/
		//LOG.log(Level.INFO, "you called three home");
		return "Hello World From Devoxx3Application";
	}

	public void sengGet(String accessToken,	String instanceUrl,	String id, String tokenType, String issuedAt, String signature) throws Exception {

		String url = instanceUrl + "/services/apexrest/v1/consume/"; // "/services/apexrest/v1/consume/"; "/services/data/v37.0/sobjects/"
		
		HttpURLConnection con = null;
		OutputStream dos = null;
		
		try{
			
			URL obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("GET");

			con.setRequestProperty("Authorization", tokenType + " " + accessToken);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("X-MY-Header", "blah-blah-blah");
			// Send post request
			//con.setDoInput(true);
			//con.setDoOutput(true);
			//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			//wr.writeBytes(urlParameters);
			//wr.flush();
			//wr.close();
			//dos = con.getOutputStream();
			//dos.write(urlParameters.getBytes("UTF-8"));
			//dos.flush();		

			int responseCode = con.getResponseCode();
			String msg = con.getResponseMessage();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			System.out.println("Response Message : " + msg);				
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
			
			System.out.println("USING ZIPKIN HEADERS: ");
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", tokenType + " " + accessToken);
			headers.set("Content-Type", "application/json");

			HttpEntity entity = new HttpEntity(headers);						
			HttpEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			//restTemplate.getForObject("https://trace-client-three.herokuapp.com", String.class);
			
			System.out.println(resp.getBody());
		
		} catch (Exception e) {
			String msg = "Failed to get access token when executing...";
			//throw e;
			System.out.println("ERROR: " + e.toString());
		} finally {
			if (con != null) {
				con.disconnect();
			}

			if (dos != null) {
				// close the stream
				try {
					dos.close();
				} catch (IOException e) {
					// NOTHING TO DO
				}
			}
		}
	}
	
	@RequestMapping("/ping")
	public String ping() {

		return "Hello World From Devoxx3Application";
	}
	
/*
	@RequestMapping("/znext")
	public String zNext() {
		
		//return "Hello World From Devoxx3Application";
		//return restTemplate.getForObject("https://trace-client-three.herokuapp.com/znext", String.class);
	}
*/	
}
