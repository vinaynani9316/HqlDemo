package com.hibernate.hql.HqlDemo;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	 Configuration config= new Configuration().configure().addAnnotatedClass(Student1.class);
         
         ServiceRegistry registry= new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
          
          SessionFactory sf= config.buildSessionFactory(registry);
          
          Session session=sf.openSession();
          
          session.beginTransaction();                    // to write into database
//          Random r= new Random();
//          for( int i=0; i<=50; i++)
//          {
//        	  Student1 s= new Student1();
//        	  s.setRollno(i);
//        	  s.setName("Name" +i);
//        	  s.setMarks(r.nextInt(100));
//        	  session.save(s);
//          }
          
          Query q=session.createQuery("from Student1 where marks >90");   // to fetch the sql data from class entity name(all elements)
          List<Student1> students =q.list();
          for(Student1 s1 : students)
          {
        	  System.out.println(s1);
          }
          System.out.println("__________________________");
          
         Query q1=session.createQuery("from Student1 where rollno =5");   // to fetch the sql data from class entity name (unique)
         Student1 student =(Student1) q1.uniqueResult();
         System.out.println(student);
         System.out.println("__________________________");
         
         Query q2=session.createQuery("select rollno, marks, name from Student1 where rollno=18");   // using where clause 
         Object[] stud=(Object[]) q2.uniqueResult();
         for(Object o: stud )
        	 System.out.println(o);
         System.out.println("__________________________");
         
         Query q3=session.createQuery("select rollno, name from Student1");     
         List<Object[]> studen =(List<Object[]>)q3.list();
         for(Object[] s: studen)
        	 System.out.println(s[0] + " :" +s[1]);
         System.out.println("__________________________");
         
         Query q4=session.createQuery("select sum(marks) from Student1 s where s.marks>60");     
         List stu =(List)q4.list();
         for(Object marks: stu)
        	 System.out.println(marks);
         System.out.println("__________________________");
         
         Query q5=session.createQuery("select sum(marks) from Student1 s where s.marks>70");     
         Number marks =(Number)q5.uniqueResult();
        	 System.out.println(marks);
        	 System.out.println("__________________________");
        	 
         int b=75;
         Query q6=session.createQuery("select sum(marks) from Student1 s where s.marks> :b");  
         q6.setParameter("b", b);
         Number marks1 =(Number)q6.uniqueResult();
    	 System.out.println(marks1);
    	 System.out.println("__________________________");
    	 
    	 SQLQuery q7=session.createSQLQuery("select * from student1 where marks>55");  // using sql query(native query)
    	 q7.addEntity(Student1.class);
    	 List<Student1> st=q7.list();
    	 for(Student1 s : st)
    		 System.out.println(s);
    	 System.out.println("__________________________");
    	 
    	 // using native queries i.e sql
    	 SQLQuery q8=session.createSQLQuery("select name, marks from student1 where marks>95");
    	 q8.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
    	 List<Integer> stude =q8.list();
    	 
    	 for(Object o: stude)
    	 {
    		 Map<String, Integer> m=(Map<String, Integer>)o;
    		 System.out.println(m.get("name")+ " : " + m.get("marks"));
    	 }
    	 
         
          session.getTransaction().commit();
    }
}
