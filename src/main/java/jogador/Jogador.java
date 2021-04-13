/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogador;

/**
 *
 * @author kairos-04
 */
public class Jogador {
    String nome;
    char escolha;

    public Jogador(){
        
    }
    
    public Jogador(String nome, char escolha){
        setNome(nome);
        setEscolha(escolha);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getEscolha() {
        return escolha;
    }

    public void setEscolha(char escolha) {
        this.escolha = escolha;
    }
    
    
}
