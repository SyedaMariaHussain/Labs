/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filecrawler;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;     
import java.util.*;   
import java.util.concurrent.Callable;

/**
 *
 * @author shussain.bscs13seecs
 */
public class FileCrawler implements Runnable {

    /**
     * @param args the command line arguments
     */
    
    private volatile File param1;
    private volatile String param2;
    
    
    public FileCrawler(File param1, String param2)
	{
		this.param1 = param1;
		this.param2 = param2;		
	}
    static List<Index_name> list = new ArrayList<Index_name>();
    static List<File_name> Stringlist = new ArrayList<File_name>();
    
    
    public static void main(String[] args) throws IOException {
        
       
        /*Take directory from the user*/
        Scanner console = new Scanner(System.in);
        Scanner in= new Scanner(System.in);
        System.out.print("Enter the directory  that you want to search:  ");
        String directoryName = console.nextLine();
        
        /*Creates a File object to search in the file structure*/
        File f = new File(directoryName);
        
        
        /*the Crawl Function takes the File argument and starts the searching*/
        //crawl(f);
        System.out.println("Total Files Found: " + list.size() );
        System.out.println("Select the Following Options: ");
        System.out.println("1. Search by File Name ");
        System.out.println("2. Search by File Content(txt) ");
        //Asking from the user whether he wants to search a file or by content of a text file.
       
    }
     
    
}

