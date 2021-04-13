/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jogodavelha;

import java.util.Scanner;

import jogador.Jogador;

import java.util.ArrayList;
/**
 *
 * @author kairos-04
 */
public class Main {
    public static void main(String[] args) {
        char continuar;
        int jogadas;
        Jogador novoJogador;
        Scanner teclado = new Scanner(System.in);
        String nome;
        final int VERTICAL = 3, HORIZONTAL = 3;
        
        do{
            int local, contGeral = 0;
            char matriz[][] = new char[3][3], escolha = 0;
            ArrayList<Jogador> jogadores = new ArrayList<>();
            boolean jaEscolhida = false, alguemGanhou = false;
            
            //preenche a matriz com 1 a 9
            for(int j = 0; j < VERTICAL; j++){
                for (int i = 0; i < HORIZONTAL; i++) {
                    matriz[i][j] = (char) (contGeral + '1');
                    contGeral++;
                }
            }

            //adiciona jogadores
            System.out.println("\nJOGO DA VELHA");
            jogadas = 0;
            for(int i = 0; i < 2; i++){
                System.out.println("\nJogador " + (i + 1) + ": ");
                nome = teclado.nextLine();

                do{
                    System.out.println("\n" + nome + " escolha x ou o: ");
                    escolha = teclado.next().charAt(0);
                    teclado.nextLine();

                    for(Jogador j:jogadores){
                        if(j.getEscolha() == escolha){
                            System.out.println("\nOpção já escolhida!");
                            jaEscolhida = true;
                        }else{
                            jaEscolhida = false;
                        }
                    }
                }while(escolha != 'x' && escolha != 'o' || jaEscolhida == true);

                novoJogador = new Jogador(nome, escolha);
                jogadores.add(novoJogador);
            }
            do{
                for(int k = 0; k < 2; k++){
                    
                    if(jogadas == 9){
                        System.out.println("\nNenhum jogador ganhou!!");
                        break;
                    }
                    
                    if(!verificaSeGanhou(matriz, VERTICAL, HORIZONTAL, alguemGanhou)){
                        System.out.println("\nJOGO DA VELHA");
                        System.out.println("");

                        // imprime o #
                        imprimeJogo(matriz, VERTICAL, HORIZONTAL);

                        System.out.println(jogadores.get(k).getNome() + " joga " + Character.toUpperCase(jogadores.get(k).getEscolha()) + " em: ");
                        local = teclado.nextInt();

                        switch(local){
                            case 1, 2, 3:
                                if(jogadores.get(k).getEscolha() == 'x'){
                                    matriz[local - 1][0] = 'X';
                                }else if(jogadores.get(k).getEscolha() == 'o'){
                                    matriz[local - 1][0] = 'O';
                                }

                                if(verificaSeGanhou(matriz, VERTICAL, HORIZONTAL, alguemGanhou)){
                                    System.out.println("\nO jogador " + jogadores.get(k).getNome() + " ganhou!!");
                                }
                                break;
                            case 4, 5, 6:
                                if(jogadores.get(k).getEscolha() == 'x'){
                                    matriz[local - 4][1] = 'X';
                                }else if(jogadores.get(k).getEscolha() == 'o'){
                                    matriz[local - 4][1] = 'O';
                                }

                                if(verificaSeGanhou(matriz, VERTICAL, HORIZONTAL, alguemGanhou)){
                                    System.out.println("\nO jogador " + jogadores.get(k).getNome() + " ganhou!!");
                                }
                                break;
                            case 7, 8, 9:
                                if(jogadores.get(k).getEscolha() == 'x'){
                                    matriz[local - 7][2] = 'X';
                                }else if(jogadores.get(k).getEscolha() == 'o'){
                                    matriz[local - 7][2] = 'O';
                                }

                                if(verificaSeGanhou(matriz, VERTICAL, HORIZONTAL, alguemGanhou)){
                                    System.out.println("\nO jogador " + jogadores.get(k).getNome() + " ganhou!!");
                                }
                                break;
                            default:
                                System.out.println("\nOpção inválida!");   
                        }
                        
                        jogadas++;
                    }
                }
            }while(verificaSeGanhou(matriz, VERTICAL, HORIZONTAL, alguemGanhou) != true && jogadas < 9);
            
            System.out.println("\nJogar de novo? [s] sim  [n] não");
            continuar = teclado.next().charAt(0);
            teclado.nextLine();
            
        }while(continuar != 'n');
    }
    
    public  static void imprimeJogo(char matriz[][], final int VERTICAL, final int HORIZONTAL){
        System.out.println("       |       |");
        for(int j = 0; j < VERTICAL; j++){
            System.out.print("   ");
            for (int i = 0; i < HORIZONTAL; i++) {
                if(i == 2){
                    System.out.print(matriz[i][j] + " ");
                }else{
                    System.out.print(matriz[i][j] + "   |   ");
                }
            }
            System.out.print("\n");
            if(j != 2){
                System.out.println("-------+-------+-------");
            }
        }
        System.out.println("       |       |");
    }
    
    public static boolean verificaSeGanhou(char matriz[][], final int VERTICAL, final int HORIZONTAL, boolean alguemGanhou){
        char linha[] = new char[3];
        int contadorDiagonalSec;
        
        //verifica diagonal primaria
        linha = new char[3];
        for(int j = 0; j < VERTICAL; j++){
            for (int i = 0; i < HORIZONTAL; i++) {
                linha[j] = matriz[j][j];
            }
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        //verifica diagonal secundaria
        linha = new char[3];
        contadorDiagonalSec = HORIZONTAL - 1;
        for(int j = 0; j < VERTICAL; j++){
            linha[j] = matriz[contadorDiagonalSec][j];
            contadorDiagonalSec--;
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        //verifica primeira linha
        linha = new char[3];
        for (int i = 0; i < HORIZONTAL; i++) {
            linha[i] = matriz[i][0];
//            System.out.println("linha: " + linha[0] + linha[1] + linha[2]);
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        //verifica segunda linha
        linha = new char[3];
        for (int i = 0; i < HORIZONTAL; i++) {
            linha[i] = matriz[i][1];
//            System.out.println("linha: " + linha[0] + linha[1] + linha[2]);
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        //verifica terceira linha
        linha = new char[3];
        for (int i = 0; i < HORIZONTAL; i++) {
            linha[i] = matriz[i][2];
//            System.out.println("linha: " + linha[0] + linha[1] + linha[2]);
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        //verifica primeira coluna
        linha = new char[3];
        for (int i = 0; i < HORIZONTAL; i++) {
            linha[i] = matriz[0][i];
//            System.out.println("linha: " + linha[0] + linha[1] + linha[2]);
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        //verifica segunda coluna
        linha = new char[3];
        for (int i = 0; i < HORIZONTAL; i++) {
            linha[i] = matriz[1][i];
//            System.out.println("linha: " + linha[0] + linha[1] + linha[2]);
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        //verifica terceira coluna
        linha = new char[3];
        for (int i = 0; i < HORIZONTAL; i++) {
            linha[i] = matriz[2][i];
//            System.out.println("linha: " + linha[0] + linha[1] + linha[2]);
            
            if(linha[0] == linha[1] && linha[1] == linha[2]){
                alguemGanhou = true;
            }
        }
        
        return alguemGanhou;
    }
}
