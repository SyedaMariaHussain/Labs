/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;
import javax.management.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author shussain.bscs13seecs
 */
public class ORM {

    /**
     * @param args the command line arguments
     */
    private static SessionFactory factory; 
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
        // TODO code application logic here
       MessageDigest md = MessageDigest.getInstance("MD5");
    FileInputStream fis = new FileInputStream("c:\\apache\\cxf.jar");
 
    byte[] dataBytes = new byte[1024];
 
    int nread = 0;
    while ((nread = fis.read(dataBytes)) != -1) {
        md.update(dataBytes, 0, nread);
    };
    byte[] mdbytes = md.digest();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < mdbytes.length; i++) {
        sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
    }
    System.out.println("Digest(in hex format):: " + sb.toString());  
    
    String checksum;
    
    checksum = sb.toString();
    
     /// Hibernate Mapping starts here....
     
     try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    //creating configuration object  
    Configuration cfg=new Configuration();  
    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
		//creating seession factory object  
                
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()). build();
    SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);
    
    //creating session object  
    Session session=factory.openSession(); 
    
    Transaction t=session.beginTransaction();  
			file e1=new file();  
			e1.setID(115);  
			e1.setName("something");  
			e1.setHashValue(checksum);  
			session.persist(e1);//persisting the object  
			t.commit();//transaction is commited  
		session.close();  
                
                boolean a  ;
                a = ask_user();
                
                if (a == true)
                {
                    Scanner o = new Scanner(System.in);
                    String s;
                    
                    System.out.print("Enter the filename");
                    s = o.nextline();
                    
                    
                    
                String hql = "SELECT"+s+"FROM file e";
               String new_check;
                
                Query query = session.createQuery(hql);
                List results;
             results = query.list();
             
             hql2 = "SELECT file hash FROM file WHERE Name like "+ o;
             Query query2 = session.createQuery(hql2);
             List results2 = query2.list();
              String new_check;
                new_check = checksum_cal(o);
                
                if(new_check .equals(old_check)) {
                    
                    System.out.println("No change");
                }
           } else {
                
                System.out.println("Change in file");
                
                
                }
   
    }
    
    // function for asking the user whether he wants to check whether the file has changed or not.
    // Have implemented passive integrity
    public boolean ask_user()
    {
        
      int a;
 
      Scanner in = new Scanner(System.in);
       System.out.print("Press 1 if you want to check integrity. Press any key to exit");
       a = in.nextInt();
       if(a == 1)
       {
           return true;
       
       }
       else 
       {
           return false;
       }
       
       
    
    
    }
    
    // function for checksum
    public String checksum_cal( String file_path) throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
     MessageDigest md = MessageDigest.getInstance("MD5");
    FileInputStream fis = new FileInputStream(file_path);
 
    byte[] dataBytes = new byte[1024];
 
    int nread = 0;
    while ((nread = fis.read(dataBytes)) != -1) {
        md.update(dataBytes, 0, nread);
    };
    byte[] mdbytes = md.digest();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < mdbytes.length; i++) {
        sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
    }
    System.out.println("Digest(in hex format):: " + sb.toString());
    return sb.toString();
    
    }
    
}
