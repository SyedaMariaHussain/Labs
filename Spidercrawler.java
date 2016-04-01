/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filecrawler;

import static filecrawler.FileCrawler.Stringlist;
import static filecrawler.FileCrawler.list;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Syeda Maria Hussain
 */
public class My_threads implements Runnable{
    
     static List<Index_name> list = new ArrayList<Index_name>();
    static List<File_name> Stringlist = new ArrayList<File_name>();
    int start;
    int end;
    String filename;
    int choice;
    
 
    
      My_threads(List<Index_name> list,int start,int end,String filename,int choice)
	{
            this.list = list;
            this.start = start;
            this.end = end;
            this.choice = choice;
            this.filename = filename;
			
	}
     
       My_threads(int start,int end,String filename,int choiceList,List<File_name> Stringlist)
	{
             this.list = list;
            this.start = start;
            this.end = end;
            this.choice = choice;
            this.filename = filename;
			
	}
       
       
          public static int crawl(File f, int choice) throws IOException {
        int run_check;
    	run_check = run(f, "",choice);
        if(run_check == 1)
        
            return 1;
        else
            return 0;
        
        
    }
          
          
          public static  int run(File f, String indent, int choice) throws IOException {
    	
    	Index_name temp = null;
    	temp = new Index_name();
    	temp._fname = f.getName();
    	temp._fpath = f.getAbsolutePath();
    	temp._fsize = (int) ((f.length())/1024);
        int c = choice;
    	
        
        
        
    	if(f.getName().endsWith(".txt") && f.isFile())
    	{
    		int readlimiter = 0;
    		System.out.println(f.getName() + " is a txt file");
    		FileInputStream in = new FileInputStream(f.getAbsoluteFile());
    	    BufferedReader br = new BufferedReader(new InputStreamReader(in));

    	    String strLine;

        	File_name temp2 = null;
        	temp2 = new File_name();
        	temp2._fname = f.getName();
        	temp2._fpath = f.getPath();
        	temp2._fstring = "";
    	    while ((strLine = br.readLine()) != null) {
    	    	readlimiter++;
    	    	temp2._fstring = temp2._fstring + strLine;
    	    	if(readlimiter > 200)
    	    		break;
    	    }
    	    in.close();
    	    Stringlist.add(temp2);
    	}
    	
    	
    	list.add(temp);
    	
    	if (f.isDirectory()) {
        	// recursive case: directory
        	// print everything in the directory
        	File[] subFiles = f.listFiles();
    		indent += "    ";
    		FileCrawler[] myRunnable = new FileCrawler[subFiles.length];  
    		
        	for (int i = 0; i < subFiles.length; i++) {
              	run(subFiles[i], indent,c);
        	}
        }
        
        return 1;
    }
    
  
    
    private static void search_by_content(String TxtContent)
    {
    	int counter = 0;
    	System.out.println("Debugging the STRING LIST\n");
    	for (ListIterator<File_name> iter = Stringlist.listIterator(); iter.hasNext(); ) {
    	    File_name element = iter.next();
    	    if(element._fstring.contains(TxtContent))
    	    {
    	    	System.out.println("Text Found!");
    	    	System.out.println("At File: " + element._fpath);
    	    	counter = 1;
    	    	break;
    	    }
    	}
    	
    	if (counter == 0)
    		System.out.println("Could not find the specified text!");
    	//System.out.println(Stringlist.get(4));
    	
    }
    private static void searh_file(String fileName)
    {
    	//System.out.println("Debugging the LIST\n");
    	int successfulFind = 0;
    	for (ListIterator<Index_name> iter = list.listIterator(); iter.hasNext(); ) {
    	    Index_name element = iter.next();
    	    if(element._fname.compareTo(fileName) == 0)
    	    {
    	    	System.out.println("File Found!");
    	    	System.out.println("at: " + element._fpath);
    	    	successfulFind = 1;
    	    }
    	}
    	if(successfulFind == 0)
    	{
    		System.out.println("No Files Found !");
    	}
    }    

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param f
     * @param indent
     */
    
  
       
    
}
