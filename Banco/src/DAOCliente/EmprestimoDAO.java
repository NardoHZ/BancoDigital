/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOCliente;

import ConnectionFactory.ConnectionCliente;
import ModelCliente.Emprestimo;
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
public class EmprestimoDAO {
    
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public EmprestimoDAO(){
        this.con = new ConnectionCliente().getConnectionCliente();
    }
    
    public boolean insert(Emprestimo e){
        String sql = "CALL pSolicitarEmprestimo(?, ?, ?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, e.getID_Cliente());
            stmt.setDouble(2, e.getValorEmprestimo());
            stmt.setInt(3, e.getParcelasTotal());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
    
    public boolean cancelarEmprestimo(Emprestimo e){
        String sql = "CALL pCancelarSolicitacaoEmprestimo(?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, e.getID_Emprestimo());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
    
    public List<Emprestimo> getTaxaJuros( ){
        List<Emprestimo> emprestimo = new ArrayList<>();
        String sql = "CALL pIdentificaTaxaJuros();";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Emprestimo e = new Emprestimo();
                e.setJuros(rs.getDouble("taxaJuros"));
                
                emprestimo.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return emprestimo;
    }
    
    public List<Emprestimo> getEmprestimo(int idCliente){
        List<Emprestimo> emprestimo = new ArrayList<>();
        String sql = "CALL pVerificarEmprestimoExistente("+idCliente+");";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Emprestimo e = new Emprestimo();
                e.setID_Emprestimo(rs.getInt("ID_Emprestimo"));
                e.setID_Cliente(rs.getInt("ID_Cliente"));
                e.setValorEmprestimo(rs.getDouble("Valor_Emprestimo"));
                e.setValorParcelas(rs.getDouble("Valor_Parcelas"));
                e.setDividaTotal(rs.getDouble("Divida_Total"));
                e.setDividaParcial(rs.getDouble("Divida_Parcial"));
                e.setJuros(rs.getDouble("Juros"));
                e.setParcelasTotal(rs.getInt("Parcelas_Total"));
                e.setParcelasParcial(rs.getInt("Parcelas_Parcial"));
                e.setStatus(rs.getString("Status"));
                
                emprestimo.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return emprestimo;
    }
}
