package com.coupang.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by daniel.lee on 2016-10-20.
 */
public class StreamExample {

	private List<String> inputStringList = Arrays.asList("나이키,기저귀,호텔,아디다스,리복,아식스,아식스,나이키,펜션,전화기,물티슈,생수,나이키,리복,아이폰".split(","));

	// NOTE: 쿠팡 쿠키 면접 출제 문제

	public void filterExample() {
		// NOTE: filter
		// "아" 로 시작하는 단어 갯수 추출 - 아디다스,아식스,아식스,아이폰
		// 단축키로 람다식 추출 :  w -> w.startsWith("아")
		System.out.println(inputStringList.stream().filter(w -> w.startsWith("아")).count());

		// NOTE: 변수 추출 확인
		inputStringList.stream().filter(startWithString("아")).forEach(System.out::println);
		inputStringList.stream().filter(startWithString("아")).forEach(w -> System.out.println(w));

		// NOTE: count
		// "나이키" 단어 찾기 - 나이키,나이키,나이키
		System.out.println(inputStringList.stream().filter(w -> w.equals("나이키")).count());
	}

	public void distinctExample() {
		// NOTE: distinct
		// 중복 제거
		System.out.println("distinct words is ");
		inputStringList.stream().distinct().forEach(System.out::println);
	}

	public void mapExample() {
		// NOTE: map, forEach
		// 길이로 변환
		System.out.println("=== transfer length number ===");
		List<Integer> lengthList = inputStringList.stream().map(w -> w.length()).collect(toList());
		lengthList.forEach(System.out::println);

		// 문장, 길이 map 으로 변환 -  Duplicate key 3 으로 에러 발생
//		System.out.println("=== transfer length number map ===");
//		inputStringList.stream().collect(Collectors.toMap(Function.identity(), String::length));

		Map<String, Integer> wordsAndLengthMap = inputStringList.stream().distinct().collect(Collectors.toMap(Function.identity(), String::length));
		inputStringList.stream().distinct().forEach(w -> System.out.println(w + " : " + w.length()));
	}

	public void anyMatchExample() {
		// allMatch, anyMatch
		System.out.println("=== allMatch, anyMatch ===");
		System.out.println("all words length > 3 is " + inputStringList.stream().allMatch(w -> w.length() > 3));
		System.out.println("any words length > 3 is " + inputStringList.stream().anyMatch(w -> w.length() > 3));
		System.out.println(inputStringList.stream().filter(w -> w.length() > 3).findAny().orElse("이게 보이면 길이 3 이상의 글자가 없다는 것"));
	}

	public void intStreamExample() {
		// 숫자 붙이기
		System.out.println("=== add number ===");
		// NOTE: IntStream
		// stream 내부에서는 final 변수만 사용 가능함. 따라서 idx++ 과 같은 변수 사용이 불가.
		// 하단의 예시코드는 에러가 발생한다.
		IntStream.range(0, inputStringList.size()).forEach(idx -> System.out.println(inputStringList.get(idx) + "[" +  idx  + "]"));

		// NOTE: 왜 에러가 발생할까?
		//		final int idx = 0;
		//		inputStringList.stream().forEach(w -> {
		//			System.out.println(w + "[" + idx + "]");
		//			idx++;
		//		});
	}


	private static Predicate<String> startWithString(final String startWord) {
		return w -> w.startsWith(startWord);
	}

	public void optionalExample() {
		String inputString = "나이키,기저귀,호텔,아디다스,리복,아식스,아식스,나이키,펜션,전화기,물티슈,생수,나이키,리복,아이폰";
		List<String> inputStringList = Arrays.asList(inputString.split(","));

		// optional
		Optional<String> optionalString = inputStringList.stream().filter(w -> w.length() > 20).findAny();
		if (optionalString.isPresent()) {
			System.out.println("길이 20자 이상의 단어가 - 있다.");
		} else {
			System.out.println("길이 20자 이상의 단어가 - 없다.");
		}

		System.out.println(optionalString.orElse("없어요"));
		System.out.println(optionalString.orElseGet(() -> "없어요") );  // 더 복잡한 객체 리턴 시
		optionalString.orElseThrow(IllegalArgumentException::new);

		Optional<String> testOptional1 = Optional.empty();
		Optional<String> testOptional2 = Optional.of("test");

		System.out.println("testOptional1.isPresent() = " + testOptional1.isPresent());
		System.out.println("testOptional2.isPresent() = " + testOptional2.orElse("없으면 찍히지만 안 찍히겠지."));

	}

	public void cretaStaticsRPS() {
		String resultText = "9.2 - 장풍,9.5 - 장풍,9.6 - 그렉,9.7 - 레인,9.8 - 레인,9.9 - 아폴로,9.12 - 호야,9.19 - 로빈,9.20 - 로빈,"
			+ "9.21 - 재스퍼,9.22 - 앤써니,9.22 - 아농,9.23 - 그렉,9.26 - 레인,9.27 - 가빈,9.28 - 장풍,9.28 - 재스퍼,9.30 - 로빈,10.5 - 가빈,"
			+ "10.07 - 로빈,10.07 - 레인,10.10 - 가빈,10.11 - 브로,10.12 - 그렉,10.13 - 가빈,10.14 - 레인,10.17 - 로빈,10.19 - 샐리";

		// 9.2 - 장풍
		List<String> resultList = Arrays.asList(resultText.split(","));
		Map<String, Long> looseCountMap = resultList.stream().map(result -> result.split(" - ")[1]).collect( Collectors.groupingBy(Function.identity(), Collectors.counting()) );

		System.out.println("== ASC == ");
		looseCountMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(sortedMap -> System.out.println(sortedMap.getKey() + " | " + sortedMap.getValue()));
		System.out.println("== DESC == ");
		looseCountMap.entrySet().stream().sorted((o1, o2) -> (int)(o2.getValue() - o1.getValue())).forEach(sortedMap -> System.out.println(sortedMap.getKey() + " | " + sortedMap.getValue()));
	}

}
