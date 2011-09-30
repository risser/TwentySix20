package com.twentysix20.cardstore;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.twentysix20.cardstore.money.TestMoneyCreation;
import com.twentysix20.cardstore.money.TestMoneyExpression;
import com.twentysix20.cardstore.order.TestMultiVendorOrder;
import com.twentysix20.cardstore.order.TestSingleVendorOrder_NumberOfCards;
import com.twentysix20.cardstore.order.TestSingleVendorOrder_Total;
import com.twentysix20.cardstore.order.TestSingleVendorOrder_addRemove;
import com.twentysix20.cardstore.parser.TestCardPageParser;
import com.twentysix20.cardstore.parser.TestSearchParser;
import com.twentysix20.cardstore.postage.TestBasePlusPerCardForEachInstancePostage;
import com.twentysix20.cardstore.postage.TestBasePlusPerCardTypePostage;
import com.twentysix20.cardstore.postage.TestBasePlusPerCardWithCapPostage;
import com.twentysix20.cardstore.postage.TestMTGChicagoPostage;
import com.twentysix20.cardstore.shopper.TestShopperBasics;
import com.twentysix20.cardstore.shopper.TestShopperWithShipping;
import com.twentysix20.cardstore.supplyfilter.TestCheapestCardsPerStoreForOrderFilter;
import com.twentysix20.cardstore.supplyfilter.TestEliminateMoreExpensiveStoresFilter;
import com.twentysix20.cardstore.supplyfilter.TestEliminateMoreExpensiveStoresFilter2;
import com.twentysix20.cardstore.supplyfilter.TestEliminateMoreThanCheapestPlusShippingFilter;
import com.twentysix20.cardstore.supplyrecord.TestSupplyRecordSorter;

@RunWith(Suite.class)
@SuiteClasses({
	TestMoneyExpression.class,
	TestMoneyCreation.class,

	TestMultiVendorOrder.class, 
	TestSingleVendorOrder_addRemove.class, 
	TestSingleVendorOrder_Total.class, 
	TestSingleVendorOrder_NumberOfCards.class, 

	TestCardPageParser.class,
	TestSearchParser.class,

	TestBasePlusPerCardTypePostage.class,
	TestBasePlusPerCardWithCapPostage.class,
	TestBasePlusPerCardForEachInstancePostage.class,
	TestMTGChicagoPostage.class,

	TestShopperBasics.class,
	TestShopperWithShipping.class, 

	TestCheapestCardsPerStoreForOrderFilter.class,
	TestEliminateMoreThanCheapestPlusShippingFilter.class,
	TestEliminateMoreExpensiveStoresFilter.class,
	TestEliminateMoreExpensiveStoresFilter2.class,
	TestSupplyRecordSorter.class, 

	TestComplicatedScenarios.class
	})

public class AllCardStoreTests {
}
