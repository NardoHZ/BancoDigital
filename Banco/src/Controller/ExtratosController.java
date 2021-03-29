/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOCliente.ContaDAO;
import DAOCliente.ExtratoDAO;
import ModelCliente.Conta;
import ModelCliente.Extrato;
import ModelCliente.SessaoCliente;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class ExtratosController implements Initializable {

    @FXML
    private TableView<Extrato> tableExtrato;
    @FXML
    private TableColumn<Extrato, String> clmData;
    @FXML
    private TableColumn<Extrato, String> clmDescricao;
    @FXML
    private TableColumn<Extrato, String> clmEntrada;
    @FXML
    private TableColumn<Extrato, String> clmSaida;
    @FXML
    private TableColumn<Extrato, String> clmSaldo;
    @FXML
    private Label lbEntrada;
    @FXML
    private Label lbSaida;
    @FXML
    private Label lbSaldo;
    
    SessaoCliente sessao = SessaoCliente.getInstancia();
    private Hyperlink linkDetalhes;
    
    Extrato selecionandoExtrato;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherExtrato();
        somaExtrato();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    preencherExtrato();
                    somaExtrato();
                });
            }
        }, 1000, 1000);
    }
    
    private void preencherExtrato(){
        clmData.setCellValueFactory(new PropertyValueFactory<>("dataFormatada"));
        clmDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        clmEntrada.setCellValueFactory(new PropertyValueFactory<>("entradaFormatada"));
        clmSaida.setCellValueFactory(new PropertyValueFactory<>("saidaFormatada"));
        clmSaldo.setCellValueFactory(new PropertyValueFactory<>("saldoFormatado"));
        
        tableExtrato.setItems(extrato());
    }
    
    public ObservableList<Extrato> extrato(){
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
        
        ExtratoDAO daoE = new ExtratoDAO();
        return FXCollections.observableArrayList(daoE.getList(conta.get(0).getID_Conta()));
    }
    
    private void somaExtrato(){
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
        
        ExtratoDAO daoE = new ExtratoDAO();
        List<Extrato> extrato = daoE.getSomaExtrato(conta.get(0).getID_Conta());
        
        try {
            if(extrato.isEmpty() == false){
                lbEntrada.setText(extrato.get(0).getEntradaFormatada());
                lbSaida.setText(extrato.get(0).getSaidaFormatada());
                lbSaldo.setText(conta.get(0).getSaldoFormatado());
            }else {
                lbEntrada.setText("R$ 0,00");
                lbSaida.setText("R$ 0,00");
                lbSaldo.setText(conta.get(0).getSaldoFormatado());
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void gerarCSV(ActionEvent event) {
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
        
        ExtratoDAO daoE = new ExtratoDAO();
        
        daoE.gerarCSV(conta.get(0).getID_Conta(), sessao.getNome(), sessao.getSobrenome());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Banco Digital");
        alert.setHeaderText("O extrato foi exportado.");
        alert.setContentText("Verifique sua área de trabalho.");
        alert.showAndWait();
    }

}
