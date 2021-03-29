/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOFuncionario.AgenciaDAO;
import DAOFuncionario.FuncionarioDAO;
import DAOFuncionario.ClienteDAOF;
import ModelFuncionario.Agencia;
import ModelFuncionario.ClienteF;
import ModelFuncionario.SessaoFuncionario;
import Recursos.Criptografia;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class DetalhesEmprestimosController implements Initializable {

    @FXML
    private TableView<ClienteF> tableClientes;
    @FXML
    private TableColumn<ClienteF, String> clmCPF;
    @FXML
    private TableColumn<ClienteF, String> clmValor;
    @FXML
    private TableColumn<ClienteF, String> clmStatus;
    @FXML
    private Label lbmostrarNome;
    @FXML
    private Label lbMostrarSobrenome;
    @FXML
    private Label lbMostrarCPF;
    @FXML
    private Label lbValorParcelas;
    @FXML
    private Label lbNumeroParcelas;
    @FXML
    private Label lbParcelasRestantes;
    @FXML
    private Label lbTaxaJuros;
    @FXML
    private Label lbStatus;
    @FXML
    private JFXButton btAprovar;
    @FXML
    private JFXButton btNegar;
    
    ClienteF selecionandoCliente;
    @FXML
    private Label lbValorEmprestimo;
    @FXML
    private Label lbDividaTotal;
    @FXML
    private Label lbDividaRestante;
    @FXML
    private JFXButton btCopiar;
    @FXML
    private Pane paneConfirmarTransacao;
    @FXML
    private JFXPasswordField tfSenhaTransacao;
    @FXML
    private Pane paneTaxaJuros;
    @FXML
    private JFXPasswordField tfSenhaTaxa;
    @FXML
    private Label lbStatusSenhaTransacao;
    @FXML
    private Label lbStatusSenhaTaxa;
    @FXML
    private JFXTextField tfTaxa;
    
    SessaoFuncionario sessao = SessaoFuncionario.getInstancia();
    
    String result;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabelaEmprestimo();
        
        tableClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(
        ) {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionandoCliente = (ClienteF) newValue;
                
                if(!selecionandoCliente.equals("")){
                    btCopiar.setDisable(false);
                    detalhesCliente();
                    if(selecionandoCliente.getStatus().equals("Aguardando aprovação")){
                        btAprovar.setDisable(false);
                        btNegar.setDisable(false);
                    }else{
                        btAprovar.setDisable(true);
                        btNegar.setDisable(true);
                    }
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
                        preencherTabelaEmprestimo();
                    }
                    
                    verificarQtdEmprestimo();
                });
            }
        }, 1000, 1000);
    }    
    
    //Verificar se a quantidade de registros da tabela é menor
    //que a quantidade mais recente
    private void verificarQtdEmprestimo(){
        ClienteDAOF daoC = new ClienteDAOF();
        List<ClienteF> c = daoC.getJoinEmprestimo();
        
        try {
            if(c.size() != tableClientes.getItems().size()){
                preencherTabelaEmprestimo();
            }
        } catch (Exception e) {
        }
    }
    
    private void detalhesCliente(){
        ClienteDAOF daoC = new ClienteDAOF();
        List<ClienteF> c = daoC.getJoinEmprestimoPorID(selecionandoCliente.getID_Emprestimo());
        
        int juros = (int) (c.get(0).getJuros() * 100);
        
        lbMostrarCPF.setText(c.get(0).getCPF());
        lbMostrarSobrenome.setText(c.get(0).getSobrenome());
        lbmostrarNome.setText(c.get(0).getNome());
        lbNumeroParcelas.setText(c.get(0).getParcelasTotal()+ "");
        lbParcelasRestantes.setText(c.get(0).getParcelasParcial() + "");
        lbStatus.setText(c.get(0).getStatus());
        lbTaxaJuros.setText(juros + "%");
        lbValorParcelas.setText(c.get(0).getValorParcelasFormatado());
        lbValorEmprestimo.setText(c.get(0).getValorEmprestimoFormatado());
        lbDividaTotal.setText(c.get(0).getDividaTotalFormatada());
        lbDividaRestante.setText(c.get(0).getDividaParcialFormatada());
        
    }
    
    private void limparLabels(){
        lbMostrarCPF.setText("");
        lbMostrarSobrenome.setText("");
        lbmostrarNome.setText("");
        lbNumeroParcelas.setText("");
        lbParcelasRestantes.setText("");
        lbStatus.setText("");
        lbTaxaJuros.setText("");
        lbValorParcelas.setText("");
        lbValorEmprestimo.setText("");
        lbDividaTotal.setText("");
        lbDividaRestante.setText("");
        
        btAprovar.setDisable(true);
        btNegar.setDisable(true);
        btCopiar.setDisable(true);
    }

    @FXML
    private void aprovarEmprestimo(ActionEvent event) {
        paneConfirmarTransacao.setVisible(true);
        result = "aprovar";
    }
    
    private void aprovar(){
        ClienteF C = new ClienteF();
        ClienteDAOF daoC = new ClienteDAOF();
        
        C.setID_Emprestimo(selecionandoCliente.getID_Emprestimo());
        C.setValorEmprestimo(selecionandoCliente.getValorEmprestimo());
        C.setID_Conta(selecionandoCliente.getID_Conta());
        
        daoC.aprovarEmprestimo(C);
        
        limparLabels();
        
        try {
            preencherTabelaEmprestimo();
        } catch (Exception e) {
        }
        
    }

    @FXML
    private void negarEmprestimo(ActionEvent event) {
        paneConfirmarTransacao.setVisible(true);
        result = "negar";
        
    }
    
    private void negar(){
        ClienteF C = new ClienteF();
        ClienteDAOF daoC = new ClienteDAOF();
        
        C.setID_Emprestimo(selecionandoCliente.getID_Emprestimo());
        
        daoC.negarEmprestimo(C);
        
        limparLabels();
        
        try {
            preencherTabelaEmprestimo();
        } catch (Exception e) {
        }
    }

    @FXML
    private void alterarTaxaJuros(ActionEvent event) {
        paneTaxaJuros.setVisible(true);
    }

    @FXML
    private void copiarCPF(ActionEvent event) {
        java.awt.datatransfer.Clipboard copiar = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardOwner selecao = new StringSelection(lbMostrarCPF.getText());
        copiar.setContents((Transferable) selecao, selecao);
    }
    
    private void preencherTabelaEmprestimo(){
        clmCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        clmValor.setCellValueFactory(new PropertyValueFactory<>("valorEmprestimoFormatado"));
        tableClientes.setItems(listaClientes());
    }
    
    private ObservableList<ClienteF> listaClientes(){
        ClienteDAOF daoI = new ClienteDAOF();
        return FXCollections.observableArrayList(daoI.getJoinEmprestimo());
    }


    @FXML
    private void cancelarTransacao(ActionEvent event) {
        paneConfirmarTransacao.setVisible(false);
        tfSenhaTransacao.setText("");
        lbStatusSenhaTransacao.setText("");
    }

    @FXML
    private void confirmarTransacao(ActionEvent event) {
        if(sessao.getSenha().equals(Criptografia.MD5(tfSenhaTransacao.getText()))){
            if(result.equals("aprovar")){
                aprovar();
            }else {
                negar();
            }
            paneConfirmarTransacao.setVisible(false);
        }else {
            lbStatusSenhaTransacao.setText("Senha inválida");
            lbStatusSenhaTransacao.setStyle("-fx-text-fill: red;");
            tfSenhaTransacao.setText("");
        }
    }

    @FXML
    private void cancelarTaxa(ActionEvent event) {
        paneTaxaJuros.setVisible(false);
        tfSenhaTaxa.setText("");
        tfTaxa.setText("");
        lbStatusSenhaTaxa.setText("");
    }

    @FXML
    private void confirmarTaxa(ActionEvent event) {
        if(sessao.getSenha().equals(Criptografia.MD5(tfSenhaTaxa.getText()))){
            FuncionarioDAO daoF = new FuncionarioDAO();
            daoF.atualizarTaxaJuros(Double.parseDouble(tfTaxa.getText())/100);
            paneTaxaJuros.setVisible(false);
            tfSenhaTaxa.setText("");
            tfTaxa.setText("");
        }else {
            lbStatusSenhaTaxa.setText("Senha inválida");
            lbStatusSenhaTaxa.setStyle("-fx-text-fill: red;");
            tfSenhaTaxa.setText("");
        }
    }

    @FXML
    private void removerStatusSenhaInvalidaTransacao(MouseEvent event) {
        lbStatusSenhaTransacao.setText("");
        lbStatusSenhaTransacao.setStyle("-fx-text-fill: #fff;");
    }

    @FXML
    private void removerStatusSenhaInvalidaTaxa(MouseEvent event) {
        lbStatusSenhaTaxa.setText("");
        lbStatusSenhaTaxa.setStyle("-fx-text-fill: #fff;");
    }
}
