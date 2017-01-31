package com.dev.vpa;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("restApiTemplate")
public class RestApiTemplateImpl implements RestApiTemplate {

	@Async
	@Override
	public Future<String> method(Handler<String> handler) throws Exception {
		Thread.sleep(1000);
		System.out.println("method !!!!!!!!!!!!!");
		System.out.println(Thread.currentThread().getName());
		
		handler.handle("return");
		  
		return new AsyncResult<String>("hello world !!!!");	
	}
}
