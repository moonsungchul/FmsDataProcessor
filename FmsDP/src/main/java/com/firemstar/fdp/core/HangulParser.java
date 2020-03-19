package com.firemstar.fdp.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

public class HangulParser {

	private Logger logger = LoggerFactory.getLogger(HangulParser.class);
	
	private Komoran komoran;
	
	public HangulParser() {
		komoran = new Komoran(DEFAULT_MODEL.STABLE);
	}
	
	public ArrayList<String> parsing(String ss ) {
		ArrayList<String> ret = new ArrayList<String>();
		KomoranResult res = komoran.analyze(ss);
		
		logger.info(res.getPlainText());
		List<Token> tokenList = res.getTokenList();
		for(Token token : tokenList) {
			System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
		}
		return ret;
	}

}
