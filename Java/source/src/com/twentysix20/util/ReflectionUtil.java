package com.twentysix20.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.twentysix20.util.exception.ReflectionException;

public class ReflectionUtil {

    /**
     * Creates an attribute map based on the following criteria:
     * <li>a getter exists that begins with 'get' or 'is',
     * <li>the getter has no parameters,
     * <li>the matching setter begins with 'set' (or 'add' for Collections),
     * <li>the setter takes a single parameter,
     * <li>the setter's parameter and getter's return are compatible types.
     * This method returns a Map with the getter Method as the key and the setter Method as the value.
     * Note that these are actual Method classes and not Strings.
     * @param c The class to find all attributes for.
     * @return A Map containing all getter Methods as keys and setter Methods as values.
     */    
    public static Map createAttributeMap(Class c) {
        Map<Method,Method> attributeMap = new HashMap<Method,Method>();
        // get all public methods
        Method[] methods = c.getMethods();
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();

            // we are only interested in the getters
            if (!(name.startsWith("get") || name.startsWith("is"))) continue;
            Method getter = methods[i];
            
            // we are only interested in getters with no parameters
            Class[] params = getter.getParameterTypes();
            if (params.length != 0) continue;
            
            String subname = (name.startsWith("is") ? name.substring(2) : name.substring(3));
            Class returnType = getter.getReturnType();
            if (Collection.class.isAssignableFrom(returnType)) {
                // if it's a collection (List, Set) check for "add" methods.
                Method setter = findAddMethod(adder(subname), methods);
                if (setter == null)
                    setter = findSetMethod(setter(subname), getter, methods);
                if (setter != null)
                    // we are only interested in setters that start with add and have a single parameter
                    attributeMap.put(getter,setter);
            } else {
                Method setter = findSetMethod(setter(subname), getter, methods);
                if (setter != null)
                    attributeMap.put(getter,setter);
            }
        }

// condense for best match here.

        return attributeMap;
    }

    private static Method findAddMethod(String generatedSetterName, Method[] methods) {
        for (int j = 0; j < methods.length; j++) {
            if (methods[j].getParameterTypes().length != 1) continue;

            if (generatedSetterName.equals(methods[j].getName()) || 
                    noSuffixEquals(generatedSetterName, methods[j].getName(), "s") ||
                    noSuffixEquals(generatedSetterName, methods[j].getName(), "es")) {
                return methods[j];
            }
        }
        return null;
    }
    private static boolean noSuffixEquals(String compare, String orig, String suffix) {
        if (compare.equals(orig)) return true;
        if (StringUtil.isEmpty(suffix)) return false;
        if (!compare.endsWith(suffix)) return false;
        compare = compare.substring(0,compare.length()-suffix.length());

        return compare.equals(orig);
    }

    private static Method findSetMethod(String setterName, Method getter, Method[] methods) {
        for (int j = 0; j < methods.length; j++) {
            if (!methods[j].getName().equals(setterName))
                continue;
            Class[] params = methods[j].getParameterTypes();
            if (params.length != 1) 
            // we are only interested in setters with a single parameter
                continue;
            Class paramType = params[0];
            if (!paramType.isAssignableFrom(getter.getReturnType()))
            // we are only interested in setters whose getters return compatible types
                continue;
            return methods[j];
        }
        return null;
    }
    
    
    /**
     * Calls a setter with a single parameter.
     * @param setter The method to be executed.
     * @param victim The object on which the called method resides.
     * @param parameter The parameter object passed to the setter.
     * @throws ReflectionException
     */
    public static void setOnObject(Method setter, Object victim, Object parameter) throws ReflectionException {
        if (setter == null) throw new IllegalArgumentException("Can't set object ("+parameter+") on object ("+victim+") with null setter.");
        if (victim == null) throw new IllegalArgumentException("Can't set object ("+parameter+") on null object ("+victim+") with setter ("+setter+").");
        try {
            setter.invoke(victim, new Object[]{parameter});
        } catch (Exception e) {
            throw new ReflectionException("An error occurred while trying to invoke the setter <"+setter.getName()+"> on the destination object <"+victim+"> with the parameter <"+(parameter == null ? "" : parameter.getClass().getName()+":")+parameter+">.",e);
        }
    }

    /**
     * @param fieldName
     * @param victim
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void clearCollection(String fieldName, Object victim) throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException  {
        Field coll = victim.getClass().getField(fieldName);
        Method clear = coll.getClass().getMethod("clear",null);
//        String clearName = "clear" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
//        Method clear = klass.getMethod(clearName,null);
        clear.invoke(victim,null);
    }

    /** This replaces any Collections with known quantities, rather than what was originally there
     *  when the object was created.  This fixes the problem when we use HashSets in the classes,
     *  but hibernate relies on LinkedHashSets to maintain loading order.  If we didn't do this,
     *  item order gets wacked out by the Hash in the HashSet and when a renumber algorithm runs,
     *  it renumbers all the items in a new order.  Trust me, it's needed. 
     * @param origColl  The original collection
     * @param victim  The object in which the Collection resides 
     * @param setterName  The setter method used to replace the Collection.  This must exist, 
     *                    because hibernate uses it.
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public static void forceSettingOfCollection(Collection origColl, Object victim, String setterName) {
        // make sure collection on destination is same type (sortable?) as collection on original, if we can.
        try {
            Collection newCollection = null;
            if (origColl instanceof SortedSet) {
            	Comparator comparator = ((SortedSet)origColl).comparator();
            	if (comparator!=null) {
            		try {
						newCollection = new TreeSet((Comparator)comparator.getClass().newInstance());
					} catch (InstantiationException e) {
						throw new ReflectionException(e);
					} catch (IllegalAccessException e) {
						throw new ReflectionException(e);
					}
            	} else {
            		newCollection = new TreeSet();
            	}
            } else if (origColl instanceof Set)
                newCollection = new LinkedHashSet();
            else if (origColl instanceof List)
                newCollection = new ArrayList();
            else
                newCollection = (Collection)createNewInstance(origColl);
            Class klass = victim.getClass();
            Class collClass = origColl.getClass();
            Method setter = null;
            while (!klass.equals(Object.class) && (setter == null)) {
                Method[] methods = klass.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    String aName = methods[i].getName();
                    if (!aName.equals(setterName)) continue;
                    Class[] params = methods[i].getParameterTypes();
                    if (params.length != 1) continue;
                    if (params[0].isAssignableFrom(collClass)) {
                        setter = methods[i];
                        break;
                    }
                }
                if (setter == null)
                    klass = klass.getSuperclass();
            }
            if (setter != null) {
                // if it's null, just forget it.
                setter.setAccessible(true);
                setOnObject(setter, victim, newCollection);
            }
        } catch (ReflectionException re) {
            // if it doesn't work, forget it.  We'll go with whatever was there.
        }
    }

    /**
     * Create a new instance of the type of object passed, as long as the object has a default constructor.
     * A null will return a null.
     * @param original The original object to make a new instance of.
     * @return The new instance of the class represented by the provided object.
     * @throws ReflectionException Thrown if the class of the provided object doesn't have a default empty constructor
     * or if some exception occurred during class creation.
     */    
    public static Object createNewInstance(Object original) throws ReflectionException {
        if (original == null) return null;
        if (original.getClass().isArray()) {
            Class klass = original.getClass();
            Class type = klass.getComponentType();
            int length = Array.getLength(original);
            return Array.newInstance(type, length);
        } else
            return createNewInstance(original.getClass());
    }
    /**
     * Create a new instance of the class passed, as long as the class has a default constructor.
     * A null will return a null.
     * @param klass The class to make a new instance of.
     * @return The new instance of the class represented by the provided object.
     * @throws ReflectionException Thrown if the class of the provided object doesn't have a default empty constructor
     * or if some exception occurred during class creation.
     */    
    public static Object createNewInstance(Class klass) throws ReflectionException {
        if (klass == null) return null;
        if (klass.isArray()) throw new IllegalArgumentException("Can't pass an array class.");
        if (klass.isPrimitive()) throw new IllegalArgumentException("Can't pass a primitive.");

        Constructor cons = null;
        Object newInstance = createNonNullConstructorInstance(klass);
        if (newInstance == null) {
            try {
                cons = klass.getConstructor(null);
            } catch (NoSuchMethodException nsme) {
                throw new ReflectionException("Cannot create a new instance of <"+klass.getName()+"> without a no-param constructor.",nsme);
            }
            try {
                newInstance = cons.newInstance(null);
            } catch (Exception ee) {
                throw new ReflectionException("An error occurred while creating a new instance of class <"+klass.getName()+">.",ee);
            }
        }
        return newInstance;
    }

    private static Object createNonNullConstructorInstance(Class klass) {
        if (klass.equals(Timestamp.class))
            return new Timestamp(0);
        if (klass.equals(Date.class))
            return new Date(0);
        return null;
    }

    /**
     * Force the setting of a field, regardless of accessibility (public/private)
     * @param victim The object to set the field on
     * @param field The field to be set (as Field, not String)
     * @param param The parameter to set the value of the field to.
     * @throws IllegalAccessException
     */
    public static void forceSetField(Object victim, Field field, Object param) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(victim, param);
    }

    /**
     * Returns the name of the class without path info.
     * @param object The object to determine the name of
     * @return The name of the class without path info.
     */
    public static String getSimpleNameOfClass(Object object) {
    	if (object == null) return "null";
    	return getSimpleName(object.getClass());
    }

    /**
     * Returns the name of the class without path info.
     * @param object The object to determine the name of
     * @return The name of the class without path info.
     */
    public static String getSimpleName(Class c) {
        String name = c.getName();
        name = name.substring(name.lastIndexOf('.') + 1);
        return name;
    }
    
    /** The thing is, getFields on a class only returns the fields exactly for that class, 
     *  not any of it's superclasses, which stinks.  This will recursively run up all
     *  superclasses and compile a List of all the Fields.
     * @param klass The class to check.
     * @return The list of Fields.
     */
    public static List getAllFields(Class klass) {
        if (klass == null) throw new IllegalArgumentException("Can't find fields for a null class.");
        if (klass.equals(Object.class))
            return new ArrayList();
        else {
            List allFields = getAllFields(klass.getSuperclass());
            List newFields = Arrays.asList(klass.getDeclaredFields());
            allFields.addAll(newFields);
            return allFields;
        }
    }
    
    /** Same with interfaces.
     * @param klass The class to check.
     * @return The list of implemented interfaces.
     */
    public static Set getAllInterfaces(Class klass) {
        if (klass == null) throw new IllegalArgumentException("Can't find interfaces for a null class.");
        if (klass.isInterface()) {
        	Set iSet = new HashSet();
        	iSet.add(klass);
        	return iSet;
        } else if (klass.equals(Object.class)) {
			return new HashSet();
        } else {
            Set allInterfaces = getAllInterfaces(klass.getSuperclass());
            List newInterfaces = Arrays.asList(klass.getInterfaces());
            allInterfaces.addAll(newInterfaces);
            return allInterfaces;
        }
    }

    /** Returns a list of all the fields in a class whose types are the same as, or are 
     *  subclasses of, the provided array of Classes.
     * @param klass The class to gather fields from.
     * @param types The array of classes to check fields against.
     * @return The List of Fields.
     */
    public static List findAllFieldsOfGivenTypes(Class klass, Class[] types) {
        List allFields = getAllFields(klass);
        List someFields = new ArrayList();
        Iterator itrFields = allFields.iterator();
        OUTER_LOOP: while (itrFields.hasNext()) {
            Field field = (Field)itrFields.next();
            Class fclass = field.getType();
            for (int t = 0; t < types.length; t++)
                if (fclass.equals(types[t]) || types[t].isAssignableFrom(fclass)) {
                    someFields.add(field);
                    continue OUTER_LOOP;  // Eeeek!  A GOTO!  :)
                }
        }
        return someFields;
    }

    /** Returns a list of all the fields in a class whose types are the same as, or are 
     *  subclasses of, the provided class.
     * @param klass The class to gather fields from.
     * @param types The class to check fields against.
     * @return The List of Fields.
     */
    public static List findAllFieldsOfGivenType(Class klass, Class type) {
        Class[] types = new Class[]{type};
        return findAllFieldsOfGivenTypes(klass, types);
    }
    
    
    /** Sure you could use getConstructor from the class, but this is more robust.
     *  First, it looks at all available constructors, including superclasses, not
     *  just those on the actual class.  Second, it will work if the parameters are
     *  not exact matches, but are subclasses.
     * @param klass  The class to search for constructors.
     * @param params  The list of parameters for the constructor.
     * @return The Constructor.
     * @throws ReflectionException If the requested constructor can't be found.
     */
    public static Constructor findConstructor(Class klass, Object[] params) throws ReflectionException {
        if (params == null) {
            try {
                Constructor con = klass.getConstructor(null);
                return con;
            } catch (NoSuchMethodException nsme) {
                throw new ReflectionException("Can't find no-param constructor.");
            }
        } else {
            Class[] paramClasses = new Class[params.length];
            for (int i = 0; i < params.length; i++)
                if (params[i] != null)
                    paramClasses[i] = params[i].getClass();
                else
                    throw new IllegalArgumentException("Item "+i+" in param array is 'null'.");
            Constructor[] cons = klass.getConstructors();
            Constructor direct = null;
            for (int i = 0; i < cons.length; i++) {
                Constructor con = cons[i];
                Class[] ps = con.getParameterTypes();
                if (ps.length != paramClasses.length) continue;
                boolean itWorks = true;
                for (int j = 0; j < ps.length; j++) {
                    if (!ps[j].isAssignableFrom(paramClasses[j]))
                        itWorks = false;
                }
                if (itWorks)
                    direct = con;
            }
            if (direct == null) 
                throw new ReflectionException("Can't find constructor matching '"+params+"'.");
            else
                return direct;
        }
    }

    private static String buildMethodName(String prefix, String fieldName) {
        if (StringUtil.isEmpty(fieldName)) throw new IllegalArgumentException("Can't pass blank or null String.");
        return prefix + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }

    public static String setter(String fieldName) {
        return buildMethodName("set", fieldName);
    }

    public static String getter(String fieldName) {
        return buildMethodName("get", fieldName);
    }
    
    public static String adder(String fieldName) {
        return buildMethodName("add", fieldName);
    }

    public static Method findAdderOrSetter(Class klass, String name, Object param) throws ReflectionException {
        Method setterMethod = null;
        String setName = setter(name);
        String addName = adder(name);
        try {
            setterMethod = findSetter(klass, setName, param);
        } catch (ReflectionException e1) {
            try {
                setterMethod = findSetter(klass, addName, param);
            } catch (ReflectionException e2) {
                throw new ReflectionException("Can't find setter matching '"+setName+"' or '"+addName+"'.");
            }
        } 
        
        return setterMethod;
    }
    
    /** This method looks for a method with the given name that takes either the
     *  given object *OR* an object that can be constructed with the given object.
     *  For example, if you pass a String as the param, and the setter is "setFoo" 
     *  which takes a Foo, it will still find this method if Foo can be constructed
     *  from a String.  If there are two methods for setFoo, it will take setFoo(String)
     *  (the direct setter) before setFoo(Foo) (an indirect setter).
     *  
     * @param klass The class to search.
     * @param setterName The name of the setter method you're looking for.
     * @param param The parameter you intend to set the setter with.
     * @return The Method that matches.
     * @throws ParseModelDataException If no direct or indirect setter can be found.
     */
    public static Method findSetter(Class klass, String setterName, Object param) throws ReflectionException {
        if (klass == null) throw new IllegalArgumentException("Can't find a setter for a null class."); 
        if (StringUtil.isEmpty(setterName)) throw new IllegalArgumentException("Can't find a setter with blank or null name."); 
        if (param == null) throw new IllegalArgumentException("Can't find a setter with a null parameter."); 
        Method[] methods = klass.getMethods();
        Method directMethod = null;
        Method indirectMethod = null;
        for (int i = 0; i < methods.length; i++)
            if (setterName.equals(methods[i].getName())) {
                Method m = methods[i];
                Class[] params = m.getParameterTypes();
                if (params.length != 1) continue;
                Class paramClass = params[0];

                if (paramClass.isAssignableFrom(param.getClass()))
                    directMethod = m;
                else {
                    try {
                    	paramClass.getConstructor(new Class[]{param.getClass()});
                    } catch (NoSuchMethodException nsme) {
                        continue;
                    }
                    indirectMethod = m;
                }
            }
        if ((directMethod == null) && (indirectMethod == null)) 
            throw new ReflectionException ("Can't find setter matching '"+setterName+"' on class '"+klass.getName()+"' with parameter '"+param.getClass().getName()+"'.");
        if (directMethod != null)
            return directMethod;
        else
            return indirectMethod;
    }


	public static boolean isThrowable(Class returnType) {
		return Throwable.class.isAssignableFrom(returnType);
	}    
}
