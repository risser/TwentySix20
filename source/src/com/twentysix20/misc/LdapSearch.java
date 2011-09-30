package com.twentysix20.misc;

//import java.io.*;
//import java.util.*;
//import geae.ldap.*;

public class LdapSearch {
/*
  public static void main (String args []) throws Exception {

    int objectType=0; //CONSTANT for object type to search on Person, Group, Company
    String query = null;  //ldap query string
    String [] attribs = {"*"}; //attributes to return
    GEAELdapSearchOperation searchOp = null;  //search command
    GEAELdapObject ldapObj = null;  //ldap data object
    Vector usernames = null;

    // Create a geae.security.GEAEPrincipal object.  Object is requiried for all LDAP transactions. 
    geae.security.GEAEPrincipal principal = new geae.security.GEAEPrincipal();

    //create a LDAP Search Operation
    searchOp = new GEAELdapSearchOperation();

    // set the object type (geae.ldap.GEAELdapOperation.PERSON,geae.ldap.GEAELdapOperation.GROUP or geae.ldap.GEAELdapOperation.COMPANY) 

    searchOp.setObjectType(geae.ldap.GEAELdapOperation.PERSON);

    // set attributes and query string for search

    searchOp.setAttributes(attribs);
    searchOp.setQuery("sn=Back");

    GEAELdapSL ldapBean = GEAELdapBeanFactory.getLdapBean(principal);

    // Execute the search using by passing the search operation to the GEAELdapSLBean's doOperation method.
     searchOp = (GEAELdapSearchOperation) ldapBean.doOperation(searchOp);

    // store search results (a vector of GEAELdapObjects) into an enumeration for futher processing
    Enumeration resultsEnum = searchOp.getResultSet().elements();

	boolean moreElements=resultsEnum.hasMoreElements();
	if (moreElements) {
		 ldapObj = (GEAELdapObject) resultsEnum.nextElement();
		//extract attribute values
		Enumeration attEnum = ldapObj.getAttribNames();
		while(attEnum.hasMoreElements()) {
			String attName = (String)attEnum.nextElement();
			String [] attribValuesArray = ldapObj.getValues(attName);
			if(attribValuesArray != null) {
				for(int i =0 ; i <attribValuesArray.length; i++ ) {
					System.out.println(attName+ " :"+attribValuesArray[i]);
				}
			}
		}
	}
}
*/
}

