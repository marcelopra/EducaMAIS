/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;

/**
 *
 * @author Zulma
 */
public class ExcluirArquivos {
    
    public static boolean deleteDir(String dir) {
        return deleteDir(new File(dir));
    }
    
    public static boolean deleteDir(File dir) {  
       if (dir.isDirectory()) {  
           String[] children = dir.list();  
           for (int i=0; i< children.length; i++) {   
              boolean success = deleteDir(new File(dir, children[i]));
              if (!success) {
                  return false;
              }  
           }  
       }  
     
       // Agora o diretório está vazio, restando apenas deletá-lo.  
       return dir.delete();  
   }
    
}
