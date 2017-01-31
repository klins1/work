package com.dev.vpa;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.phrase_extractor.KoreanPhraseExtractor;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import scala.collection.Seq;
@EnableAsync
public class Parser {
	@Asynchronous
	public void parse() throws Exception {
		System.out.println("parsing ����");
		

		// TODO Auto-generated method stub
	    String text = "����� ����";

	    // Normalize
	    CharSequence normalized = TwitterKoreanProcessorJava.normalize(text);
	    System.out.println(normalized);
	    // �ѱ�� ó���ϴ� �����Դϴ٤��� #�ѱ���


	    // Tokenize
	    Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
	    System.out.println(TwitterKoreanProcessorJava.tokensToJavaStringList(tokens));
	    // [�ѱ���, ��, ó��, �ϴ�, ����, �Դ�, ��, ����, #�ѱ���]
	    System.out.println(TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(tokens));
	    // [�ѱ���(Noun: 0, 3), ��(Josa: 3, 1),  (Space: 4, 1), ó��(Noun: 5, 2), �ϴ�(Verb: 7, 2),  (Space: 9, 1), ����(Noun: 10, 2), �Դ�(Adjective: 12, 2), ��(Eomi: 14, 1), ����(KoreanParticle: 15, 2),  (Space: 17, 1), #�ѱ���(Hashtag: 18, 4)]


	    // Stemming
	    Seq<KoreanTokenizer.KoreanToken> stemmed = TwitterKoreanProcessorJava.stem(tokens);
	    System.out.println(TwitterKoreanProcessorJava.tokensToJavaStringList(stemmed));
	    // [�ѱ���, ��, ó��, �ϴ�, ����, �̴�, ����, #�ѱ���]
	    System.out.println(TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed));
	    // [�ѱ���(Noun: 0, 3), ��(Josa: 3, 1),  (Space: 4, 1), ó��(Noun: 5, 2), �ϴ�(Verb: 7, 2),  (Space: 9, 1), ����(Noun: 10, 2), �̴�(Adjective: 12, 3), ����(KoreanParticle: 15, 2),  (Space: 17, 1), #�ѱ���(Hashtag: 18, 4)]


	    // Phrase extraction
	    List<KoreanPhraseExtractor.KoreanPhrase> phrases = TwitterKoreanProcessorJava.extractPhrases(tokens, true, true);
	    System.out.println(phrases);
	    // [�ѱ���(Noun: 0, 3), ó��(Noun: 5, 2), ó���ϴ� ����(Noun: 5, 7), ����(Noun: 10, 2), #�ѱ���(Hashtag: 18, 4)]
	}
}
