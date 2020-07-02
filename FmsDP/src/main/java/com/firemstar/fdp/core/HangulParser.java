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

	/**
	 * 명사, 동사, 형용사를 추출한다. 
	 * @param ss
	 * @return
	 */
	public KomoranResultAr parsing(String ss ) {
		logger.info("ssssssssss :||" +  ss + "|||");
		KomoranResultAr ret = new KomoranResultAr();
		KomoranResult res = komoran.analyze(ss);
		
		logger.info(res.getPlainText());
		List<Token> tokenList = res.getTokenList();
		for(Token token : tokenList) {
			if(token.getPos().equals("NNG") || 
					token.getPos().equals("NNP") || 
					token.getPos().equals("NNB") ) {
				if(ret.getnArr().contains(token.getMorph()) == false) {
					ret.getnArr().add(token.getMorph());
				}
			} else if (token.getPos().equals("VV")) {
				if(ret.getvArr().contains(token.getMorph()) == false) {
					ret.getvArr().add(token.getMorph());
				}
			} else if (token.getPos().equals("VA")) {
				if(ret.getVaArr().contains(token.getMorph()) == false) {
					ret.getVaArr().add(token.getMorph());
				}
			}
		}
	return ret;
	}

}
