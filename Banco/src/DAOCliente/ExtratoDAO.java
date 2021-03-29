/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOCliente;

import ConnectionFactory.ConnectionCliente;
import ModelCliente.Extrato;
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
public class ExtratoDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public ExtratoDAO(){
        this.con = new ConnectionCliente().getConnectionCliente();
    }
    
    public List<Extrato> getSomaExtrato(int idConta){
        List<Extrato> extrato = new ArrayList<>();
        String sql = "SELECT * FROM vExtrato WHERE idConta = " + idConta + ";";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Extrato e = new Extrato();
                e.setID_Conta(rs.getInt("idConta"));
                e.setEntrada(rs.getDouble("totalEntrada"));
                e.setSaida(rs.getDouble("totalSaida"));
                
                extrato.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return extrato;
    }
    
    public List<Extrato> getList(int idConta){
        List<Extrato> extrato = new ArrayList<>();
        String sql = "SELECT * FROM Extrato WHERE ID_Conta = " + idConta + ";";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Extrato e = new Extrato();
                e.setID_Extrato(rs.getInt("ID_Extrato"));
                e.setID_Conta(rs.getInt("ID_conta"));
                e.setData(rs.getString("Data"));
                e.setDescricao(rs.getString("Descricao"));
                e.setEntrada(rs.getDouble("Entrada"));
                e.setSaida(rs.getDouble("Saida"));
                e.setSaldo(rs.getDouble("Saldo"));
                
                extrato.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return extrato;
    }
    
    public boolean gerarCSV(int id, String nome, String sobrenome){
        String sql = "CALL pBackupExtrato('" + id + "', '" + nome + "', '"+ sobrenome + "');";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
}
