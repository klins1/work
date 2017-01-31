package com.dev.vpa;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface RestApiTemplate {
	public Future<String>  method(Handler<String> handler) throws Exception;
}
