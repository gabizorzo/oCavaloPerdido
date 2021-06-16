import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Movimentos {

    private static int tamanho;
    private static char tabuleiro[][];
    private static boolean tabuleiroVisitado[][];
    private static List<int[]> caminho = new ArrayList<int[]>();


    public static void leTabuleiro() throws IOException{
        Tabuleiros caso = new Tabuleiros();
        tabuleiro = caso.reader();
        tamanho = tabuleiro.length;
    }

    public static void inicializaTabuleiroVisitado(){
        tabuleiroVisitado = new boolean[tamanho][tamanho];

        for (int i = 0; i<tamanho; i++){
            for (int j = 0; j<tamanho; j++){
                tabuleiroVisitado[i][j] = false;
            }
        }
    }

    public static void imprimeTabuleiro(){
        for (int i = 0; i<tamanho; i++){
            for (int j = 0; j<tamanho; j++){
                System.out.print(tabuleiro[i][j]);
            }
        System.out.print("\n");
        }
    }


    public static int[] encontraCavalo(){
        int P[] = {-1, -1};

        for (int i = 0; i<tamanho; i++){
            for (int j = 0; j<tamanho; j++){
                if (tabuleiro[i][j] == 'C'){
                    P[0] = i;
                    P[1] = j;
                    return P;
                }
            }
        }

        System.out.println("Cavalo não encontrado.");
        System.exit(-1);
        return P;
    }

    public static boolean saida(int posicao[]){
        Boolean saida = false;

        if (tabuleiro[posicao[0]][posicao[1]] == 'S'){
            saida = true;

        }

        return saida;
    }

    public static int valorPosicao(int i){
        if (i < 0){
            i = tamanho + i;
        } else if (i > tamanho - 1){
            i = i - tamanho;
        }

        return i;
    }

    public static List<int[]> posicoesParaVisitar(int posicao[]){
        List<int[]> posicoes = new ArrayList<int[]>();

        int[] p1 = {valorPosicao(posicao[0]-1), valorPosicao(posicao[1]+2)};
        int[] p2 = {valorPosicao(posicao[0]-1), valorPosicao(posicao[1]-2)};
        int[] p3 = {valorPosicao(posicao[0]-2), valorPosicao(posicao[1]-1)};
        int[] p4 = {valorPosicao(posicao[0]-2), valorPosicao(posicao[1]+1)};
        int[] p5 = {valorPosicao(posicao[0]+2), valorPosicao(posicao[1]-1)};
        int[] p6 = {valorPosicao(posicao[0]+2), valorPosicao(posicao[1]+1)};
        int[] p7 = {valorPosicao(posicao[0]+1), valorPosicao(posicao[1]+2)};
        int[] p8 = {valorPosicao(posicao[0]+1), valorPosicao(posicao[1]-2)};

        posicoes.add(p1);
        posicoes.add(p2);
        posicoes.add(p3);
        posicoes.add(p4);
        posicoes.add(p5);
        posicoes.add(p6);
        posicoes.add(p7);
        posicoes.add(p8);

        return posicoes;
    }

    // public static int percorreProfundidade(int posicao[], List<int[]> caminho){
    //     caminho.add(posicao);
        

    //     if (!saida(posicao)){
    //         tabuleiroVisitado[posicao[0]][posicao[1]] = true;
    //         List<int[]> proximasPosicoes = posicoesParaVisitar(posicao);
    //         /*System.out.println("Visitando para o ponto (" + posicao[0] + "," + posicao[1] + ") ");
    //         for(int i = 0; i < proximasPosicoes.size(); i++){
    //             int c[] = proximasPosicoes.get(i);
    //             System.out.print("(" + c[0] + "," + c[1] + ") ");
    //         }   
    //         System.out.println();*/
    //         for(int i = 0; i < 8; i++){
    //             int p[] = proximasPosicoes.get(i);
    //             if (tabuleiro[p[0]][p[1]] != 'x' && !tabuleiroVisitado[p[0]][p[1]]){
    //                 //System.out.println("indo ->"+p[0]+","+p[1]);
    //                 percorreProfundidade(p, caminho);
    //                 //System.out.println("voltei"+p[0]+","+p[1]);
    //             }
    //         }


    //     } else {
    //         // for(int i = 0; i < caminho.size(); i++){
    //         //     int c[] = caminho.get(i);
    //         //     System.out.print("(" + c[0] + "," + c[1] + ") ");
    //         // }
    //         System.out.print(".");
    //         if (caminho.size() < menor){
    //             menor = caminho.size();
    //             System.out.print(" "+menor);
    //         }
    //     }

    //     caminho.remove(posicao);
    //     tabuleiroVisitado[posicao[0]][posicao[1]] = false;

    //     return 0;
    // }

    public static int[] percorreLargura(int posicao[]){
        
        List<int[]> proximasPosicoes;
        List<int[]> list = new ArrayList<int[]>();
        int x[] = {posicao[0], posicao[1], 0};
        int resultado[] = {-1, -1, -1};

        list.add(x);
        tabuleiroVisitado[x[0]][x[1]] = true;
        
        while (!list.isEmpty()){
            int p[] = list.get(0);

            list.remove(p);

            proximasPosicoes = posicoesParaVisitar(p);

            for(int j = 0; j < 8; j++){
                int p2[] = proximasPosicoes.get(j);

                if (tabuleiro[p2[0]][p2[1]] == '.' && !tabuleiroVisitado[p2[0]][p2[1]]){
                    int y[] = {p2[0], p2[1], p[2] + 1};
                    list.add(y);
                    tabuleiroVisitado[p2[0]][p2[1]] = true;
                } else if (saida(p2)){
                    resultado[0] = p2[0];
                    resultado[1] = p2[1];
                    resultado[2] = p[2] + 1;
                    return resultado;
                }
            }
        }
          

        return resultado;
    }


    public static void main(String[] args) throws IOException{
        leTabuleiro();
        
        inicializaTabuleiroVisitado();
        
        //imprimeTabuleiro();
        
        int posicaoInicial[] = encontraCavalo();

        System.out.println("O cavalo está em (" + posicaoInicial[0] + "," + posicaoInicial[1] + ")");

        int resultado[] = percorreLargura(posicaoInicial);

        if (resultado[0] == -1){
            System.out.println("A saída não foi encontrada.");
        } else {
            System.out.println("A saída foi encontrada na posição (" + resultado[0] + "," + resultado[1] + ") \nEm " + resultado[2] + " movimentos.");
        }
       
    }
}
