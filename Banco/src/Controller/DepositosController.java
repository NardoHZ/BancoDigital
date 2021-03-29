/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOCliente.BoletoDAO;
import ModelCliente.Boleto;
import ModelCliente.SessaoCliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class DepositosController implements Initializable {

    @FXML
    private Pane paneGerarBoleto;
    @FXML
    private Pane paneReceberBoleto;
    @FXML
    private JFXTextField tfValor;
    @FXML
    private Label lbVencimento;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbValor;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private Pane paneBoletos;
    @FXML
    private TableView<Boleto> tableBoletos;
    @FXML
    private TableColumn<Boleto, String> clmCodigo;
    @FXML
    private TableColumn<Boleto, String> clmDataEmissao;
    @FXML
    private TableColumn<Boleto, String> clmValidade;
    @FXML
    private TableColumn<Boleto, String> clmStatus;
    @FXML
    private TableColumn<Boleto, Double> clmValor;
    @FXML
    private Hyperlink btMeusBoletos;
    
    private Boleto selecionandoBoleto;
    SessaoCliente sessao = SessaoCliente.getInstancia();
    @FXML
    private JFXButton btCopiarCodigoTabela;
    @FXML
    private JFXButton btExcluirBoleto;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableBoletos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionandoBoleto = (Boleto) newValue;
                
                if(selecionandoBoleto != null) {
                    btCopiarCodigoTabela.setDisable(false);
                    btExcluirBoleto.setDisable(false);
                }else {
                    btCopiarCodigoTabela.setDisable(true);
                    btExcluirBoleto.setDisable(true);
                }
            }
        });
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {{
                    try {
                        if(selecionandoBoleto.equals("")){
                            preencherTabelaBoletos();
                        }
                    } catch (Exception e) {
                    }
                }});
            }
        }, 1000, 1000);
    }    

    @FXML
    private void confirmarValor(ActionEvent event) {
        paneGerarBoleto.setVisible(false);
        paneReceberBoleto.setVisible(true);
        gerarBoleto();
        valorDeposito();
    }

    @FXML
    private void copiarCodigo(ActionEvent event) {
        java.awt.datatransfer.Clipboard copiar = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardOwner selecao = new StringSelection(lbCodigo.getText());
        copiar.setContents((Transferable) selecao, selecao);
    }

    @FXML
    private void gerarNovoCodigo(ActionEvent event) {
        paneGerarBoleto.setVisible(true);
        paneReceberBoleto.setVisible(false);
        lbCodigo.setText("");
    }
   
    private void gerarBoleto(){
        java.util.Date validade = new java.util.Date();
        java.util.Date dataEmissao = new java.util.Date();
        
        SimpleDateFormat formatarData = new SimpleDateFormat("yyyy-MM-dd");
        
        String dataEmissaoFormat = formatarData.format(dataEmissao);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(validade);
        cal.add(Calendar.DATE, 3);
        validade = cal.getTime();
        
        String validadeFormat = formatarData.format(validade);
        
        Random gerador = new Random();
        String codigo = "";
        for(int i = 1; i <= 44; i++){
            codigo += "" + gerador.nextInt(10);
        }
        lbCodigo.setText(codigo);
        
        Boleto b = new Boleto();
        BoletoDAO daoB = new BoletoDAO();
        
        b.setID_Cliente(sessao.getID_Cliente());
        b.setValor(Double.parseDouble(tfValor.getText().replace(",", ".")));
        b.setCodigo(codigo);
        b.setDataEmissao(dataEmissaoFormat);
        b.setValidade(validadeFormat);
        b.setStatus("Pendente");
        
        daoB.insert(b);
    }

    private void valorDeposito(){
        Locale l = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(l);
        lbValor.setText(nf.format(Double.parseDouble(tfValor.getText().replace(",", "."))));
    }

    @FXML
    private void detectarValor(KeyEvent event) {
        if(tfValor.getText().length() > 0){
            try {
                int valor = Integer.parseInt(tfValor.getText());
                if(valor >= 20){
                    btConfirmar.setDisable(false);
                }else{
                    btConfirmar.setDisable(true);
                }
            } catch (Exception e) {
                
            }
        }
    }
    
    @FXML
    private void copiarCodigoTabela(ActionEvent event) {
        java.awt.datatransfer.Clipboard copiar = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardOwner selecao = new StringSelection(selecionandoBoleto.getCodigo());
        copiar.setContents((Transferable) selecao, selecao);
    }

    @FXML
    private void excluirBoleto(ActionEvent event) {
        BoletoDAO daoB = new BoletoDAO();
        daoB.delete(selecionandoBoleto);
        preencherTabelaBoletos();   
    }

    @FXML
    private void voltarGerarBoleto(ActionEvent event) {
        paneBoletos.setVisible(false);
        btMeusBoletos.setVisible(true);
    }

    @FXML
    private void meusBoletos(ActionEvent event) {
        paneBoletos.setVisible(true);
        btMeusBoletos.setVisible(false);
        preencherTabelaBoletos();
    }
    
    private void preencherTabelaBoletos(){
        
        clmCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        clmDataEmissao.setCellValueFactory(new PropertyValueFactory<>("dataEmissaoFormatada"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        clmValidade.setCellValueFactory(new PropertyValueFactory<>("validadeFormatada"));
        clmValor.setCellValueFactory(new PropertyValueFactory<>("valorFormatado"));
        tableBoletos.setItems(listaBoletos());
    }
    
    public ObservableList<Boleto> listaBoletos(){
        BoletoDAO daoB = new BoletoDAO();
        return FXCollections.observableArrayList(daoB.getList(sessao.getID_Cliente()));
    }
}
