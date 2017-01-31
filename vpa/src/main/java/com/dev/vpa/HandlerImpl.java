package com.dev.vpa;

public class HandlerImpl<E> implements Handler<E>{
	private E value;
	
	@Override
	public void handle(E e) {
		this.value = e;
		System.out.println("@@@@@@@@@@@ callback : " + this.value);
	}
}