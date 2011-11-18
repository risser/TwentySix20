package com.twentysix20.dlc;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestArtistFactory {

	@Test public void newArtistCreatesArtist() {
		String name = "Poison";
		
		Artist artist = ArtistFactory.getArtist(name);
		assertEquals(name, artist.getName());
	}

	@Test public void sameArtistReturnsSameArtist() {
		String name = "Poison";
		
		Artist artist1 = ArtistFactory.getArtist(name);
		Artist artist2 = ArtistFactory.getArtist(name);

		assertSame(artist1, artist2);
	}
}
