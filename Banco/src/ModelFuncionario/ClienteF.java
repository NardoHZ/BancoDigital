/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelFuncionario;

import ModelCliente.Emprestimo;
import Recursos.FormatarData;

/**
 *
 * @author Anderson
 */
public class ClienteF extends Emprestimo{
    
    private int ID_Conta;
    private int ID_Endereco;
    private String Nome;
    private String Sobrenome;
    private String CPF;
    private String dataNascimento;
    private String dataNascimentoFormatada;
    private String Profissao;
    private String Sexo;
    private String Telefone;
    private String Senha;
    private String Agencia;
    private String Conta;
    private String Digito;
    private String Renda;
    private double Saldo;
    private String Logradouro;
    private String Bairro;
    private String CEP;
    private String UF;

    public ClienteF() {
    }
    
    public int getID_Endereco() {
        return ID_Endereco;
    }

    public void setID_Endereco(int ID_Endereco) {
        this.ID_Endereco = ID_Endereco;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String Logradouro) {
        this.Logradouro = Logradouro;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getRenda() {
        return Renda;
    }

    public void setRenda(String Renda) {
        this.Renda = Renda;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getDataNascimentoFormatada() {
        return FormatarData.formatarData(getDataNascimento(), dataNascimentoFormatada);
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getProfissao() {
        return Profissao;
    }

    public void setProfissao(String Profissao) {
        this.Profissao = Profissao;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public int getID_Conta() {
        return ID_Conta;
    }

    public void setID_Conta(int ID_Conta) {
        this.ID_Conta = ID_Conta;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String Agencia) {
        this.Agencia = Agencia;
    }

    public String getConta() {
        return Conta;
    }

    public void setConta(String Conta) {
        this.Conta = Conta;
    }

    public String getDigito() {
        return Digito;
    }

    public void setDigito(String Digito) {
        this.Digito = Digito;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }
}
