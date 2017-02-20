/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author marce
 */
public class Configuracoes implements AbstractEntity {
    
    private String caminhoAplicacao;

    public String getCaminhoAplicacao() {
        return caminhoAplicacao;
    }

    public void setCaminhoAplicacao(String caminhoAplicacao) {
        this.caminhoAplicacao = caminhoAplicacao;
    }

    @Override
    public void valida() throws ValidacaoEntidadeException {
        if(caminhoAplicacao.trim().isEmpty()) {
            throw new ValidacaoEntidadeException("O caminho da aplicação é obrigatório.");
        }
    }
    
}
