package com.twentysix20.media.tvboxer;

import java.io.File;

import org.junit.Test;

import com.twentysix20.media.tvboxer.TVFileFilter;
import com.twentysix20.testutil.TestCase2620;

public class TestTVFileFilter extends TestCase2620 {

	@Test public void testAcceptGood() {
		TVFileFilter filter = new TVFileFilter();
		assertTrue(filter.accept(new File("Bobby 01.01 Foo.mp4")));
		assertTrue(filter.accept(new File("Bobby 01.02 Foo.mp4")));
		assertTrue(filter.accept(new File("Lots of words up Here 01.02 And many Words back here.mp4")));
		assertTrue(filter.accept(new File("Bobby 00.00 Foo.mp4")));
		assertTrue(filter.accept(new File("Bobby 99.99 Foo.mp4")));
		assertTrue(filter.accept(new File("Mr. Bobby 02.12 Meets Mr. Foo.mp4")));
		assertTrue(filter.accept(new File("Mr.mp4 02.12 Meets Mr.mp4 today.mp4")));
	}

	@Test public void testAcceptBad() {
		TVFileFilter filter = new TVFileFilter();
		assertFalse(filter.accept(new File("Bobby 01-01 Foo.mp4")));
		assertFalse(filter.accept(new File("Bobby 01 Foo.mp4")));
		assertFalse(filter.accept(new File("01.02 And many Words back here.mp4")));
		assertFalse(filter.accept(new File("Bobby 02.02 Foo.mp4.m4v")));
		assertFalse(filter.accept(new File("Bobby 02.02.mp4")));
		assertFalse(filter.accept(new File("Mr Bobby 02 12 Meets Mr Foo.mp4")));
	}

}
