/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOFuncionario;

import ConnectionFactory.ConnectionFuncionario;
import DAOCliente.ClienteDAO;
import ModelCliente.RelatorioFinanceiro;
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
public class RelatorioFinanceiroDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public RelatorioFinanceiroDAO(){
        this.con = new ConnectionFuncionario().getConnectionFuncionario();
    }
    
    public List<RelatorioFinanceiro> getSomaRelatorio(int idD){
        List<RelatorioFinanceiro> relatorio = new ArrayList<>();
        String sql = "SELECT * FROM vRelatorioFinanceiro WHERE ID_Departamento = " + idD + ";";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                RelatorioFinanceiro r = new RelatorioFinanceiro();
                r.setID_Departamento(rs.getInt("ID_Departamento"));
                r.setEntrada(rs.getDouble("totalEntrada"));
                r.setSaida(rs.getDouble("totalSaida"));
                
                relatorio.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionFuncionario.closeConnection(con, stmt, rs);
        }
        return relatorio;
    }
    
    public List<RelatorioFinanceiro> getList(int idD){
        List<RelatorioFinanceiro> relatorio = new ArrayList<>();
        String sql = "SELECT * FROM Relatorio_Financeiro WHERE ID_Departamento = " + idD + ";";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                RelatorioFinanceiro r = new RelatorioFinanceiro();
                r.setID_Departamento(rs.getInt("ID_Departamento"));
                r.setID_RelatorioFinanceiro(rs.getInt("ID_RelatorioFinanceiro"));
                r.setData(rs.getString("Data"));
                r.setDescricao(rs.getString("Descricao"));
                r.setEntrada(rs.getDouble("Entrada"));
                r.setSaida(rs.getDouble("Saida"));
                r.setSaldo(rs.getDouble("Saldo"));
                
                relatorio.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionFuncionario.closeConnection(con, stmt, rs);
        }
        return relatorio;
    }
    
    public boolean gerarCSV(int idD){
        String sql = "CALL pBackupRelatorio('" + idD +"');";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
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
