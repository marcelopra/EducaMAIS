/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import java.util.List;
import util.DataUtils;

/**
 *
 * @author Marcelo
 */
public class Pedido implements AbstractEntity {
    
    private Integer codigo;
    private Aluno aluno;
    private Vendedor vendedor;
    private Curso curso;
    private Date dataInicio;
    private Date dataTermino;
    private Date dataEmissaoCertificado;
    private Integer numeroRegistro;
    private Float nota;
    private boolean cancelado;
    private Date dataCadastro;
    private boolean antigo;

    public boolean isAntigo() {
        return antigo;
    }

    public void setAntigo(boolean antigo) {
        this.antigo = antigo;
    }
    
    public String getDataCadastroToString() {
        if(dataCadastro != null) {
            return DataUtils.getDataFormatada(dataCadastro, "dd/MM/yyyy");
        }
        return "";
    }
    
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date date) {
        this.dataCadastro = date;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getDataInicioToString() {
        if(dataInicio != null) {
            return DataUtils.getDataFormatada(dataInicio, "dd/MM/yyyy");
        }
        return "";
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTerminoToString() {
        if(dataTermino != null) {
            return DataUtils.getDataFormatada(dataTermino, "dd/MM/yyyy");
        }
        return "";
    }
    
    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getDataEmissaoCertificadoToString() {
        if(dataEmissaoCertificado != null) {
            return DataUtils.getDataDiaSemanaFormatada(dataEmissaoCertificado);
        }
        return "";
    }
    
    public Date getDataEmissaoCertificado() {
        return dataEmissaoCertificado;
    }

    public void setDataEmissaoCertificado(Date dataEmissaoCertificado) {
        this.dataEmissaoCertificado = dataEmissaoCertificado;
    }

    public String getNumeroRegistroToString() {
        if(numeroRegistro == null || numeroRegistro == 0) {
            return "";
        }
        return numeroRegistro.toString();
    }

    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    @Override
    public void valida() throws ValidacaoEntidadeException {
        if(aluno == null) {
            throw new ValidacaoEntidadeException("O aluno é obrigatório.");
        }
        if(vendedor == null) {
            throw new ValidacaoEntidadeException("O vendedor é obrigatório.");
        }
        if(curso == null) {
            throw new ValidacaoEntidadeException("O curso é obrigatório.");
        }
    }
    
}
