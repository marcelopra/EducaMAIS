/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcelo
 */
public class ReadFile {

    /**
     * Função utilizada para ler somente a primeira linha do arquivo.
     * @param arquivo 
     * @return Retorna uma String que repsenta a primeira linha do arquivo
     * @throws IOException
     */
    public static String getPrimeiraLinhaArquivo(File arquivo) throws IOException {
        String str = "";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(arquivo));
            while (in.ready()) {
                str = in.readLine();
                return str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                in.close();
            }
        }
        return str;
    }

    /**
     * Função utilizada para ler somente a primeira linha do arquivo.
     * @param arquivo 
     * @return Retorna uma String que repsenta a primeira linha do arquivo
     * @throws IOException
     */
    public static String getPrimeiraLinhaArquivo(String arquivo) throws IOException {
        return getPrimeiraLinhaArquivo(new File(arquivo));
    }

    /**
     * Função utilizada para ler um arquivo por completo.
     * @param arquivo 
     * @return Retorna uma String com todo o conteúdo do arquivo.
     * @throws IOException
     */
    public static String readArquivo(String arquivo) throws IOException {
        return readArquivo(new File(arquivo));
    }

    /**
     * Função utilizada para ler um arquivo por completo.
     * @param arquivo 
     * @return Retorna uma String com todo o conteúdo do arquivo.
     * @throws IOException
     */
    public static String readArquivo(File arquivo) throws IOException {
        String arquivoLido = "";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(arquivo));
            while (in.ready()) {
                arquivoLido += in.readLine() + "\n";
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if(in != null) {
                in.close();
            }
        }
        return arquivoLido;
    }
    
    public static String readArquivoIgnorandoPrimeiraLinha(String arquivo) throws IOException {
        String arquivoLido = "";
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(arquivo));
            in.readLine();
            while (in.ready()) {
                arquivoLido += in.readLine() + "\n";
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if(in != null) {
                in.close();
            }
        }
        return arquivoLido;
    }
    
    public static List<String> readLinhasArquivo(String arquivo, String charSet) throws IOException {
        List<String> arquivoLido = new ArrayList<String>();
        if(new File(arquivo).exists()) {
            InputStream is = new FileInputStream(arquivo);
            InputStreamReader isr = new InputStreamReader(is, charSet);
            BufferedReader in = new BufferedReader(isr);

            while(in.ready()){
                arquivoLido.add(in.readLine());
            }

            is.close();
            isr.close();
            in.close();
        }

        return arquivoLido;
    }
    
    public static List<String> readLinhasArquivo(File arquivo, String charSet) throws IOException {
        return readLinhasArquivo(arquivo.getPath(), charSet);
    }
    
    public static List<String> readLinhasArquivoUtf16(File arquivo) throws IOException {
        return readLinhasArquivo(arquivo.getPath(), "UTF-16");
    }
    
    public static List<String> readLinhasArquivoUtf16(String arquivo) throws IOException {
        return readLinhasArquivo(arquivo, "UTF-16");
    }
    
    public static List<String> readLinhasArquivo(String arquivo) throws IOException {
        List<String> linhasArquivo = new ArrayList<String>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(arquivo));
            while (in.ready()) {
                linhasArquivo.add(in.readLine());
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if(in != null) {
                in.close();
            }
        }
        return linhasArquivo;
    }
}
