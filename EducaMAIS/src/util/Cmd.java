/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author marcelo
 */
public class Cmd {
    
    private Process exec;
    
    /**
     * Executa um processo e fica aguardando o término do mesmo.
     * @param comando Comando a ser executado.
     * @return Retorna um inteiro que representando o status do processo executado;
     * @throws IOException Caso ocorra algum erro de IO.
     * @throws InterruptedException Caso a thread do processo seja interrompida.
     */
    public int executaAguarda(String comando) throws IOException, InterruptedException {
        int rtn = executa(comando).waitFor();
        return rtn;
    }
    
    /**
     * Executa um processo, porém não fica esperando o término do mesmo.
     * @param comando
     * @return Um objeto "Process".
     * @throws IOException Caso ocorra algum erro de IO.
     * @throws InterruptedException Caso a thread do processo seja interrompida. 
     */
    public Process executaSemAguardar(String comando) throws IOException, InterruptedException {
        return executa(comando);
    }
    
    /**
     * Funcção que faz a execução das funções "executaSemAguardar" e "executaAguarda".
     * @param comando Comando a ser executado.
     * @return Um objeto "Process".
     * @throws IOException Caso ocorra algum erro de IO.
     */
    private Process executa(String comando) throws IOException {
        System.out.println(comando);
        exec = Runtime.getRuntime().exec(comando);
        //new ExecOutPutStrem(exec).imprimir();
        return exec;
    }
    
    /**
     * Verifica se o processo passado por parâmetro está sendo executado.
     * @param nomeProcesso Nome do processo a ser verificado.
     * @return Retorna TRUE se o processo já estiver sendo executado, caso contrário retorna FALSE.
     */
    public boolean verificaSeProcessoEstaExecutando(String nomeProcesso) throws IOException {
        Process p = Runtime.getRuntime().exec("tasklist");  
        InputStream input = p.getInputStream();  
        BufferedInputStream reader = new BufferedInputStream(input);  
        Scanner sc = new Scanner(input);  

        while(sc.hasNext()) {
            if(sc.nextLine().toUpperCase().startsWith(nomeProcesso.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retorna a quantidade de processos iniciados com o nome passado por parâmetro.
     * @param nomeProcesso Nome do processo a ser verificado.
     * @return Retorna a quantidade.
     */
    public int getQtdeProcessosComMesmoNome(String nomeProcesso) throws IOException {
        int qtde = 0;
        
        Process p = Runtime.getRuntime().exec("tasklist");  
        InputStream input = p.getInputStream();  
        BufferedInputStream reader = new BufferedInputStream(input);  
        Scanner sc = new Scanner(input);  

        while(sc.hasNext()) {
            if(sc.nextLine().toUpperCase().startsWith(nomeProcesso.toUpperCase())) {
                qtde ++;
            }
        }
        
        return qtde;
    }
    
}
