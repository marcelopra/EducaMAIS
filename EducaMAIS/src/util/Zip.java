/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipFile;

/**
 *
 * @author marcelo
 */
public class Zip {
    
    private String arquivoZipSemAspas;
    private String arquivoZipComAspas;
    private File fCaminhoArquivo;
    public static String DIR_7ZIP = "C:\\Program Files\\7-Zip\\7-Zip\\";
    public static String DIR_7ZIP_7zFM = DIR_7ZIP + "7zFM.exe";
    public static String DIR_7ZIP_7zG = DIR_7ZIP + "7zG.exe";

    public Zip(String arquivoZIP) {
        if(arquivoZIP.startsWith("\"")) {
            this.arquivoZipComAspas = arquivoZIP;
            this.arquivoZipSemAspas = arquivoZIP.replace("\"", "");
        } else {
            this.arquivoZipComAspas = "\"" + arquivoZIP + "\"";
            this.arquivoZipSemAspas = arquivoZIP;
        }
        this.fCaminhoArquivo = new File(arquivoZipSemAspas);
    }
    
    /**
     * Realiza a descompactação de todos os arquivos do ZIP para dentro do diretorio.
     * @param diretorioDestino Diretório para onde os arquivos serão descompactados.
     */
    public void descompactar(String diretorioDestino) throws IOException, InterruptedException {
        /*Realiza a descompactação*/
        if(fCaminhoArquivo.exists()) {
            new Cmd().executaAguarda(DIR_7ZIP_7zG + " e " + arquivoZipComAspas + " -o\"" + diretorioDestino + "\" -r -y");
        }
    }
    
    /**
     * Realiza a compactação de todos os arquivos que estão dentro do diretório informado. Caso o caminho do arquivo
     * ZIP compactado já exista, o mesmo será sobreescrito.
     * @param diretorio Diretório que contém os arquivos para serem compactados.
     * @param caminhoArquivoZipCompactado Caminho de destino do arquivo .ZIP.
     */
    public void compactarDiretorio(String diretorio) throws IOException, InterruptedException {
        
        /*Deleta o arquivo de destino caso ele já exista.*/
        File fArqDestino = new File(arquivoZipSemAspas);
        if(fArqDestino.exists()) {
            fArqDestino.delete();
            Thread.sleep(250); //Da uma aguarda pois as vezes dava erro ao compactar no mesmo caminho em seguida.
        }
        
        /*Realiza a compactação*/
        new Cmd().executaAguarda(DIR_7ZIP_7zG + " a \"" + arquivoZipComAspas + "\" \"" + diretorio + "*\"");
    }
    
    /**
     * @return Retorna a quantidade de arquivos dentro do ZIP.
     */
    public int getQtdeArquivosDentro() throws IOException {
        if(fCaminhoArquivo.exists()) {
            ZipFile zf = new ZipFile(arquivoZipSemAspas);
            return zf.size();
        }
        return 0;
    }
    
    /**
     * @return Abre uma lista para selecionar um arquivo de dentro do zip.
     */
    public String selecionarArquivoDentroDoZip() {
        ZipListaSelecionarArquivo zSel = new ZipListaSelecionarArquivo(arquivoZipComAspas);
        zSel.setVisible(true);

        if(zSel.BTN_PRESSIONADO == zSel.BTN_SELECIONAR) {
            return zSel.getNomeArquivoSelecionado();
        }
        return null;
    }
    
        /**
     * @return Retorna uma listagem com os nomes dos arquivos detro do ZIP.
     */
    public List<String> getListagemArquivoDentroZip() {
        List<String> listagem = new ArrayList<String>();
        try {
            ZipFile zf = new ZipFile(arquivoZipSemAspas);
            Enumeration e = zf.entries();
            
            while(e.hasMoreElements()){
                listagem.add(e.nextElement().toString());
            }
        } catch (IOException ex) {
            TrataException.fatal(ex);
        }
        return listagem;
    }
    
}
