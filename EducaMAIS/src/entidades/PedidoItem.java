/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import processos.criarpedido.PedidoController;
import util.DataUtils;

/**
 *
 * @author Marcelo
 */
public class PedidoItem implements AbstractEntity {
    
    private Integer sequencial;
    private Curso curso;
    private Float valorCobrado;
    private Integer numeroRegistro;
    private Date dataInicio;
    private Date dataFinal;
    private Float nota;
    private Date dataEmissao;
    private Integer codigoPedido;
    private boolean cancelado;

    public String getSituacao() {
        if(isCancelado()) {
            return "Cancelado";
        }
        return "";
    }
    
    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Pedido getPedido() {
        return new PedidoController().carregar(codigoPedido);
    }
    
    public Integer getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getDataEmissaoToString() {
        if(dataEmissao != null) {
            return DataUtils.getDataDiaSemanaFormatada(dataEmissao);
        }
        return null;
    }
    
    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getDataInicioToString() {
        if(dataInicio != null) {
            return DataUtils.getDataDiaSemanaFormatada(dataInicio);
        }
        return null;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinalToString() {
        if(dataFinal != null) {
            return DataUtils.getDataDiaSemanaFormatada(dataFinal);
        }
        return null;
    }
    
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }
    
    public String getNumeroRegistroToString() {
        if(numeroRegistro != null) {
            return numeroRegistro.toString();
        }
        return "";
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Float getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(Float valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Integer getSequencial() {
        return sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public void valida() throws ValidacaoEntidadeException {
        if(curso == null) {
            throw new ValidacaoEntidadeException("O curso é obrigatório.");
        }
        if(valorCobrado == null) {
            throw new ValidacaoEntidadeException("O valor cobrado é obrigatório.");
        }
        if(dataInicio == null) {
            throw new ValidacaoEntidadeException("A data de início do curso é obrigatória.");
        }
        if(dataFinal == null) {
            throw new ValidacaoEntidadeException("A data de término do curso é obrigatória.");
        }
        if(nota == null || nota > 10) {
            throw new ValidacaoEntidadeException("A nota informada é inválida.");
        }
    }
    
}
