package com.mooo.mycoz.test;

/*
 *     file: DynamicTypeChecking.java
 *  package: oreilly.hcj.reflection
 *
 * This software is granted under the terms of the Common Public License,
 * CPL, which may be found at the following URL:
 * http://www-124.ibm.com/developerworks/oss/CPLv1.0.htm
 *
 * Copyright(c) 2003-2005 by the authors indicated in the @author tags.
 * All Rights are Reserved by the various authors.
 *
 ########## DO NOT EDIT ABOVE THIS LINE ########## */

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Demonstrates Dynamic class type checking.
 * 
 * @author <a href=mailto:kraythe@arcor.de>Robert Simmons jr. (kraythe)</a>
 * @version $Revision: 1.3 $
 */
public class DynamicTypeChecking {
  /**
   * Demonstrates usage of <tt>isAssignableFrom</tt> on a class object.
   * 
   * @param dataType
   *          The data type to check.
   * 
   * @return The value <tt>true</tt> if the class is a descendant of
   *         <tt>java.lang.Number</tt> or <tt>false</tt> if it isnt.
   */
  public static boolean isNumber(final Class dataType) {
    return Number.class.isAssignableFrom(dataType);
  }

  /**
   * Demonstration Method.
   * 
   * @param args
   *          Command Line arguments.
   */
  public static void main(final String[] args) {

    final Set classes = new HashSet();
    classes.add(Class.class);
    classes.add(Comparable.class);
    classes.add(Serializable.class);
    classes.add(Integer.class);
    classes.add(int.class);
    classes.add(Float[].class);
    classes.add(String.class);
    classes.add(double[].class);
    classes.add(boolean.class);
    
    System.out.println("Using isAssignableFrom:");
    useAssignable(classes);
    System.out.println("\nUsing isInstance:");
    useIsInstance(classes);
    
    System.out.println("is:"+Number.class.isAssignableFrom(Integer.class));

  }

  /**
   * Demonstrates use of <tt>isAssignableFrom</tt> on objects in a set.
   * 
   * @param inputSet
   *          The set to check.
   */
  public static void useAssignable(final Set inputSet) {
    final Iterator iter = inputSet.iterator();
    Object obj = null;

    while (iter.hasNext()) {
      obj = iter.next();
      System.out.println("useAssignable:"+obj);

      if (obj != null) {
        if (Number.class.isAssignableFrom(obj.getClass())) {
          System.out.println("useAssignable:"+obj);
        }
      }
    }
  }

  /**
   * Demonstrates usage of <tt>isInstance</tt> on objects in a set.
   * 
   * @param inputSet
   *          The set to check.
   */
  public static void useIsInstance(final Set inputSet) {
    final Iterator iter = inputSet.iterator();
    Object obj = null;

    while (iter.hasNext()) {
      obj = iter.next();
      if (Number.class.isInstance(obj)) {
        System.out.println("useIsInstance:"+obj);
      }
    }
  }

  /**
   * Demonstration method for class comparison based on passed class and object.
   * 
   * @param dataType
   *          The data type to check.
   * @param inputSet
   *          The input set to check.
   * 
   * @throws NullPointerException
   *           If the data type given is null.
   */
  public static void useIsInstance2(final Class dataType, final Set inputSet) {
    if (dataType == null) {
      throw new NullPointerException();
    }

    final Iterator iter = inputSet.iterator();
    Object obj = null;

    while (iter.hasNext()) {
      obj = iter.next();
      if (dataType.isInstance(obj)) {
          System.out.println("useIsInstance2:"+obj);
      }
    }
  }

  /**
   * A bad version of useInstance. If you uncomment this method you will get a
   * compiler error.
   */

  // public static void useIsInstance3(final Class dataType, final Set inputSet)
  // {
  // final Iterator iter = inputSet.iterator();
  // Object obj = null;
  // while (iter.hasNext()) {
  // obj = iter.next();
  // if (obj instanceof dataType) {
  // System.out.println(obj);
  // }
  // }
  // }
}