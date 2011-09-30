package com.twentysix20.kata.guildedrose;


public class InventoryManager {
	private Item[] items;

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			if (items[i].name != "Aged Brie"
					&& items[i].name != "Backstage passes to a Backstreet Boys concert") {
				if (items[i].quality > 0) {
					if (items[i].name != "Sulfuras, Hand of Ragnaros") {
						items[i].quality = items[i].quality - 1;
					}
				}
			} else {
				if (items[i].quality < 50) {
					items[i].quality = items[i].quality + 1;

					if (items[i].name == "Backstage passes to a Backstreet Boys concert") {
						if (items[i].sellIn < 11) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}

						if (items[i].sellIn < 6) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}
					}
				}
			}

			if (items[i].name != "Sulfuras, Hand of Ragnaros") {
				items[i].sellIn = items[i].sellIn - 1;
			}

			if (items[i].sellIn < 0) {
				if (items[i].name != "Aged Brie") {
					if (items[i].name != "Backstage passes to a Backstreet Boys concert") {
						if (items[i].quality > 0) {
							if (items[i].name != "Sulfuras, Hand of Ragnaros") {
								items[i].quality = items[i].quality - 1;
							}
						}
					} else {
						items[i].quality = items[i].quality - items[i].quality;
					}
				} else {
					if (items[i].quality < 50) {
						items[i].quality = items[i].quality + 1;
					}
				}
			}
		}
	}

	static public void main(String[] args) {
		System.out.println("Welcome to the Guilded Rose Inventory Management System!");
		InventoryManager inventory = new InventoryManager();

		inventory.items = new Item[6];

		inventory.items[0] = new Item("+3 Broadsword of Smiting", 10, 20);
		inventory.items[1] = new Item("Aged Brie", 2, 0);
		inventory.items[2] = new Item("Elixir of the Mongoose", 5, 7);
		inventory.items[3] = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
		inventory.items[4] = new Item("Backstage passes to a Backstreet Boys concert", 15, 20);
		inventory.items[5] = new Item("Enchanted Mana Cake", 3, 6);
		inventory.updateQuality();
	}
}
