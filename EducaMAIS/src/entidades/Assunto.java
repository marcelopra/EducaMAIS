/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author u0181181
 */
public class Assunto implements AbstractEntity {
    
    private String assunto;
    private Integer cargaHoraria;

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    public void valida() throws ValidacaoEntidadeException {
        if(assunto.trim().isEmpty()) {
            throw new ValidacaoEntidadeException("O arquivo é obrigatório.");
        }
        if(cargaHoraria == null || cargaHoraria == 0) {
            throw new ValidacaoEntidadeException("A carga horária deve ser informada.");
        }
    }

    @Override
    public String toString() {
        return assunto;
    }
}
