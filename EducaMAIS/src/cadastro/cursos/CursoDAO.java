package cadastro.cursos;

import cadastro.areas.AreaController;
import dao.ExecutarSql;
import dao.GenericSimpleDAO;
import entidades.Apostila;
import entidades.Assunto;
import entidades.Curso;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.TrataException;

public class CursoDAO extends GenericSimpleDAO<Curso, Integer> {
    
    @Override
    public void persist(Curso curso) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("INSERT INTO CURSOS "
                                                             + "(NOME, VALOR_BASE, CARGA_HORARIA, CODIGO_AREA)"
                                                             + "VALUES "
                                                             + "(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, curso.getNome());
            stmt.setFloat(2, 0);
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getArea().getCodigo());
            ExecutarSql.update(stmt);
            
            Integer codigo = ExecutarSql.getAutoIncrementIdGeradoPeloInsert(stmt);
            
            curso.setCodigo(codigo);
            
            gravarListagemApostilas(curso, curso.getListagemApostilas());
            gravarListagemAssuntos(codigo, curso.getListagemAssuntos());
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }

    public void gravarListagemApostilas(Curso curso, List<Apostila> listagem) {
        try {
            
            /*Deleta a listagem*/
            PreparedStatement stmtDelete = conexaoDb.prepareStatement("DELETE FROM CURSOS_APOSTILAS WHERE CODIGO_CURSO=?");
            stmtDelete.setInt(1, curso.getCodigo());
            ExecutarSql.update(stmtDelete);
            
            /*Grava novamente*/
            for(Apostila a : listagem) {
                PreparedStatement stmt = conexaoDb.prepareStatement("INSERT INTO CURSOS_APOSTILAS "
                                                                 + "(CODIGO_CURSO, SEQUENCIAL, ARQUIVO, DESCRICAO)"
                                                                 + "VALUES "
                                                                 + "(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, curso.getCodigo());
                stmt.setInt(2, getProximoSequencialApostila(curso.getCodigo()));
                stmt.setString(3, a.getNomeArquivo());
                stmt.setString(4, a.getDescricao());
                ExecutarSql.update(stmt);
            }
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public void gravarListagemAssuntos(Integer codigoCurso, List<Assunto> listagem) {
        try {
            /*Deleta a listagem*/
            PreparedStatement stmtDelete = conexaoDb.prepareStatement("DELETE FROM CURSOS_ASSUNTOS WHERE CODIGO_CURSO=?");
            stmtDelete.setInt(1, codigoCurso);
            ExecutarSql.update(stmtDelete);
            
            /*Grava novamente*/
            for(Assunto a : listagem) {
                PreparedStatement stmt = conexaoDb.prepareStatement("INSERT INTO CURSOS_ASSUNTOS "
                                                                 + "(CODIGO_CURSO, SEQUENCIAL, ASSUNTO, CARGA_HORARIA_MINUTOS)"
                                                                 + "VALUES "
                                                                 + "(?,?,?,?)");
                stmt.setInt(1, codigoCurso);
                stmt.setInt(2, getProximoSequencialAssunto(codigoCurso));
                stmt.setString(3, a.getAssunto());
                stmt.setInt(4, a.getCargaHoraria());
                ExecutarSql.update(stmt);
            }
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public void atualizar(Curso curso) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE CURSOS "
                                                              + "SET NOME=?, VALOR_BASE=?, CARGA_HORARIA=?, CODIGO_AREA=? "
                                                              + "WHERE CODIGO=?");
            stmt.setString(1, curso.getNome());
            stmt.setFloat(2, 0);
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getArea().getCodigo());
            stmt.setInt(5, curso.getCodigo());
            ExecutarSql.update(stmt);
            
            gravarListagemApostilas(curso, curso.getListagemApostilas());
            gravarListagemAssuntos(curso.getCodigo(), curso.getListagemAssuntos());
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public void delete(Curso curso) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("DELETE FROM CURSOS "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, curso.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public Curso carregar(Integer codigo) {
        Curso c = null;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM CURSOS "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                c = new Curso();
                c.setCodigo(codigo);
                c.setNome(rs.getString("NOME"));
                c.setValorBase(rs.getFloat("VALOR_BASE"));
                c.setCargaHoraria(rs.getInt("CARGA_HORARIA"));
                c.setListagemApostilas(listagemApostilas(c));
                c.setListagemAssuntos(listagemAssuntos(codigo));
                c.setArea(new AreaController().carregar(rs.getInt("CODIGO_AREA")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return c;
    }

    @Override
    public List<Curso> listagem() {
        List<Curso> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM CURSOS "
                                                              + "ORDER BY NOME");
            ResultSet rs = ExecutarSql.consulta(stmt);
            while(rs.next()) {
                listagem.add(carregar(rs.getInt("CODIGO")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return listagem;
    }
    
    public List<Curso> listagem(String nomeCurso) {
        List<Curso> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM CURSOS "
                                                              + "WHERE NOME LIKE ('%" + nomeCurso + "%')"
                                                              + "ORDER BY NOME");
            ResultSet rs = ExecutarSql.consulta(stmt);
            while(rs.next()) {
                listagem.add(carregar(rs.getInt("CODIGO")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return listagem;
    }
    
    public List<Apostila> listagemApostilas(Curso curso) {
        List<Apostila> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM CURSOS_APOSTILAS "
                                                              + "WHERE CODIGO_CURSO=? "
                                                              + "ORDER BY SEQUENCIAL");
            stmt.setInt(1, curso.getCodigo());
            ResultSet rs = ExecutarSql.consulta(stmt);
            while(rs.next()) {
                Apostila a = new Apostila();
                a.setDescricao(rs.getString("DESCRICAO"));
                a.setNomeArquivo(rs.getString("ARQUIVO"));
                a.setCaminhoCompleto(curso.getDiretorioCurso() + rs.getString("ARQUIVO"));
                listagem.add(a);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return listagem;
    }
    
    public List<Assunto> listagemAssuntos(Integer codigoCurso) {
        List<Assunto> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM CURSOS_ASSUNTOS "
                                                              + "WHERE CODIGO_CURSO=? "
                                                              + "ORDER BY SEQUENCIAL");
            stmt.setInt(1, codigoCurso);
            ResultSet rs = ExecutarSql.consulta(stmt);
            while(rs.next()) {
                Assunto a = new Assunto();
                a.setAssunto(rs.getString("ASSUNTO"));
                a.setCargaHoraria(rs.getInt("CARGA_HORARIA_MINUTOS"));
                listagem.add(a);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return listagem;
    }
    
    public int getProximoSequencialApostila(Integer codigoCurso) {
        int seq = 1;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT (MAX(SEQUENCIAL)+1) AS SEQ "
                                                              + "FROM CURSOS_APOSTILAS "
                                                              + "WHERE CODIGO_CURSO=?");
            stmt.setInt(1, codigoCurso);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                if(rs.getInt("SEQ") != 0) {
                    seq = rs.getInt("SEQ");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return seq;
    }
    
    public int getProximoSequencialAssunto(Integer codigoCurso) {
        int seq = 1;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT (MAX(SEQUENCIAL)+1) AS SEQ "
                                                              + "FROM CURSOS_ASSUNTOS "
                                                              + "WHERE CODIGO_CURSO=?");
            stmt.setInt(1, codigoCurso);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                if(rs.getInt("SEQ") != 0) {
                    seq = rs.getInt("SEQ");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return seq;
    }

}
