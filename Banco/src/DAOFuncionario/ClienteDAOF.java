/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOFuncionario;

import ConnectionFactory.ConnectionFuncionario;
import DAOCliente.ClienteDAO;
import ModelFuncionario.ClienteF;
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
public class ClienteDAOF {
    
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public ClienteDAOF(){
        this.con = new ConnectionFuncionario().getConnectionFuncionario();
    }
    
    public List<ClienteF> getJoinEmprestimo(){
        List<ClienteF> join = new ArrayList<>();
        String sql = "CALL pInnerJoinEmprestimo()";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ClienteF e = new ClienteF();
                e.setID_Emprestimo(rs.getInt("ID_Emprestimo"));
                e.setID_Cliente(rs.getInt("ID_Cliente"));
                e.setID_Conta(rs.getInt("ID_Conta"));
                e.setID_Endereco(rs.getInt("ID_EnderecoCliente"));
                e.setValorEmprestimo(rs.getDouble("Valor_Emprestimo"));
                e.setValorParcelas(rs.getDouble("Valor_Parcelas"));
                e.setDividaTotal(rs.getDouble("Divida_Total"));
                e.setDividaParcial(rs.getDouble("Divida_Parcial"));
                e.setJuros(rs.getDouble("Juros"));
                e.setParcelasTotal(rs.getInt("Parcelas_Total"));
                e.setParcelasParcial(rs.getInt("Parcelas_Parcial"));
                e.setStatus(rs.getString("Status"));
                e.setCPF(rs.getString("CPF"));
                e.setNome(rs.getString("Nome"));
                e.setSobrenome(rs.getString("Sobrenome"));
                e.setDataNascimento(rs.getString("Data_Nascimento"));
                e.setRenda(rs.getString("Renda"));
                e.setProfissao(rs.getString("Profissao"));
                e.setSexo(rs.getString("Sexo"));
                e.setTelefone(rs.getString("Telefone"));
                e.setAgencia(rs.getString("Agencia"));
                e.setConta(rs.getString("Conta"));
                e.setDigito(rs.getString("Digito"));
                e.setSaldo(rs.getDouble("Saldo"));
                e.setLogradouro(rs.getString("Logradouro"));
                e.setBairro(rs.getString("Bairro"));
                e.setCEP(rs.getString("CEP"));
                e.setUF(rs.getString("UF"));
                
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
    
    public List<ClienteF> getJoinEmprestimoPorID(int id){
        List<ClienteF> join = new ArrayList<>();
        String sql = "CALL pInnerJoinEmprestimoPorID('"+id+"')";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ClienteF e = new ClienteF();
                e.setID_Emprestimo(rs.getInt("ID_Emprestimo"));
                e.setID_Cliente(rs.getInt("ID_Cliente"));
                e.setID_Conta(rs.getInt("ID_Conta"));
                e.setID_Endereco(rs.getInt("ID_EnderecoCliente"));
                e.setValorEmprestimo(rs.getDouble("Valor_Emprestimo"));
                e.setValorParcelas(rs.getDouble("Valor_Parcelas"));
                e.setDividaTotal(rs.getDouble("Divida_Total"));
                e.setDividaParcial(rs.getDouble("Divida_Parcial"));
                e.setJuros(rs.getDouble("Juros"));
                e.setParcelasTotal(rs.getInt("Parcelas_Total"));
                e.setParcelasParcial(rs.getInt("Parcelas_Parcial"));
                e.setStatus(rs.getString("Status"));
                e.setCPF(rs.getString("CPF"));
                e.setNome(rs.getString("Nome"));
                e.setSobrenome(rs.getString("Sobrenome"));
                e.setDataNascimento(rs.getString("Data_Nascimento"));
                e.setRenda(rs.getString("Renda"));
                e.setProfissao(rs.getString("Profissao"));
                e.setSexo(rs.getString("Sexo"));
                e.setTelefone(rs.getString("Telefone"));
                e.setAgencia(rs.getString("Agencia"));
                e.setConta(rs.getString("Conta"));
                e.setDigito(rs.getString("Digito"));
                e.setSaldo(rs.getDouble("Saldo"));
                e.setLogradouro(rs.getString("Logradouro"));
                e.setBairro(rs.getString("Bairro"));
                e.setCEP(rs.getString("CEP"));
                e.setUF(rs.getString("UF"));
                
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
    
    public List<ClienteF> getJoinClientes(){
        List<ClienteF> join = new ArrayList<>();
        String sql = "CALL pInnerJoinClientes()";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ClienteF e = new ClienteF();
                e.setID_Cliente(rs.getInt("ID_Cliente"));
                e.setID_Conta(rs.getInt("ID_Conta"));
                e.setID_Endereco(rs.getInt("ID_EnderecoCliente"));
                e.setCPF(rs.getString("CPF"));
                e.setNome(rs.getString("Nome"));
                e.setSobrenome(rs.getString("Sobrenome"));
                e.setDataNascimento(rs.getString("Data_Nascimento"));
                e.setRenda(rs.getString("Renda"));
                e.setProfissao(rs.getString("Profissao"));
                e.setSexo(rs.getString("Sexo"));
                e.setTelefone(rs.getString("Telefone"));
                e.setAgencia(rs.getString("Agencia"));
                e.setConta(rs.getString("Conta"));
                e.setDigito(rs.getString("Digito"));
                e.setSaldo(rs.getDouble("Saldo"));
                e.setLogradouro(rs.getString("Logradouro"));
                e.setBairro(rs.getString("Bairro"));
                e.setCEP(rs.getString("CEP"));
                e.setUF(rs.getString("UF"));
                
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
    
    public List<ClienteF> buscarCPF(String cpf){
        List<ClienteF> join = new ArrayList<>();
        String sql = "CALL pBuscarClientePorCPF('"+cpf+"')";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ClienteF e = new ClienteF();
                e.setID_Cliente(rs.getInt("ID_Cliente"));
                e.setID_Conta(rs.getInt("ID_Conta"));
                e.setID_Endereco(rs.getInt("ID_EnderecoCliente"));
                e.setCPF(rs.getString("CPF"));
                e.setNome(rs.getString("Nome"));
                e.setSobrenome(rs.getString("Sobrenome"));
                e.setDataNascimento(rs.getString("Data_Nascimento"));
                e.setRenda(rs.getString("Renda"));
                e.setProfissao(rs.getString("Profissao"));
                e.setSexo(rs.getString("Sexo"));
                e.setTelefone(rs.getString("Telefone"));
                e.setAgencia(rs.getString("Agencia"));
                e.setConta(rs.getString("Conta"));
                e.setDigito(rs.getString("Digito"));
                e.setSaldo(rs.getDouble("Saldo"));
                e.setLogradouro(rs.getString("Logradouro"));
                e.setBairro(rs.getString("Bairro"));
                e.setCEP(rs.getString("CEP"));
                e.setUF(rs.getString("UF"));
                
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
    
    public boolean negarEmprestimo(ClienteF j){
        String sql = "CALL pNegarEmprestimo(?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, j.getID_Emprestimo());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            ConnectionFuncionario.closeConnection(con, stmt);
        }
    }
    
    public boolean aprovarEmprestimo(ClienteF j){
        String sql = "CALL ptAprovarEmprestimo(?, ?, ?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, j.getID_Emprestimo());
            stmt.setDouble(2, j.getValorEmprestimo());
            stmt.setInt(3, j.getID_Conta());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            ConnectionFuncionario.closeConnection(con, stmt);
        }
    }
}
