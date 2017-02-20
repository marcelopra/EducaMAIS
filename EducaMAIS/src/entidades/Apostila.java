/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.File;
import java.util.Objects;
import mc.ConfiguracoesGerais;

/**
 *
 * @author u0181181
 */
public class Apostila implements AbstractEntity {
    
    private String nomeArquivo;
    private String descricao;
    private String caminhoCompleto;

    public String getCaminhoCompleto() {
        return caminhoCompleto;
    }

    public void setCaminhoCompleto(String caminhoCompleto) {
        this.caminhoCompleto = caminhoCompleto;
    }
    
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return nomeArquivo;
    }

    @Override
    public void valida() throws ValidacaoEntidadeException {
        if(nomeArquivo.trim().isEmpty()) {
            throw new ValidacaoEntidadeException("O arquivo é obrigatório.");
        }
        if(!new File(getCaminhoCompleto()).exists()) {
            throw new ValidacaoEntidadeException("Não foi possível encontrar o arquivo:\n\n" + nomeArquivo);
        }
    }
}
