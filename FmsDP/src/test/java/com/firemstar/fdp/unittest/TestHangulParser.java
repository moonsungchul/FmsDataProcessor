package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.firemstar.fdp.core.HangulParser;
import com.firemstar.fdp.core.KomoranResultAr;

class TestHangulParser {
	
	private Logger logger = LoggerFactory.getLogger(TestHangulParser.class);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		HangulParser parser = new HangulParser();
		String in = "신종 코로나바이러스 감염증(코로나19)의 발원지인 중국이 최근 신규 확진자 증가세가 꺾이고 정부 차원의 대규모 경기 부양 기대가 작용하면서 증시가 빠르게 회복되고 있다.전문가들은 향후 발표될 경제 지표와 기업 실적 등에 따라 변동성이 다소 커질 수 있지만 향후 부동산 규제 완화 등 강력한 부양책이 나올 가능성이 높아 이에 따른 수혜 업종과 종목에 관심을 가질 만하다는 의견을 내놓고 있다.5일 금융정보업체 에프앤가이드 에 따르면 172개 중국 펀드의 최근 한 달 수익률은 평균 8.15%(3월 4일 기준)로 집계됐다. 에프앤가이드 가 분류하는 20개 권역별 펀드 가운데 가장 높다. 올해 초까지 글로벌 증시 랠리를 이끌어온 미국(한 달 수익률 -2.44%)이나 지난해 20% 넘는 수익률을 올린 브라질(-9.71%), ";
		KomoranResultAr ret = parser.parsing(in);
		logger.info(ret.getnArr().toString());
		logger.info(ret.getvArr().toString());
		logger.info(ret.getVaArr().toString());
		
	}

}
