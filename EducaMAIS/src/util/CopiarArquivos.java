/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author marcelo
 */
public class CopiarArquivos {
    
    public void copiar(File origem, File destino) throws FileNotFoundException, IOException, InterruptedException {
        if(!origem.getParent().equalsIgnoreCase(destino.getParent())) {
            if(destino.exists()) {
                destino.delete();
            }
            if(origem.exists()) {

                    File fParentDestino = destino.getParentFile();
                    if(!fParentDestino.exists()) {
                        fParentDestino.mkdirs();
                    }

                    String cmd = "cmd.exe /c copy \"" + origem + "\" \"" + destino + "\" /y";
                    new Cmd().executaAguarda(cmd);

            } else {
                throw new FileNotFoundException("O arquivo de origem não existe: \n\n" + origem);
            }
        }
    }
    
    public void copiar(String origem, String destino) throws FileNotFoundException, IOException, InterruptedException {
        copiar(new File(origem), new File(destino));
    }
    
    public void copiarArquivoParaDiretorio(String origem, String diretorio) throws FileNotFoundException, IOException, InterruptedException {
        if(!diretorio.endsWith("\\")) {
            diretorio += "\\";
        }
        
        File fDirDestino = new File(diretorio);
        if(!fDirDestino.exists()) {
            fDirDestino.mkdirs();
        }
        
        copiar(origem, diretorio + new File(origem).getName());
    }
    
}
