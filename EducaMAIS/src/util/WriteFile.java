/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author marcelo
 */
public class WriteFile {
    
    /**
     * Realiza uma escrita em um arquivo. Obs.: A escrita é feito no final do arquivo, sem apagar
     * o conteúdo já existente.
     * @param texto
     * @param arquivo
     * @throws IOException
     */
    public static void write(String texto, String arquivo) throws IOException {
        File dir = new File(arquivo).getParentFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }
        
        OutputStream os = new FileOutputStream(arquivo, true);
        OutputStreamWriter osw = new OutputStreamWriter(os, "ISO-8859-1");
        BufferedWriter escreve = new BufferedWriter(osw);
                
        escreve.write(texto);
        escreve.newLine();
        escreve.flush();
        
        escreve.close();
        osw.close();
        os.close();
    }

    /**
     * Realiza uma escrita em um arquivo. Verifica se é para deletar o arquivo antes de escrever.
     * @param texto
     * @param arquivo
     * $param deletar - True para deletar o arquivo antes de escrever.
     * @throws IOException
     */
    public static void write(String texto, String arquivo, boolean deletar) throws IOException {
        if(deletar) {
            File temp = new File(arquivo);
            if(temp.exists()) {
                temp.delete();
            }
        }
        write(texto, arquivo);
    }
    
}
