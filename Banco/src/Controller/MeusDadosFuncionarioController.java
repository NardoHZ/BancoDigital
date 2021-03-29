/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOFuncionario.FuncionarioDAO;
import ModelFuncionario.Funcionario;
import ModelFuncionario.SessaoFuncionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class MeusDadosFuncionarioController implements Initializable {

    @FXML
    private Label lbMostrarDataNascimento;
    @FXML
    private Label lbMostrarCPF;
    @FXML
    private Label lbMostrarSobrenome;
    @FXML
    private Label lbmostrarNome;
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
    private Label lbSenha;
    @FXML
    private JFXButton btEditar;
    @FXML
    private JFXButton btDeletar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSalvar;
    @FXML
    private Pane paneConfirmarExclusao;
    @FXML
    private JFXPasswordField tfSenhaExclusao;
    @FXML
    private Label lbStatusSenha;
    @FXML
    private Label lbMostrarDepartamento;
    @FXML
    private Label lbMostrarSalario;
    @FXML
    private Label lbMostrarSexo;
    
    SessaoFuncionario sessao = SessaoFuncionario.getInstancia();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        meusDadosFuncionario();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                    meusDadosFuncionario();
                });
            }
        },1000, 1000);
    }    
    
    private void meusDadosFuncionario(){
        FuncionarioDAO daoF = new FuncionarioDAO();
        List<Funcionario> f = daoF.getFuncionario(sessao.getCPF());
        
        lbmostrarNome.setText(f.get(0).getNome());
        lbMostrarSobrenome.setText(f.get(0).getSobrenome());
        lbMostrarCPF.setText(f.get(0).getCPF());
        lbMostrarDataNascimento.setText(f.get(0).getDataNascimentoFormatado());
        lbMostrarDepartamento.setText(f.get(0).getDepartamento());
        lbMostrarSalario.setText(f.get(0).getSalarioFormatado());
        lbMostrarSexo.setText(f.get(0).getSexo());
        lbMostrarTelefone.setText(f.get(0).getTelefone());
        lbMostrarLogradouro.setText(f.get(0).getLogradouro());
        lbMostrarBairro.setText(f.get(0).getBairro());
        lbMostrarCEP.setText(f.get(0).getCEP());
        lbMostrarUF.setText(f.get(0).getUF());
       
    }
    

    @FXML
    private void editarMinhaConta(ActionEvent event) {
    }

    @FXML
    private void deletarMinhaConta(ActionEvent event) {
    }

    @FXML
    private void cancelarEdicao(ActionEvent event) {
    }

    @FXML
    private void salvarEdicao(ActionEvent event) {
    }

    @FXML
    private void removerStatusSenhaInvalida(MouseEvent event) {
    }

    @FXML
    private void cancelarExclusaoConta(ActionEvent event) {
    }

    @FXML
    private void confirmarExclusaoConta(ActionEvent event) {
    }
    
}
