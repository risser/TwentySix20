package com.twentysix20.media.tvboxer.boxes;

import java.lang.reflect.Field;

import com.coremedia.iso.boxes.UnknownBox;

public class MP4UnknownBox {

	private UnknownBox box;

	public MP4UnknownBox(UnknownBox x) {
		this.box = x;
	}

	public String getType() {
		return new String(box.getType());
	}

	public String getContent() {
		try {
			Field field = box.getClass().getSuperclass().getDeclaredField("content");
	        field.setAccessible(true);
	        byte[] content = (byte[])field.get(box);
			return new String(content);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
