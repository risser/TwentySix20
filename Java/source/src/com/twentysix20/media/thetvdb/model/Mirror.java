package com.twentysix20.media.thetvdb.model;

public class Mirror {
	private String id;
	private String mirrorpath;
	private int typemask;

	private Mirror(String id, String mirrorpath, int typemask) {
		this.id = id;
		this.mirrorpath = mirrorpath;
		this.typemask = typemask;
	}

	public String getId() {
		return id;
	}
	public String getMirrorpath() {
		return mirrorpath;
	}
	public int getTypemask() {
		return typemask;
	}

	public boolean isXMLMirror() {
		return ((typemask & 1) > 0);
	}

	public boolean isBannerMirror() {
		return ((typemask & 1) > 2);
	}

	public boolean isZipMirror() {
		return ((typemask & 1) > 4);
	}

	@Override
	public String toString() {
		return String.format("Mirror [%s: %s, typemask=%s]", id, mirrorpath, typemask);
	}
}
