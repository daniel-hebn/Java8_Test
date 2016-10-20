package com.coupang.test;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by daniel.lee on 2016-10-20.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

	String process(BufferedReader br) throws IOException;
}
