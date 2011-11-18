package com.twentysix20.dlc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.twentysix20.dlc.model.Artist;
import com.twentysix20.dlc.model.Disc;
import com.twentysix20.dlc.model.Pack;
import com.twentysix20.dlc.model.Song;

public class DLCCollection {

	private Set<Artist> artists = new HashSet<Artist>();
	private Set<Song> songs = new HashSet<Song>();
	private Set<Pack> packs = new HashSet<Pack>();
	private Set<Disc> discs = new HashSet<Disc>();

	public Set<Artist> getArtists() {
		return artists;
	}

	private void addArtist(Artist artist) {
		artists.add(artist);		
	}

	public Set<Song> getSongs() {
		return songs ;
	}

	public void addSong(Song song) {
		songs.add(song);
		addArtist(song.getArtist());
		addPack(song.getPack());
		addDisc(song.getDisc());
	}

	public Set<Pack> getPacks() {
		return packs;
	}

	private void addPack(Pack pack) {
		packs.add(pack);
	}

	public Set<Disc> getDiscs() {
		return discs;
	}

	private void addDisc(Disc disc) {
		discs.add(disc);
	}

	public List<Artist> sortedArtistList() {
		List<Artist> artists = new ArrayList<Artist>(getArtists());
		Collections.sort(artists);
		return artists;
	}
}
