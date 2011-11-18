package com.twentysix20.dlc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class TestDlcCollection {
	@Before public void clearFactories() {
		DiscFactory.clear();
		ArtistFactory.clear();
		PackFactory.clear();
	}

	@Test public void newSongAddsSongToCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());

		assertEquals(1, dlc.getSongs().size());
		Song song = dlc.getSongs().iterator().next();
		assertEquals(1234, song.getId());
		assertEquals("Hum Hum", song.getTitle());
	}

	@Test public void additionalSongCreatesAnotherSongInCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());
		dlc.addSong(ObjectFactoryForTests.anotherSongDifferentEverything());
		dlc.addSong(ObjectFactoryForTests.anotherSongSameEverything());

		assertEquals(3, dlc.getSongs().size());
	}

	@Test public void newArtistAddsArtistToCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());

		assertEquals(1, dlc.getArtists().size());
		assertEquals("Mr. Fake", dlc.getArtists().iterator().next().getName());
	}

	@Test public void newArtistCreatesAnotherArtistInCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());
		dlc.addSong(ObjectFactoryForTests.anotherSongDifferentEverything());

		assertEquals(2, dlc.getArtists().size());
	}

	@Test public void sameArtistDoesntAddNewArtist() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());
		dlc.addSong(ObjectFactoryForTests.anotherSongSameEverything());

		assertEquals(1, dlc.getArtists().size());
	}

	@Test public void newDiscAddsDiscToCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());

		assertEquals(1, dlc.getDiscs().size());
		assertEquals("DLC", dlc.getDiscs().iterator().next().getName());
	}

	@Test public void newDiscCreatesAnotherDiscInCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());
		dlc.addSong(ObjectFactoryForTests.anotherSongDifferentEverything());

		assertEquals(2, dlc.getDiscs().size());
	}

	@Test public void sameDiscDoesntAddNewDisc() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());
		dlc.addSong(ObjectFactoryForTests.anotherSongSameEverything());

		assertEquals(1, dlc.getDiscs().size());
	}

	@Test public void newPackAddsPackToCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());

		assertEquals(1, dlc.getPacks().size());
		assertEquals("Fake Package 01", dlc.getPacks().iterator().next().getName());
	}

	@Test public void newPackCreatesAnotherPackInCollection() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());
		dlc.addSong(ObjectFactoryForTests.anotherSongDifferentEverything());

		assertEquals(2, dlc.getPacks().size());
	}

	@Test public void samePackDoesntAddNewPack() {
		DlcCollection dlc = new DlcCollection();
		dlc.addSong(ObjectFactoryForTests.basicSong());
		dlc.addSong(ObjectFactoryForTests.anotherSongSameEverything());

		assertEquals(1, dlc.getPacks().size());
	}
}
