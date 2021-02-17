package br.alvaro.handler;

import java.util.Random;

public class JanKenPanHandler {

    public Boolean sePrimeiroJogadorGanhou (String mao1, String mao2){
        if (mao1.equalsIgnoreCase("Papel") && (mao2.equalsIgnoreCase("Pedra"))){
            return true;
        }

        if (mao1.equalsIgnoreCase("Papel") && (mao2.equalsIgnoreCase("Tesoura"))){
            return false;
        }

        if (mao1.equalsIgnoreCase("Pedra") && (mao2.equalsIgnoreCase("Tesoura"))){
            return true;
        }

        if (mao1.equalsIgnoreCase("Pedra") && (mao2.equalsIgnoreCase("Papel"))){
            return false;
        }

        if (mao1.equalsIgnoreCase("Tesoura") && (mao2.equalsIgnoreCase("Papel"))){
            return true;
        }

        if (mao1.equalsIgnoreCase("Tesouta") && (mao2.equalsIgnoreCase("Pedra"))){
            return false;
        }

        //casos de empate
        return null;
    }

    public String gerarJogada (){
        Random gerador = new Random();
        int intMao = gerador.nextInt(3);
        String mao;
        if (intMao == 0)
            mao = "Papel";
        else if (intMao == 1)
            mao = "Pedra";
        else
            mao = "Tesoura";

        return mao;
    }
}
