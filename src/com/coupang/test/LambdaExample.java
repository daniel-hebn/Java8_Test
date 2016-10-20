package com.coupang.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by daniel.lee on 2016-10-10.
 */
public class LambdaExample {

	private static final String FILE_PATH = "C:\\Users\\Coupang\\Desktop\\축구 기사.txt";

	// NOTE: 자바 8 이전
//	public String processFile() throws IOException {
//		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//			return br.readLine();
//		}
//	}

	// NOTE: 2줄을 읽어 달라는 요구사항이 추가된다면?
	public String processFile() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
			return br.readLine() + br.readLine();  // 원하는 로직은 이 부분
		}
	}

	// NOTE: 원하는 로직의 메서드 시그니처를 확인 후 함수형 인터페이스 선언
	// 		 이제 원하는 로직을 사용할 곳에서는 함수형 인터페이스를 파라미터로 가지도록 함.
	//       그럼 함수형 인터페이스 자리에 람다식을 넣을 수 있음.

	public String processFile(BufferedReaderProcessor brp) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
			return brp.process(br);
		}
	}

}
