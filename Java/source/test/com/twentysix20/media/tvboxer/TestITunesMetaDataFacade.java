package com.twentysix20.media.tvboxer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.coremedia.iso.IsoFileConvenienceHelper;
import com.coremedia.iso.boxes.apple.AbstractAppleMetaDataBox;
import com.coremedia.iso.boxes.apple.AppleArtistBox;
import com.coremedia.iso.boxes.apple.AppleItemListBox;
import com.coremedia.iso.boxes.apple.AppleMediaTypeBox;
import com.coremedia.iso.boxes.apple.AppleRecordingYearBox;
import com.coremedia.iso.boxes.apple.AppleTrackTitleBox;
import com.coremedia.iso.boxes.apple.AppleTvSeasonBox;
import com.twentysix20.media.tvboxer.ITunesMediaType;
import com.twentysix20.media.tvboxer.ITunesMetaDataFacade;
import com.twentysix20.media.tvboxer.boxes.AppleMediaRatingBox;
import com.twentysix20.media.webreaders.movie.Rating;


public class TestITunesMetaDataFacade {

	@Test public void readEpisodeTitle() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleTrackTitleBox.class, "Episode Title");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertEquals("Episode Title", facade.getEpisodeTitle());
	}

	@Test public void readNonExistentEpisodeTitle() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertNull(facade.getEpisodeTitle());
	}

	@Test public void updateEpisodeTitle() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleTrackTitleBox.class, "Episode Title-update");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setEpisodeTitle("Episode Title-new");
		assertEquals("Episode Title-new", getChildBox(container, AppleTrackTitleBox.TYPE).getValue());
	}

	@Test public void createEpisodeTitle() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setEpisodeTitle("Episode Title-brand new");
		assertEquals("Episode Title-brand new", getChildBox(container, AppleTrackTitleBox.TYPE).getValue());
	}

	@Test public void readActors() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleArtistBox.class, "Actor One, Actor Two");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		List<String> expectedActors = Arrays.asList("Actor One","Actor Two");
		assertEquals(expectedActors, facade.getActors());
	}

	@Test public void readActorWithNoSpaceAfterComma() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleArtistBox.class, "Actor Three,Actor Four");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		List<String> expectedActors = Arrays.asList("Actor Three","Actor Four");
		assertEquals(expectedActors, facade.getActors());
	}

	@Test public void readSingleActor() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleArtistBox.class, "Actor One");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		List<String> expectedActors = Arrays.asList("Actor One");
		assertEquals(expectedActors, facade.getActors());
	}

	@Test public void readNonExistentActors() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertTrue(facade.getActors().isEmpty());
	}

	@Test public void updateActors() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleTrackTitleBox.class, "Actress One, Actress Two");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setActors(Arrays.asList("Johnny Boy","Gillian Girl","Frank the Dog"));
		assertEquals("Johnny Boy, Gillian Girl, Frank the Dog", getChildBox(container, AppleArtistBox.TYPE).getValue());
	}

	@Test public void updateWithListLongerThan255CharsWithSpacesButLessWithout() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setActors(Arrays.asList("Anne Dudek", "Corbin Bernsen", "Dulé Hill", "James Roday", "Timothy Omundson", "Kirsten Nelson", "Sean Devine", "Jocelyne Loewen", "Ingrid Tesch", "Robert Parent", "Jason Bryden", "P. Lynn Johnson", "Pascale Hutton", "Don S. Davis", "Josh Hayden", "Michael Roberds", "Dagmar Midcap", "Catherine Thomas"));
		assertEquals("Anne Dudek,Corbin Bernsen,Dulé Hill,James Roday,Timothy Omundson,Kirsten Nelson,Sean Devine,Jocelyne Loewen,Ingrid Tesch,Robert Parent,Jason Bryden,P. Lynn Johnson,Pascale Hutton,Don S. Davis,Josh Hayden,Michael Roberds,Dagmar Midcap,Catherine Thomas", getChildBox(container, AppleArtistBox.TYPE).getValue());
	}

	@Test public void updateWithListLongerThan255CharsEvenWithoutSpaces() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setActors(Arrays.asList("Anne Dudek", "Corbin Bernsen", "Dulé Hill", "James Roday", "Timothy Omundson", "Kirsten Nelson", "Sean Devine", "Jocelyne Loewen", "Ingrid Tesch", "Robert Parent", "Jason Bryden", "P. Lynn Johnson", "Pascale Hutton", "Don S. Davis", "Josh Hayden", "Michael Roberds", "Dagmar Midcap", "Catherine Thomasen", "Bill Flurpgartiname","Carl Instocation"));
		assertEquals("Anne Dudek,Corbin Bernsen,Dulé Hill,James Roday,Timothy Omundson,Kirsten Nelson,Sean Devine,Jocelyne Loewen,Ingrid Tesch,Robert Parent,Jason Bryden,P. Lynn Johnson,Pascale Hutton,Don S. Davis,Josh Hayden,Michael Roberds,Dagmar Midcap,Catherine Thomasen",getChildBox(container,AppleArtistBox.TYPE).getValue());
	}

	@Test public void updateSingleActor() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleTrackTitleBox.class, "Actress One, Actress Two");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setActors(Arrays.asList("Johnny Boy"));
		assertEquals("Johnny Boy", getChildBox(container, AppleArtistBox.TYPE).getValue());
	}

	@Test public void createActors() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setActors(Arrays.asList("Johnny Boy","Gillian Girl","Frank the Dog"));
		assertEquals("Johnny Boy, Gillian Girl, Frank the Dog", getChildBox(container, AppleArtistBox.TYPE).getValue());
	}

	@Test public void readSeason() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleTvSeasonBox.class, "2");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertEquals(2, facade.getSeasonNumber());
	}

	@Test public void readNonExistentSeason() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertEquals(0, facade.getSeasonNumber());
	}

	@Test public void updateSeason() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleTvSeasonBox.class, "3");
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setSeasonNumber(1);
		assertEquals("1", getChildBox(container, AppleTvSeasonBox.TYPE).getValue());
	}

	@Test public void createSeason() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setSeasonNumber(5);
		assertEquals("5", getChildBox(container, AppleTvSeasonBox.TYPE).getValue());
	}

	@Test public void validDateAsFourDigitYear() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setReleaseDateString("1977");
		assertEquals("1977", getChildBox(container, AppleRecordingYearBox.TYPE).getValue());
	}

	@Test public void validDateAsYYYYMMDD() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setReleaseDateString("1977-03-04");
		assertEquals("1977-03-04", getChildBox(container, AppleRecordingYearBox.TYPE).getValue());
	}

	@Test(expected=IllegalArgumentException.class) 
	public void invalidDateAsThreeDigits() {
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(new AppleItemListBox());
		facade.setReleaseDateString("197");
	}

	@Test(expected=IllegalArgumentException.class) 
	public void invalidDateAsYearMonth() {
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(new AppleItemListBox());
		facade.setReleaseDateString("1977-05");
	}

	@Test(expected=IllegalArgumentException.class) 
	public void invalidDateAsNonZeroPadded() {
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(new AppleItemListBox());
		facade.setReleaseDateString("1977-5-12");
	}

	@Test public void readMediaType() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleMediaTypeBox.class, ITunesMediaType.TV.getValueAsString());
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertEquals(ITunesMediaType.TV, facade.getMediaType());
	}

	@Test public void readNonExistentMediaType() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertNull(facade.getMediaType());
	}

	@Test public void updateMediaType() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleMediaTypeBox.class, ITunesMediaType.Movie.getValueAsString());
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setMediaType(ITunesMediaType.TV);
		assertEquals(ITunesMediaType.TV.getValueAsString(), getChildBox(container, AppleMediaTypeBox.TYPE).getValue());
	}

	@Test public void createMediaType() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setMediaType(ITunesMediaType.Movie);
		assertEquals(ITunesMediaType.Movie.getValueAsString(), getChildBox(container, AppleMediaTypeBox.TYPE).getValue());
	}

	@Test public void readRating() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleMediaTypeBox.class, ITunesMediaType.TV.getValueAsString());
		AppleMediaRatingBox box = new AppleMediaRatingBox(Rating.NC_17);
		container.addBox(box.getUnderlyingBox());
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertEquals(Rating.NC_17, facade.getRating());
	}

	@Test public void readNonExistentRating() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		assertNull(facade.getMediaType());
	}

	@Test public void updateRating() {
		AppleItemListBox container = createItemBoxWithMetaBox(AppleMediaTypeBox.class, ITunesMediaType.Movie.getValueAsString());
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setMediaType(ITunesMediaType.TV);
		assertEquals(ITunesMediaType.TV.getValueAsString(), getChildBox(container, AppleMediaTypeBox.TYPE).getValue());
	}

	@Test public void createRating() {
		AppleItemListBox container = new AppleItemListBox();
		ITunesMetaDataFacade facade = new ITunesMetaDataFacade(container);
		facade.setMediaType(ITunesMediaType.Movie);
		assertEquals(ITunesMediaType.Movie.getValueAsString(), getChildBox(container, AppleMediaTypeBox.TYPE).getValue());
	}

/////////////////////////

	private AppleItemListBox createItemBoxWithMetaBox(Class<? extends AbstractAppleMetaDataBox> boxClass, String value) {
		AppleItemListBox container = new AppleItemListBox();
	    try {
	    	AbstractAppleMetaDataBox box = boxClass.newInstance();
			box.setValue(value);
			container.addBox(box);
		} catch (InstantiationException e) {
			throw new RuntimeException("Instantiation Exception.", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Illegal Access Exception.", e);
		}
		return container;
	}

	private AbstractAppleMetaDataBox getChildBox(AppleItemListBox container, String key) {
		return (AbstractAppleMetaDataBox)IsoFileConvenienceHelper.get(container, key);		
	}
}
