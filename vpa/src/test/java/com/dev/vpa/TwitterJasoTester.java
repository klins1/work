package com.dev.vpa;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import scala.collection.Seq;

import com.twitter.penguin.korean.TwitterKoreanProcessor;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.phrase_extractor.KoreanPhraseExtractor;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;


public class TwitterJasoTester {

	public static void main(String[] args) throws Exception {
		System.out.println("여기는 시작");
		new Parser().parse();
		System.out.println("여기는 어디일까?");
	}
	


}
