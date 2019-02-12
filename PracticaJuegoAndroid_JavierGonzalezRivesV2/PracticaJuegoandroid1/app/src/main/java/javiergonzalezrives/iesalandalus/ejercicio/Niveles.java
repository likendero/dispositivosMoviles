package javiergonzalezrives.iesalandalus.ejercicio;

/**
 * clase almacen con los niveles del juego
 */
public class Niveles {
    private static final int[][] NIVEL1 = {
            {0,0,0,2},
            {0,0,1,1},
            {0,0,0,0},
            {0,1,1,0},
            {0,0,0,0},
            {1,1,1,1}

    };
    private static final int[][] NIVEL2 = {
            {0,0,0,0},
            {1,0,2,0},
            {0,0,0,0},
            {0,0,0,1},
            {1,0,0,0},
            {1,0,0,0}

    };
    private static final int[][] NIVEL3 = {
            {0,0,0,0},
            {0,0,1,2},
            {0,0,1,1},
            {0,1,0,0},
            {0,0,0,0},
            {1,0,0,0}

    };
    private static final int[][] NIVEL4 = {
            {1,0,2,1},
            {0,0,0,0},
            {0,1,1,0},
            {0,1,1,0},
            {0,0,0,0},
            {1,0,0,1}

    };
    private static final int[][] NIVEL5 = {
            {0,0,0,0},
            {0,1,0,0},
            {0,0,0,0},
            {1,0,1,0},
            {2,0,1,0},
            {0,0,1,0}

    };
    public static int[][] getNivel(int nivel){
       // selector que devuelve el nivel segun el indicado
        switch (nivel){
            case 1:
                return NIVEL1;
            case 2:
                return NIVEL2;
            case 3:
                return NIVEL3;
            case 4:
                return NIVEL4;
            case 5:
                return NIVEL5;
       }
        return null;//hola guapo
    }


}
