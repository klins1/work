package com.dev.vpa;


import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;




/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired  
	RestApiTemplate restApiTemplate;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		System.out.println("home   1   !!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(Thread.currentThread().getName());
		
		
		Future<String> future = restApiTemplate.method(new Handler<String>() {
			@Override
			public void handle(String e) {
				System.out.println("@@@@@@@@@@@ callback : " + e);
			}
		});

		// String result = future.get();
		
		System.out.println("home   2  !!!!!!!!!!!!!!!!!!!!!!");
		
		if (future.isDone()) {
			System.out.println("@@@@@@@@@@@@@@@@@@" + future.get());
		}
		
		return "home";
	}

	@RequestMapping(value="/api/default", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> defaultRest() {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("success", "ok");
		return body;
	}

	/**
	 * HTML GET 요청
	 * <p>단순한 HTML 응답을 받는 예이다.</p>
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/api/rest/html", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> callRest() {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("success", "ok");

		HttpHeaders header = new HttpHeaders();
		header.add("Accept", MediaType.TEXT_HTML_VALUE);
		ResponseEntity<String> response = new RestTemplate().exchange("http://www.naver.com/", HttpMethod.GET, new HttpEntity<Object>(header), String.class);

		return response;
	}	

	/**
	 * JSON REST
	 * 에코 응답을 제공하는 JSON Test API를 사용하여 예를 작성해보겠다. JSON GET 요청에 앞서 응답을 받을 POJO 클래스를 구현한다. 
	 * 아래 HeadersResponse 클래스는 JSON 응답을 담는 역할을 한다.
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/api/rest/json", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> callRestJSON() throws Exception {
		// HttpHeaders header = new HttpHeaders(); header.add("Accept", MediaType.APPLICATION_JSON_VALUE); 
		// ResponseEntity<Object> response = new RestTemplate().exchange("http://headers.jsontest.com/", HttpMethod.GET, new HttpEntity<Object>(header), Object.class);
		// logger.info(response.toString());

		// new RestTemplate().exchange("http://headers.jsontest.com/", HttpMethod.GET, new HttpEntity<Object>(header), HttpResponseData.class);

		// RestTemplate logging 실정
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);


		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 1. 파라미터 Template
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// 파라미터 User -----> 

		String restUrl = "https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/6b06ca37-0717-4b1f-a28c-be7859592730?subscription-key=6d5f7a401a584f36a282e9c384b089fd&q=hello&timezoneOffset=0.0&verbose=true";

		// Object responseString = restTemplate.getForObject("https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/6b06ca37-0717-4b1f-a28c-be7859592730?subscription-key=6d5f7a401a584f36a282e9c384b089fd&q=hello&timezoneOffset=0.0&verbose=true", Object.class);
		// System.out.println(responseString.getClass());

		Map<String, Object> body = new HashMap<String, Object>();
//		body.put("success", "ok");
//		body.put("data", null);
//
//		HashMap<String, Object> scopes = new HashMap<String, Object>();
//		scopes.put("name", "Mustache");
//
//		HashMap<String, Object> child = new HashMap<String, Object>();
//		child.put("description", "hello");
//
//		scopes.put("feature", child);




		// method type

		// header
		String headerTemplate = "{ \"Authorization\" : \"Bearer\",  \"Accept\" : \"application/json\" }";
		
		// application/x-www-form-urlencoded
		// 
		HttpHeaders header = new HttpHeaders(); 
		Map<String, Object> headerMap = new JSONObject(headerTemplate).toMap();

		for(String key : headerMap.keySet()) {
			header.add(key, headerMap.get(key).toString());
		}
		
		header.add("Content-Type", "application/x-www-form-urlencoded");

		logger.info("# HEADER #");
		logger.info(header.toString());

		// parameter


		// body

		StringBuffer bodyParamtemplate = new StringBuffer();
		bodyParamtemplate.append("{");
		bodyParamtemplate.append("  \"query\" : \"{{query}}\", ");
		bodyParamtemplate.append("  \"message\" : \"{{message}}\" "); 
		bodyParamtemplate.append("}");		
		
//		System.out.println(bodyParamtemplate);
		
		body = new JSONObject(bodyParamtemplate.toString()).toMap();
		
		
		MultiValueMap<String, Object> body2 = new LinkedMultiValueMap<String, Object>();

		body2.setAll(body);
//		System.out.println(body);
		
		// HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(body, header);
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(body2, header);	    

		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		// String _response = restTemplate.postForObject(restUrl, body, String.class, new HttpEntity<Object>(header));
		String _response = restTemplate.postForObject(restUrl, request, String.class);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 2. 결과 Template
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		StringBuffer template = new StringBuffer();
		template.append("{");
		template.append("  \"query\" : \"{{query}}\", ");
		template.append("  \"message\" : \"요청하신 내용의 가장 높은 인텐트는 {{topScoringIntent.intent}} 입니다.\", ");
		template.append("  \"intent\" : \"{{topScoringIntent.intent}}\", ");
		template.append("  \"score\" : \"{{topScoringIntent.score}}\" ");	    
		template.append("}");

		Writer writer = new StringWriter();
		MustacheFactory mf = new DefaultMustacheFactory();
		// Mustache mustache = mf.compile(new StringReader("{{name}}, {{feature.description}}!"), "template.mustache");

		Mustache mustache = mf.compile(new StringReader(template.toString()), "template.mustache");

		mustache.execute(writer, _response).flush();
		String jsonString = writer.toString();

		logger.info("#############################################");
		logger.info("String");
		logger.info("#############################################");
		logger.info(jsonString);
		logger.info("#############################################");

		JSONObject json = new JSONObject(jsonString);

		logger.info("#############################################");
		logger.info("json");
		logger.info("#############################################");
		logger.info(json.toString());
		logger.info("#############################################");


		body.put("json", json.toMap());

		return body;
	}	


	/**
	 * SQL 요청
	 */
	@RequestMapping(value="/api/sql", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> callSql() throws Exception {
		
		return null;
	}

	/**
	 * 내부 자원 호출
	 */
	@RequestMapping(value="/api/component", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> callComponent() throws Exception {
		
		return null;
	}

	@RequestMapping(value="/api/sqlmap", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> callSqlMapper() throws Exception {
		
		return null;
	}	
}














