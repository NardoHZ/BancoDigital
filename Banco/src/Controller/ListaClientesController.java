/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOFuncionario.ClienteDAOF;
import ModelFuncionario.ClienteF;
import Recursos.TextFieldFormatter;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class ListaClientesController implements Initializable {

    @FXML
    private Label lbmostrarNome;
    @FXML
    private Label lbMostrarSobrenome;
    @FXML
    private Label lbMostrarCPF;
    @FXML
    private Label lbMostrarDataNascimento;
    @FXML
    private Label lbMostrarRenda;
    @FXML
    private Label lbMostrarProfissao;
    @FXML
    private Label lbMostrarTelefone;
    @FXML
    private Label lbMostrarLogradouro;
    @FXML
    private Label lbMostrarBairro;
    @FXML
    private Label lbMostrarCEP;
    @FXML
    private Label lbMostrarUF;
    @FXML
    private TableView<ClienteF> tableClientes;
    @FXML
    private TableColumn<ClienteF, String> clmNome;
    @FXML
    private TableColumn<ClienteF, String> clmSobrenome;
    @FXML
    private TableColumn<ClienteF, String> clmCPF;
    @FXML
    private JFXTextField tfFiltroCPF;
    
    ClienteF selecionandoCliente;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabelaClientes();
        
        tableClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(
        ) {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionandoCliente = (ClienteF) newValue;
                
                if(!selecionandoCliente.equals("")){
                    detalhesCliente();
                }
            }
        });
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                   if(tableClientes.getSelectionModel().isEmpty() == false){
                       detalhesCliente();
                   }else {
                       if(tfFiltroCPF.getText().length() == 0){
                           verificarQtdClientes();
                       }
                   }
                });
            }
        }, 1000, 1000);
    }    
    
    //Verificar se a quantidade de registros da tabela é menor
    //que a quantidade mais recente
    private void verificarQtdClientes(){
        ClienteDAOF daoC = new ClienteDAOF();
        List<ClienteF> c = daoC.getJoinClientes();
        
        if(c.size() != tableClientes.getItems().size()){
            preencherTabelaClientes();
        }
    }
    
    private void detalhesCliente(){
        
        ClienteDAOF daoC = new ClienteDAOF();
        List<ClienteF> c = daoC.buscarCPF(selecionandoCliente.getCPF());
        
        lbmostrarNome.setText(c.get(0).getNome());
        lbMostrarSobrenome.setText(c.get(0).getSobrenome());
        lbMostrarCPF.setText(c.get(0).getCPF());
        lbMostrarDataNascimento.setText(c.get(0).getDataNascimentoFormatada());
        lbMostrarRenda.setText(c.get(0).getRenda());
        lbMostrarProfissao.setText(c.get(0).getProfissao());
        lbMostrarTelefone.setText(c.get(0).getTelefone());
        lbMostrarLogradouro.setText(c.get(0).getLogradouro());
        lbMostrarBairro.setText(c.get(0).getBairro());
        lbMostrarCEP.setText(c.get(0).getCEP());
        lbMostrarUF.setText(c.get(0).getUF());
    }
    
    private void preencherTabelaClientes(){
        clmNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        clmSobrenome.setCellValueFactory(new PropertyValueFactory<>("Sobrenome"));
        clmCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        tableClientes.setItems(getClientes());
    }
    
    private ObservableList<ClienteF> getClientes(){
        ClienteDAOF daoI = new ClienteDAOF();
        return FXCollections.observableArrayList(daoI.getJoinClientes());
    }
    
    private void preencherTabelaClientesCPF(){
        clmNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        clmSobrenome.setCellValueFactory(new PropertyValueFactory<>("Sobrenome"));
        clmCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        tableClientes.setItems(getClientesCPF());
    }
    
    private ObservableList<ClienteF> getClientesCPF(){
        ClienteDAOF daoI = new ClienteDAOF();
        return FXCollections.observableArrayList(daoI.buscarCPF(tfFiltroCPF.getText()));
    }

    @FXML
    private void filtroCPF(KeyEvent event) {
        formatCPF();
        if(tfFiltroCPF.getText().length() > 0){
            preencherTabelaClientesCPF();
        }else {
            preencherTabelaClientes();
        }
    }
    
    private void formatCPF(){
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("###.###.###-##");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfFiltroCPF);
        formatar.formatter();
    }
}
