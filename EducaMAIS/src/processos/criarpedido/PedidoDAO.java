package processos.criarpedido;

import cadastro.alunos.AlunoController;
import cadastro.cursos.CursoController;
import cadastro.vendedor.VendedorController;
import dao.ExecutarSql;
import dao.GenericSimpleDAO;
import entidades.Aluno;
import entidades.Pedido;
import entidades.Vendedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.TrataException;

public class PedidoDAO extends GenericSimpleDAO<Pedido, Integer> {
    
    @Override
    public void persist(Pedido pedido) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("INSERT INTO PEDIDOSS "
                                                             + "(CODIGO_ALUNO, CODIGO_VENDEDOR, CODIGO_CURSO, "
                                                             + "DATA_INICIAL, DATA_FINAL, NOTA, DATA_GRAVACAO, RECEBIDO, ANTIGO) "
                                                             + "VALUES "
                                                             + "(?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pedido.getAluno().getCodigo());
            stmt.setInt(2, pedido.getVendedor().getCodigo());
            stmt.setInt(3, pedido.getCurso().getCodigo());
            stmt.setTimestamp(4, new Timestamp(pedido.getDataInicio().getTime()));
            stmt.setTimestamp(5, new Timestamp(pedido.getDataTermino().getTime()));
            stmt.setDouble(6, pedido.getNota());
            stmt.setTimestamp(7, new Timestamp(new Date().getTime()));
            stmt.setBoolean(8, false);
            stmt.setBoolean(9, false);
            ExecutarSql.update(stmt);
            
            Integer codigo = ExecutarSql.getAutoIncrementIdGeradoPeloInsert(stmt);
            
            pedido.setCodigo(codigo);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public void atualizarCertificadoEmitido(Integer codigoPedido) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE PEDIDOSS "
                                                              + "SET CERTIFICADO_EMITIDO=? "
                                                              + "WHERE CODIGO=?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, codigoPedido);
            ExecutarSql.update(stmt);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public void atualizarCdGravado(Integer codigoPedido) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE PEDIDOSS "
                                                              + "SET CD_GRAVADO=? "
                                                              + "WHERE CODIGO=?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, codigoPedido);
            ExecutarSql.update(stmt);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public void atualizarNumeroRegistro(Integer codigoPedido, Integer numeroRegistro) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE PEDIDOSS "
                                                              + "SET NUMERO_REGISTRO_OFICIAL=? "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, numeroRegistro);
            stmt.setInt(2, codigoPedido);
            ExecutarSql.update(stmt);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public void atualizarDataEmissao(Integer codigoPedido, java.sql.Date dataEmissao) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE PEDIDOSS "
                                                              + "SET DATA_EMISSAO=? "
                                                              + "WHERE CODIGO=?");
            stmt.setDate(1, dataEmissao);
            stmt.setInt(2, codigoPedido);
            ExecutarSql.update(stmt);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public void receberCurso(Integer codigoPedido) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE PEDIDOSS "
                                                              + "SET RECEBIDO=? "
                                                              + "WHERE CODIGO=?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, codigoPedido);
            ExecutarSql.update(stmt);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public void cancelarCurso(Integer codigoPedido) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE PEDIDOSS "
                                                              + "SET CANCELADO=? "
                                                              + "WHERE CODIGO=?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, codigoPedido);
            ExecutarSql.update(stmt);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public void delete(Pedido pedido) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("DELETE FROM PEDIDOSS "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, pedido.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public Pedido carregar(Integer codigo) {
        Pedido pedido = null;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM PEDIDOSS "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                pedido = new Pedido();
                pedido.setCodigo(codigo);
                pedido.setAluno(new AlunoController().carregar(rs.getInt("CODIGO_ALUNO")));
                pedido.setCurso(new CursoController().carregar(rs.getInt("CODIGO_CURSO")));
                pedido.setVendedor(new VendedorController().carregar(rs.getInt("CODIGO_VENDEDOR")));
                pedido.setDataInicio(new Date(rs.getTimestamp("DATA_INICIAL").getTime()));
                pedido.setDataTermino(new Date(rs.getTimestamp("DATA_FINAL").getTime()));
                if(rs.getTimestamp("DATA_EMISSAO") != null) {
                    pedido.setDataEmissaoCertificado(new Date(rs.getTimestamp("DATA_EMISSAO").getTime()));
                }
                pedido.setNumeroRegistro(rs.getInt("NUMERO_REGISTRO_OFICIAL"));
                pedido.setNota(rs.getFloat("NOTA"));
                pedido.setCancelado(rs.getBoolean("CANCELADO"));
                if(rs.getTimestamp("DATA_GRAVACAO") != null) {
                    pedido.setDataCadastro(new Date(rs.getTimestamp("DATA_GRAVACAO").getTime()));
                }
                pedido.setAntigo(rs.getBoolean("ANTIGO"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return pedido;
    }

    @Override
    public List<Pedido> listagem() {
        List<Pedido> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM PEDIDOSS "
                                                              + "ORDER BY CODIGO, NUMERO_REGISTRO_OFICIAL ASC");
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
    
    public List<Pedido> listagem(Aluno aluno) {
        List<Pedido> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM PEDIDOSS "
                                                              + "WHERE CODIGO_ALUNO=IF(?=999999,CODIGO_ALUNO,?) "
                                                              + "ORDER BY DATA_GRAVACAO ASC ");
            stmt.setInt(1, aluno.getCodigo());
            stmt.setInt(2, aluno.getCodigo());
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
    
    public List<Pedido> listagem(Vendedor vendedor, Date dataInicio, Date dataFim) {
        List<Pedido> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM PEDIDOSS "
                                                              + "WHERE CODIGO_VENDEDOR= IF(?=99999, CODIGO_VENDEDOR, ?) AND "
                                                              + "     ((DATA_GRAVACAO >= ? AND DATA_GRAVACAO <= ?) OR DATA_GRAVACAO IS NULL) "
                                                              + "ORDER BY DATA_GRAVACAO DESC, DATA_INICIAL ASC");
            stmt.setInt(1, vendedor.getCodigo());
            stmt.setInt(2, vendedor.getCodigo());
            stmt.setTimestamp(3, new Timestamp(dataInicio.getTime()));
            stmt.setTimestamp(4, new Timestamp(dataFim.getTime()));
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
    
    public List<Pedido> listagemRecebimentoPendente(Vendedor vendedor) {
        List<Pedido> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM PEDIDOSS "
                                                              + "WHERE CODIGO_VENDEDOR= IF(?=99999, CODIGO_VENDEDOR, ?) AND "
                                                              + "      RECEBIDO=? "
                                                              + "ORDER BY DATA_GRAVACAO DESC, DATA_INICIAL ASC");
            stmt.setInt(1, vendedor.getCodigo());
            stmt.setInt(2, vendedor.getCodigo());
            stmt.setBoolean(3, false);
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

    @Override
    public void atualizar(Pedido pedido) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE PEDIDOSS SET "
                    + "CODIGO_ALUNO=?, CODIGO_VENDEDOR=?, CODIGO_CURSO=?, "
                    + "DATA_INICIAL=?, DATA_FINAL=?, NOTA=? "
                    + "WHERE CODIGO=?");
            stmt.setInt(1, pedido.getAluno().getCodigo());
            stmt.setInt(2, pedido.getVendedor().getCodigo());
            stmt.setInt(3, pedido.getCurso().getCodigo());
            stmt.setTimestamp(4, new Timestamp(pedido.getDataInicio().getTime()));
            stmt.setTimestamp(5, new Timestamp(pedido.getDataTermino().getTime()));
            stmt.setDouble(6, pedido.getNota());
            stmt.setInt(7, pedido.getCodigo());
            System.out.println(stmt.toString());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    public int getProximoNumeroRegistro() {
        int seq = 1;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT (MAX(NUMERO_REGISTRO_OFICIAL)+1) AS N "
                                                              + "FROM PEDIDOSS ");
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                if(rs.getInt("N") != 0) {
                    seq = rs.getInt("N");
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
