/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;

/**
 *
 * @author u0181181
 */
public class WindowsExplorerUtil {
    
    /**
     * Para o SO Windows, abre o windows explorer com o diretório passado por parâmetro selecionado.
     * @param dir Diretório para vim selecionado.
     */
    public static void abrirDiretorioSelecionado(String dir) {
        try {
            new Cmd().executaSemAguardar("Explorer /select, " + dir);
        } catch (IOException ex) {
            TrataException.fatal(ex);
        } catch (InterruptedException ex) {
            TrataException.fatal(ex);
        }
    }
    
    /**
     * Para o SO Windows, abre o windows explorer com o diretório passado por parâmetro selecionado.
     * @param dir Diretório para vim selecionado.
     */
    public static void abrirDiretorio(String dir) {
        try {
            new Cmd().executaSemAguardar("Explorer /root, " + dir);
        } catch (IOException ex) {
            TrataException.fatal(ex);
        } catch (InterruptedException ex) {
            TrataException.fatal(ex);
        }
    }
    
}
