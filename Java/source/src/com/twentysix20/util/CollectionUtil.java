package com.twentysix20.util;

import java.util.*;

public class CollectionUtil {
	
	public static boolean isEmpty(Collection collection) {
		return (collection == null) || collection.isEmpty();
	}

	/**
         * Returns all objects of given type and its subtypes
	 * @param clazz The class you want to return all of the instances of.
	 * @param objects The collection you'd like to search.
	 * @return A List of all of the instances of a specific Class found in a Collection.
	 */
	public static List getAllInstances(Class clazz, Collection objects) {
		List allInstances = new ArrayList();
		if(objects != null && clazz != null) {
			Iterator objectsIterator = objects.iterator();
			while(objectsIterator.hasNext()) {
				Object instance = objectsIterator.next();
				if(instance != null) {
					if(clazz.isAssignableFrom(instance.getClass())) {
						allInstances.add(instance);
					}
				}
			}
		}
		return allInstances;
	}
	
	/**
         * Returns the first object of given type or its subtypes
	 * @param clazz The class you want to return the first instance found of.
	 * @param objects The collection you'd like to search.
	 * @return The first instance of a specific Class found in a Collection.  Returns null if no instance is found.
	 */
	public static Object getFirstInstance(Class clazz, Collection objects) {
		if(objects != null && clazz != null) {
			Iterator objectsIterator = objects.iterator();
			while(objectsIterator.hasNext()) {
				Object instance = objectsIterator.next();
				if(clazz.isAssignableFrom(instance.getClass())) {
					return instance;
				}
			}
		}
		return null;
	}
	
    public static boolean hasInstance(Class clazz, Collection objects) {
        return (getFirstInstance(clazz, objects) != null);
    }

}
