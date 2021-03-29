/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOFuncionario;
import ModelFuncionario.Funcionario;
import ConnectionFactory.ConnectionFuncionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class FuncionarioDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public FuncionarioDAO(){
        this.con = new ConnectionFuncionario().getConnectionFuncionario();
    }
    
    public List<Funcionario> getFuncionario(String cpf){
        List<Funcionario> funcionario = new ArrayList<>();
        String sql = "CALL pBuscarFuncionarioPorCPF('"+ cpf +"');";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setID_EnderecoFuncionario(rs.getInt("ID_EnderecoFuncionario"));
                f.setID_Departamento(rs.getInt("ID_Departamento"));
                f.setCPF(rs.getString("CPF"));
                f.setNome(rs.getString("Nome"));
                f.setSobrenome(rs.getString("Sobrenome"));
                f.setDataNascimento(rs.getString("Data_Nascimento"));
                f.setSexo(rs.getString("Sexo"));
                f.setTelefone(rs.getString("Telefone"));;
                f.setSalario(rs.getDouble("Salario"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setBairro(rs.getString("Bairro"));
                f.setCEP(rs.getString("CEP"));
                f.setUF(rs.getString("UF"));
                f.setSenha(rs.getString("Senha"));
                f.setDepartamento(rs.getString("NomeDepartamento"));
                
                funcionario.add(f);
            }
            
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();   
            return null;
        }finally {
            ConnectionFuncionario.closeConnection(con, stmt, rs);
        }
        return funcionario;
    }
    
    public boolean atualizarTaxaJuros(double juros){
        String sql = "ALTER TABLE Emprestimo MODIFY COLUMN Juros DOUBLE DEFAULT '"+juros+"';";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionFuncionario.closeConnection(con, stmt);
        }
    }
    
    public List<Funcionario> getJoinFuncionarios(int id){
        List<Funcionario> join = new ArrayList<>();
        String sql = "CALL pInnerJoinFuncionario(" + id +")";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario e = new Funcionario();
                e.setID_EnderecoFuncionario(rs.getInt("ID_EnderecoFuncionario"));
                e.setID_Departamento(rs.getInt("ID_Departamento"));
                e.setCPF(rs.getString("CPF"));
                e.setNome(rs.getString("Nome"));
                e.setSobrenome(rs.getString("Sobrenome"));
                e.setDataNascimento(rs.getString("Data_Nascimento"));
                e.setSexo(rs.getString("Sexo"));
                e.setTelefone(rs.getString("Telefone"));;
                e.setSalario(rs.getDouble("Salario"));
                e.setLogradouro(rs.getString("Logradouro"));
                e.setBairro(rs.getString("Bairro"));
                e.setCEP(rs.getString("CEP"));
                e.setUF(rs.getString("UF"));
                e.setSenha(rs.getString("Senha"));
                e.setDepartamento(rs.getString("NomeDepartamento"));
                
                join.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionFuncionario.closeConnection(con, stmt, rs);
        }
        return join;
    }
}
