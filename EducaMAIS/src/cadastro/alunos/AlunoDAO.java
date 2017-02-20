package cadastro.alunos;

import dao.ExecutarSql;
import dao.GenericSimpleDAO;
import entidades.Aluno;
import entidades.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.TrataException;

public class AlunoDAO extends GenericSimpleDAO<Aluno, Integer> {
    
    @Override
    public void persist(Aluno aluno) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("INSERT INTO ALUNOS "
                                                             + "(NOME, EMAIL, CPF, RG, RUA, BAIRRO, CIDADE, NUMERO, TELEFONE)"
                                                             + "VALUES "
                                                             + "(?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getCpf());
            stmt.setString(4, aluno.getRg());
            stmt.setString(5, aluno.getRua());
            stmt.setString(6, aluno.getBairro());
            stmt.setString(7, aluno.getCidade());
            stmt.setInt(8, aluno.getNumero());
            stmt.setString(9, aluno.getTelefone());
            ExecutarSql.update(stmt);
            
            Integer codigo = ExecutarSql.getAutoIncrementIdGeradoPeloInsert(stmt);
            
            aluno.setCodigo(codigo);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE ALUNOS "
                                                              + "SET NOME=?, EMAIL=?, CPF=?, RG=?, RUA=?, BAIRRO=?, CIDADE=?, NUMERO=?, TELEFONE=? "
                                                              + "WHERE CODIGO=?");
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getCpf());
            stmt.setString(4, aluno.getRg());
            stmt.setString(5, aluno.getRua());
            stmt.setString(6, aluno.getBairro());
            stmt.setString(7, aluno.getCidade());
            stmt.setInt(8, aluno.getNumero());
            stmt.setString(9, aluno.getTelefone());
            stmt.setInt(10, aluno.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public void delete(Aluno aluno) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("DELETE FROM ALUNOS "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, aluno.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public Aluno carregar(Integer codigo) {
        Aluno a = null;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM ALUNOS "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                a = new Aluno();
                a.setCodigo(codigo);
                a.setNome(rs.getString("NOME"));
                a.setEmail(rs.getString("EMAIL"));
                a.setCpf(rs.getString("CPF"));
                a.setRg(rs.getString("RG"));
                a.setRua(rs.getString("RUA"));
                a.setBairro(rs.getString("BAIRRO"));
                a.setCidade(rs.getString("CIDADE"));
                a.setNumero(rs.getInt("NUMERO"));
                a.setTelefone(rs.getString("TELEFONE"));
                
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return a;
    }
    
    public Aluno carregarByRG(String rg) {
        Aluno a = null;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM ALUNOS "
                                                              + "WHERE RG=?");
            stmt.setString(1, rg);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                a = carregar(rs.getInt("CODIGO"));
                
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return a;
    }

    @Override
    public List<Aluno> listagem() {
        List<Aluno> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM ALUNOS "
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
    
    public List<Aluno> listagem(String nome) {
        List<Aluno> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM ALUNOS "
                                                              + "WHERE NOME LIKE ('%" + nome + "%')"
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

}
