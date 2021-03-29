/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOCliente;
import ConnectionFactory.ConnectionCliente;
import ModelCliente.ClienteC;
import ModelCliente.Conta;
import ModelCliente.Endereco;
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
public class ClienteDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public ClienteDAO(){
        this.con = new ConnectionCliente().getConnectionCliente();
    }
    
    public boolean cadastrar(ClienteC cl, Endereco e, Conta co){
        String sql = "CALL ptCadastro(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, cl.getNome());
            stmt.setString(2, cl.getSobrenome());
            stmt.setString(3, cl.getCPF());
            stmt.setString(4, cl.getDataNascimento());
            stmt.setString(5, cl.getRenda());
            stmt.setString(6, cl.getProfissao());
            stmt.setString(7, cl.getSexo());
            stmt.setString(8, cl.getTelefone());
            stmt.setString(9, cl.getSenha());
            stmt.setString(10, e.getLogradouro());
            stmt.setString(11, e.getBairro());
            stmt.setString(12, e.getCEP());
            stmt.setString(13, e.getUF());
            stmt.setString(14, co.getConta());
            stmt.setString(15, co.getDigito());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
    
    public List<ClienteC> getUsuario(String cpf){
        List<ClienteC> cliente = new ArrayList<>();
        String sql = "SELECT * FROM Cliente WHERE CPF = '"+ cpf +"';";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ClienteC c = new ClienteC();
                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setCPF(rs.getString("CPF"));
                c.setDataNascimento(rs.getString("Data_Nascimento"));
                c.setNome(rs.getString("Nome"));
                c.setProfissao(rs.getString("Profissao"));
                c.setRenda(rs.getString("Renda"));
                c.setSenha(rs.getString("Senha"));
                c.setSexo(rs.getString("Sexo"));
                c.setSobrenome(rs.getString("Sobrenome"));
                c.setTelefone(rs.getString("Telefone"));
                
                cliente.add(c);
            }
            
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();   
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return cliente;
    }
    
    public List<ClienteC> getLista(){
        List<ClienteC> cliente = new ArrayList<>();
        String sql = "SELECT * FROM Cliente;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ClienteC c = new ClienteC();
                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setCPF(rs.getString("CPF"));
                c.setDataNascimento(rs.getString("Data_Nascimento"));
                c.setNome(rs.getString("Nome"));
                c.setProfissao(rs.getString("Profissao"));
                c.setRenda(rs.getString("Renda"));
                c.setSenha(rs.getString("Senha"));
                c.setSexo(rs.getString("Sexo"));
                c.setSobrenome(rs.getString("Sobrenome"));
                c.setTelefone(rs.getString("Telefone"));
                
                cliente.add(c);
            }
            
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();   
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return cliente;
    }
    
    public boolean update(ClienteC c){
        String sql = "CALL pUpdateCliente(?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, c.getID_Cliente());
            stmt.setString(2, c.getRenda());
            stmt.setString(3, c.getProfissao());
            stmt.setString(4, c.getTelefone());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
    
    public boolean delete(ClienteC c){
        String sql = "CALL pDeletarConta(?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, c.getID_Cliente());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
}
