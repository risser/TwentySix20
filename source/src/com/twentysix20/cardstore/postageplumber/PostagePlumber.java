package com.twentysix20.cardstore.postageplumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.parser.CardPageParser;
import com.twentysix20.cardstore.parser.SearchParser;
import com.twentysix20.cardstore.shopper.Shopper;
import com.twentysix20.cardstore.strategy.TheStrategy;
import com.twentysix20.cardstore.supplyfilter.CheapestCardsPerStoreForOrderFilter;
import com.twentysix20.cardstore.supplyfilter.EliminateMoreExpensiveStoresFilter;
import com.twentysix20.cardstore.supplyfilter.EliminateMoreThanCheapestPlusShippingFilter;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.supplyrecord.UnplayedOnlyFilter;
import com.twentysix20.util.ListMap;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.InternetHtmlLoader;

public class PostagePlumber {
    public static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );

        int id = 39101;
        String vendorName = "Jax Card Singles";
//        System.out.println("Enter a card ID:");
//        int id = Integer.valueOf(stdin.readLine());
//        System.out.println("Enter a vendor name:");
//        String vendorName = stdin.readLine();

        SingleVendorCardPageParser cardPageParser = new SingleVendorCardPageParser(new InternetHtmlLoader());

        int tries = 0;
        int largestQuantity = 0;
        String largestQuantityID = null;

        while(largestQuantity < 50 && tries < 50) {
        	String info = cardPageParser.getCartIDandQuantity(vendorName, id);
        	if (info == null) continue;
        	String[] ss = info.split("\\|");
        	int quantity = Integer.parseInt(ss[1]);
        	if (quantity > largestQuantity) {
        		largestQuantity = quantity;
        		largestQuantityID = ss[0];
        	}
        	id++;
    		tries++;
        }

        if (largestQuantity > 50)
        	largestQuantity = 50;
System.out.println(new InternetHtmlLoader().getHtmlPage("https://store.tcgplayer.com/Shipping.aspx"));
    }
}