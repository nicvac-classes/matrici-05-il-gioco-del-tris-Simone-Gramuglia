//LEGGERE LE ISTRUZIONI NEL FILE README.md

import java.util.Scanner;
// Classe principale, con metodo main
class Esercizio {
    static Scanner input = new Scanner(System.in);
    //Inserisce il simbolo x oppure o nella grigla di gioco in riga i e colonna j.
    //Se la mossa non è valida (pedina già presente o le coordinate sono fuori la griglia) allora ritorno falso.
    static boolean inserisciInGriglia(String [][]G , int i, int j, String s) {
        boolean t = false;
        --i;
        --j;
        t = (0<= i && i<= 2 && 0<= j && j<= 2 && G[i][j].equals(s));
        if (t){
            G[i][j] = s;
        }
        return t;
    }
    //Azzero la griglia di gioco inserendo la stringa "-" in tutte le celle.
    static void azzeraGriglia(String [][]G , int r , int c) {
        int i = 0;
        while (i < r){
            int j = 0;
            while (j < c) {
                G[i][j] = "-";
                ++j;
            }
            ++i;
        }
    }
    //Controlla se nella griglia c'è una vincita.
    // s può valore "O" oppure "X"
    static boolean controllaVincita(String [][]G ,String s) {
        boolean v = false;
        if ( G[0][0].equals(s) && G[0][1].equals(s) && G[0][2].equals(s) ) {
            v = true;
        }
        if ( !(v) && G[1][0].equals(s) && G[1][1].equals(s) && G[1][2].equals(s) ) {
            v = true;
        }
        if ( !(v) && G[2][0].equals(s) && G[2][1].equals(s) && G[2][2].equals(s) ) {
            v = true;
        }
        if ( !(v) && G[0][0].equals(s) && G[1][0].equals(s) && G[2][0].equals(s) ) {
            v = true;
        }
        if ( !(v) && G[0][1].equals(s) && G[1][1].equals(s) && G[2][1].equals(s) ) {
            v = true;
        }
        if ( !(v) && G[0][2].equals(s) && G[1][2].equals(s) && G[2][2].equals(s) ) {
            v = true;
        }
        if ( !(v) && G[0][0].equals(s) && G[1][1].equals(s) && G[2][2].equals(s) ) {
            v = true;
        }
        if ( !(v) && G[0][2].equals(s) && G[1][1].equals(s) && G[2][0].equals(s) ) {
            v = true;
        }
        return v;
    }
    //Conta quante caselle libere ci sono ancora.
    //Se non ci sono caselle libere e non si è vinto allora è un pareggio
    static int contaCaselleLibere(String[][] G) {
        int count=0;
        for (int i=0; i<=2; i=i+1) {
            for (int j=0; j<=2; j=j+1) {
                if ( G[i][j].equals("-") ) {
                    count = count + 1;
                }
            }
        } 
        return count;
    }
    public static void main(String args[]) {
    
        String[][] G = new String[3][3];
        azzeraGriglia(G,3,3);
        System.out.println("Griglia di gioco:");
        UtilsMatrice.visualizza(G);
        String s = "X";
        boolean haiVinto = false;
        boolean pareggio = false;
        do {
            boolean mC;
            int mI, mJ;
            do {
                System.out.println("Giocatore "+s+", inserisci la tua mossa (riga e colonna [1-3]):");
                mI = Integer.parseInt(input.nextLine());
                mJ = Integer.parseInt(input.nextLine());
                mC = inserisciInGriglia(G, mI, mJ, s);
            } while( !(mC) );
            System.out.println("Griglia :");
            UtilsMatrice.visualizza(G);
            haiVinto = controllaVincita(G, s);
            if (haiVinto) {
                System.out.println(s + " vince la partita");
            }
            int caselleLibere = contaCaselleLibere(G);
            pareggio = !(haiVinto) && (caselleLibere == 0);
            if ( pareggio ) {
                System.out.println("Hai pareggiato");
            }
            if ( s.equals("X") ) {
                s = "O";
            } else {
                s = "X";
            }
        } while ( !(haiVinto) && !(pareggio) );
    }
}