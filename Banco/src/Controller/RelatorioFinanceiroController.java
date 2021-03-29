/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOFuncionario.AgenciaDAO;
import DAOFuncionario.FuncionarioDAO;
import DAOFuncionario.RelatorioFinanceiroDAO;
import ModelFuncionario.Agencia;
import ModelCliente.RelatorioFinanceiro;
import ModelFuncionario.Funcionario;
import ModelFuncionario.SessaoFuncionario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class RelatorioFinanceiroController implements Initializable {

    @FXML
    private TableView<RelatorioFinanceiro> tableRelatorio;
    @FXML
    private TableColumn<RelatorioFinanceiro, String> clmData;
    @FXML
    private TableColumn<RelatorioFinanceiro, String> clmDescricao;
    @FXML
    private TableColumn<RelatorioFinanceiro, String> clmEntrada;
    @FXML
    private TableColumn<RelatorioFinanceiro, String> clmSaida;
    @FXML
    private TableColumn<RelatorioFinanceiro, String> clmSaldo;
    @FXML
    private Label lbEntrada;
    @FXML
    private Label lbSaida;
    @FXML
    private Label lbSaldo;
    
    SessaoFuncionario sessao = SessaoFuncionario.getInstancia();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherRelatorio();
        somaExtrato();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    preencherRelatorio();
                    somaExtrato();
                });
            }
        }, 1000, 1000);
    }    

    @FXML
    private void gerarCSV(ActionEvent event) {
        RelatorioFinanceiroDAO daoR = new RelatorioFinanceiroDAO();
        daoR.gerarCSV(sessao.getID_Departamento());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Banco Digital");
        alert.setHeaderText("O Relatório foi exportado.");
        alert.setContentText("Verifique sua área de trabalho.");
        alert.showAndWait();
    }
    
    private void preencherRelatorio(){
        clmData.setCellValueFactory(new PropertyValueFactory<>("dataFormatada"));
        clmDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        clmEntrada.setCellValueFactory(new PropertyValueFactory<>("entradaFormatada"));
        clmSaida.setCellValueFactory(new PropertyValueFactory<>("saidaFormatada"));
        clmSaldo.setCellValueFactory(new PropertyValueFactory<>("saldoFormatado"));
        
        tableRelatorio.setItems(relatorio());
    }
    
    public ObservableList<RelatorioFinanceiro> relatorio(){
        FuncionarioDAO daoF = new FuncionarioDAO();
        List<Funcionario> f = daoF.getFuncionario(sessao.getCPF());
        
        RelatorioFinanceiroDAO daoR = new RelatorioFinanceiroDAO();
        return FXCollections.observableArrayList(daoR.getList(sessao.getID_Departamento()));
    }
    
    private void somaExtrato(){
        AgenciaDAO daoC = new AgenciaDAO();
        List<Agencia> a = daoC.getSaldoAgencia();
        
        RelatorioFinanceiroDAO daoR = new RelatorioFinanceiroDAO();
        List<RelatorioFinanceiro> relatorio = daoR.getSomaRelatorio(sessao.getID_Departamento());
        
        try {
            if(relatorio.isEmpty() == false){
                lbEntrada.setText(relatorio.get(0).getEntradaFormatada());
                lbSaida.setText(relatorio.get(0).getSaidaFormatada());
                lbSaldo.setText(a.get(0).getSaldoFormatado());
            }else {
                lbEntrada.setText("R$ 0,00");
                lbSaida.setText("R$ 0,00");
                lbSaldo.setText(a.get(0).getSaldoFormatado());
            }
        } catch (Exception e) {
        }
    }
    
}
