/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOCliente.ClienteDAO;
import DAOCliente.EmprestimoDAO;
import DAOFuncionario.AgenciaDAO;
import ModelCliente.ClienteC;
import ModelCliente.Emprestimo;
import ModelCliente.SessaoCliente;
import ModelFuncionario.Agencia;
import Recursos.Criptografia;
import Recursos.FormatarDinheiro;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class EmprestimosController implements Initializable {

    @FXML
    private JFXButton btConfirmar;
    @FXML
    private CheckBox cbTermos;
    @FXML
    private Pane paneTermos;
    @FXML
    private JFXTextField tfValor;
    @FXML
    private JFXComboBox<?> cbParcelas;
    @FXML
    private Label lbSenha;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private Label lbValorEmprestimo;
    @FXML
    private Label lbDividaRestante;
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
    private Label lbDividaTotal;
    @FXML
    private Pane meuEmprestimo;
    @FXML
    private JFXButton btSolicitacao;
    
    SessaoCliente sessao = SessaoCliente.getInstancia();
    @FXML
    private Label lbTaxa;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        verificarSolicitacaoEmprestimo();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(!btSolicitacao.getText().equals("Aberto")){
                        verificarSolicitacaoEmprestimo();
                    }
                    
                    EmprestimoDAO daoE = new EmprestimoDAO();
                    List<Emprestimo> e = daoE.getTaxaJuros();
                    
                    int juros = (int) (e.get(0).getJuros() * 100);

                    lbTaxa.setText("Juros: " + juros + "%");
                });
            }
        }, 1000, 10000);
    }    

    @FXML
    private void solicitarEmprestimo(ActionEvent event) {
        int numParcelas = numParcelas(cbParcelas.getSelectionModel().getSelectedIndex());
        Emprestimo e = new Emprestimo();
        e.setID_Cliente(sessao.getID_Cliente());
        e.setValorEmprestimo(Double.parseDouble(tfValor.getText().replace(",", ".")));
        e.setParcelasTotal(numParcelas);
        
        EmprestimoDAO daoE = new EmprestimoDAO();
        
        if(tfSenha.getText().length() > 0){
            if(sessao.getSenha().equals(Criptografia.MD5(tfSenha.getText()))){
                if(cbTermos.isSelected()){
                    if(cbParcelas.getSelectionModel().isEmpty() == false){
                        daoE.insert(e);
                        tfSenha.setText("");
                        tfValor.setText("");
                        cbTermos.selectedProperty().set(false);
                        cbParcelas.getItems().clear();

                        meuEmprestimo.setVisible(true);
                        btSolicitacao.setText("Cancelar solicitação");
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Transação não autorizada.");
                        alert.setContentText("Escolha a quantidade de parcelas.");
                        alert.showAndWait(); 
                    }
                }else {
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Banco Digital");
                    alert.setHeaderText("Transação não autorizada.");
                    alert.setContentText("Aceite os termos para continuar.");
                    alert.showAndWait(); 
                }
            }else {
                tfSenha.setText("");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Banco Digital");
                alert.setHeaderText("Senha inválida.");
                alert.setContentText("Tente novamente.");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("Campo senha vazio.");
            alert.setContentText("Informe sua senha.");
            alert.showAndWait();
        }
        verificarSolicitacaoEmprestimo();
    }
    
    private int numParcelas(int opcao){
        int numParcelas = 0;
        
        switch(opcao){
            case 0:
                numParcelas = 6;
            break;
            case 1:
                numParcelas = 12;
            break;
            case 2:
                numParcelas = 18;
            break;
            case 3:
                numParcelas = 24;
            break;
            case 4:
                numParcelas = 30;
            break;
            case 5:
                numParcelas = 36;
            break;
            case 6:
                numParcelas = 42;
            break;
            case 7:
                numParcelas = 48;
            break;
        }
        
        return numParcelas;
    }

    @FXML
    private void abrirPaneTermos(ActionEvent event) {
        paneTermos.setVisible(true);
    }

    @FXML
    private void fecharTermos(ActionEvent event) {
        paneTermos.setVisible(false);
    }   
    
    private void preencherComboBoxParcelas(){
        EmprestimoDAO daoE = new EmprestimoDAO();
        List<Emprestimo> em = daoE.getTaxaJuros();
        try {
            if(tfValor.getText().length() > 0){
                Double valor = Double.parseDouble(tfValor.getText().replace(",", "."));
                valor += valor * em.get(0).getJuros();
                double a = valor/6;
                double b = valor/12;
                double c = valor/18;
                double d = valor/24;
                double e = valor/30;
                double f = valor/36;
                double g = valor/42;
                double h = valor/48;

                List parcelas = new ArrayList<>();

                parcelas.add(0, "6x de " + FormatarDinheiro.formatarDinheiro(a));
                parcelas.add(1, "12x de " + FormatarDinheiro.formatarDinheiro(b));
                parcelas.add(2, "18x de " + FormatarDinheiro.formatarDinheiro(c));
                parcelas.add(3, "24x de " + FormatarDinheiro.formatarDinheiro(d));
                parcelas.add(4, "30x de " + FormatarDinheiro.formatarDinheiro(e));
                parcelas.add(5, "36x de " + FormatarDinheiro.formatarDinheiro(f));
                parcelas.add(6, "42x de " + FormatarDinheiro.formatarDinheiro(g));
                parcelas.add(7, "48x de " + FormatarDinheiro.formatarDinheiro(h));

                cbParcelas.setItems(FXCollections.observableArrayList(parcelas));
            } else {
                cbParcelas.getItems().clear();
            }
        } catch (Exception e) {
        }
    }
    
    private void verificarLimiteEmprestimo(){
        ClienteDAO daoCl = new ClienteDAO();
        List<ClienteC> cliente = daoCl.getUsuario(sessao.getCPF());
        
        String renda = cliente.get(0).getRenda();
        double limite = 0;
        
        switch(renda){
            case "Inferior a R$ 998,00":
                limite = 2994;
                break;
                
            case "Entre R$ 998,00 e R$ 1.996,00":
                limite = 5998;
                break;
                
            case "Entre R$ 1.996,00 e R$ 3.992,00":
                limite = 11976;
                break;
                
            case "Superior à R$ R$ 3.992,00":
                limite = 39920;
                break;
        }
        
        if(tfValor.getText().length() > 0){
            try {
                if(Double.parseDouble(tfValor.getText()) <= limite){
                    btConfirmar.setDisable(false);
                }else {
                    btConfirmar.setDisable(true);
                }
            } catch (Exception e) {
            }
        }else{
            btConfirmar.setDisable(true);
        }
    }

    @FXML
    private void verificarStatusTermos(MouseEvent event) {
    }

    
    private void verificarSolicitacaoEmprestimo(){
        EmprestimoDAO daoE = new EmprestimoDAO();
        List<Emprestimo> emprestimo = daoE.getEmprestimo(sessao.getID_Cliente());
        
        try {
            if(emprestimo.isEmpty() == false){
                int juros = (int) (emprestimo.get(0).getJuros() * 100);
                
                lbValorEmprestimo.setText(emprestimo.get(0).getValorEmprestimoFormatado());
                lbDividaRestante.setText(emprestimo.get(0).getDividaParcialFormatada());
                lbDividaTotal.setText(emprestimo.get(0).getDividaTotalFormatada());
                lbValorParcelas.setText(emprestimo.get(0).getValorParcelasFormatado());
                lbNumeroParcelas.setText(emprestimo.get(0).getParcelasTotal() + "");
                lbParcelasRestantes.setText(emprestimo.get(0).getParcelasParcial()+ "");
                lbTaxaJuros.setText(juros + "%");
                lbStatus.setText(emprestimo.get(0).getStatus());
                
                String status = emprestimo.get(0).getStatus();
                switch(status){
                    case "Aguardando aprovação":
                        meuEmprestimo.setVisible(true);
                        break;
                    case "Aprovado":
                        meuEmprestimo.setVisible(true);
                        btSolicitacao.setVisible(false);
                        break;
                    case "Negado":
                        meuEmprestimo.setVisible(true);
                        btSolicitacao.setVisible(true);
                        btSolicitacao.setText("Nova solicitação");
                        break;
                    case "Cancelado":
                        meuEmprestimo.setVisible(false);
                        break;
                    case "Pago":
                        meuEmprestimo.setVisible(true);
                        btSolicitacao.setVisible(true);
                        btSolicitacao.setText("Nova solicitação");
                        break;
                }
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void solicitacaoEmprestimo(ActionEvent event) {
        verificarSolicitacaoEmprestimo();
        
        Emprestimo e = new Emprestimo();
        EmprestimoDAO daoE = new EmprestimoDAO();
        EmprestimoDAO dao = new EmprestimoDAO();
        List<Emprestimo> emprestimo = daoE.getEmprestimo(sessao.getID_Cliente());
        e.setID_Emprestimo(emprestimo.get(0).getID_Emprestimo());
        
        if(lbStatus.getText().equals("Aguardando aprovação")){
            dao.cancelarEmprestimo(e);
            meuEmprestimo.setVisible(false);
            btSolicitacao.setText("Aberto");
        }else {
            meuEmprestimo.setVisible(true);
        }
        
        if(lbStatus.getText().equals("Pago") || lbStatus.getText().equals("Negado") && btSolicitacao.getText().equals("Nova solicitação")){
            meuEmprestimo.setVisible(false);
            btSolicitacao.setText("Aberto");
        }
    }

    @FXML
    private void calcularParcelas(KeyEvent event) {
        preencherComboBoxParcelas();
        verificarLimiteEmprestimo();
    }
 }
