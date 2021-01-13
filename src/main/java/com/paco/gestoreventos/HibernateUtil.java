/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paco.gestoreventos;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author paco
 */
public class HibernateUtil {

  private static final SessionFactory sessionFactory;
  
  static {
    try {
      sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    } catch (HibernateException ex) {
      // Log the exception. 
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
    
}
