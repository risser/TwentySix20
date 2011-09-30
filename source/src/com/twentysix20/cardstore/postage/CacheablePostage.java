package com.twentysix20.cardstore.postage;


public interface CacheablePostage extends Postage {
	public int calculate(int count);
}
