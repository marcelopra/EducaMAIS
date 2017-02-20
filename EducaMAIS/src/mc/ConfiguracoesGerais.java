/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc;

import configuracoes.ConfiguracoesController;
import entidades.Configuracoes;

/**
 *
 * @author Marcelo
 */
public class ConfiguracoesGerais {
    
    public static String DIRETORIO_CURSOS = new ConfiguracoesController().carregar().getCaminhoAplicacao() + "Cursos\\";
    public static String NERO_CMD = "C:\\Program Files\\Nero\\Nero 2015\\Nero Burning ROM\\NeroCmd.exe ";
    public static String DIRETORIO_TEMP_GRAVACAO = "C:\\GravacaoTemp\\";
    
}
