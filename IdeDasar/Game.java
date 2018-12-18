import java.util.Random;

public class Game{
    int boardLabel[][] = {
        {0 , 0 , 0 , 0 },   // [0][0] , [0][1] , [0][2] , [0][3] , 
        {0 , 0 , 0 , 0 },   // [1][0] , [1][1] , [1][2] , [1][3] , 
        {0 , 0 , 0 , 0 },   // [2][0] , [2][1] , [2][2] , [2][3] , 
        {0 , 0 , 0 , 0 }    // [3][0] , [3][1] , [3][2] , [3][3] , 
     };

     Random rand = new Random(); //Menginisiasi perandom


    public boolean boardSpawnerChecker(){ //untuk mengecek apakah ada space tersisa 
        for(int row = 0;row<4;row++){
            for(int column=0;column<4;column++){
                if (boardLabel[row][column] == 0){
                    return true;
                }
            }   
        }
        return false;
    }
    
    public int[][] copyTableData(){ //untuk copy boardTable
        int copyTable[][] = new int[4][4];
        for(int row = 0;row<4;row++){
            for(int column=0;column<4;column++){
                copyTable[row][column] = boardLabel[row][column];
            }
        }
        return copyTable;
        
    }

    public void spawnNumber(){ //untuk mengespawn nomor 2 / 4 di tile yang kosong 
        int row;
        int column;
        do{
            row     = rand.nextInt(4);
            column  = rand.nextInt(4);
        }while(boardLabel[row][column] != 0); //dilakukan perandoman koordinat untuk pemasukan tile yang kosong
        int twoOrFour;
        do{
            twoOrFour = rand.nextInt(5);
        }while(twoOrFour!=2 && twoOrFour !=4); //perandom agar nilai yang ingin di tampilkan di koordinat yang kosong adalah 2 / 4
        boardLabel[row][column] = twoOrFour;   
    }

    public void up3(){ //versi ke 3 fungsi pada saat user menekan tombol atas ^
        // int[][] copyTable = movedOrNot();
        for (int column = 0;column<4;column++){ 
            for(int row = 0;row<3;row++){
                for(int ulang = 0;ulang<3;ulang++){ // pertama yang dilakukan adalah memindahkan semua data agar berdekatan .. agar mencangkup semua tile , kita ulang 3x
                    for(int fillRow=0+row;fillRow<3;fillRow++){
                        if(boardLabel[fillRow][column] == 0 && boardLabel[fillRow+1][column] !=0){
                            boardLabel[fillRow][column] += boardLabel[fillRow+1][column];
                            boardLabel[fillRow+1][column]=0;
                        }
                    }
                }
                if(boardLabel[row][column]==boardLabel[row+1][column] && boardLabel[row][column]!=0 && boardLabel[row+1][column] != 0){
                    boardLabel[row][column]+=boardLabel[row+1][column];
                    boardLabel[row+1][column]=0;
                }
            }
         }
    }

    //------------------------------------------------
    // ide daasar
    // for (int row_checker = 1+row; row_checker<4;row_checker++){
    //     if(boardLabel[row][column]==boardLabel[row_checker][column] && boardLabel[row][column]!=0 && boardLabel[row_checker][column] != 0 && row_checker-1 == row){
    //         boardLabel[row][column]+=boardLabel[row_checker][column];
    //         boardLabel[row_checker][column]=0;
    //         break;
    //     }
    // else if(boardLabel[row][column] == 0 && boardLabel[row_checker][column]!=0){
    //     boardLabel[row][column]+=boardLabel[row_checker][column];
    //     boardLabel[row_checker][column]=0;
    // }
    //----------------------------------------------

    //  public void up(){
    //     int terjumlah = 0;
    //     for(int ulang = 0; ulang<3;ulang++){
    //         for(int row = 0;row<3;row++){
    //             for(int column = 0; column<4;column++){
    //                 if(boardLabel[row][column]==boardLabel[row+1][column]){
    //                     boardLabel[row][column]+=boardLabel[row+1][column];
    //                     boardLabel[row+1][column] = 0;
    //                     terjumlah++;
    //                 }else if(boardLabel[row][column]==0){
    //                     boardLabel[row][column]+=boardLabel[row+1][column];
    //                     boardLabel[row+1][column] = 0;
    //                 }
    //             }
    //         }
    //     }
    //  }

     public void right(){ //fungsi saat user mennekan tombol kanan
        boardLabel = rotateLeft(); //kita putar ke kiri agar bagian kanan menjadi di atas , dan kita bisa menggunakan logika yang sama untuk menggerakan bagian kanan 
        for (int column = 0;column<4;column++){
            for(int row = 0;row<3;row++){
                for(int ulang = 0;ulang<3;ulang++){
                    for(int fillRow=0+row;fillRow<3;fillRow++){
                        if(boardLabel[fillRow][column] == 0 && boardLabel[fillRow+1][column] !=0){
                            boardLabel[fillRow][column] += boardLabel[fillRow+1][column];
                            boardLabel[fillRow+1][column]=0;
                        }
                    }
                }
                if(boardLabel[row][column]==boardLabel[row+1][column] && boardLabel[row][column]!=0 && boardLabel[row+1][column] != 0){
                    boardLabel[row][column]+=boardLabel[row+1][column];
                    boardLabel[row+1][column]=0;
                }
            }
         }
        boardLabel = rotateRight(); //setelah itu kita kembalikan agar board normal
    }

     public void down(){ //fungsi saat user mennekan tombol bawah
        boardLabel = mirror();
        for (int column = 0;column<4;column++){
            for(int row = 0;row<3;row++){
                for(int ulang = 0;ulang<3;ulang++){
                    for(int fillRow=0+row;fillRow<3;fillRow++){
                        if(boardLabel[fillRow][column] == 0 && boardLabel[fillRow+1][column] !=0){
                            boardLabel[fillRow][column] += boardLabel[fillRow+1][column];
                            boardLabel[fillRow+1][column]=0;
                        }
                    }
                }
                if(boardLabel[row][column]==boardLabel[row+1][column] && boardLabel[row][column]!=0 && boardLabel[row+1][column] != 0){
                    boardLabel[row][column]+=boardLabel[row+1][column];
                    boardLabel[row+1][column]=0;
                }
            }
         }
        boardLabel = mirror();
     }


     public void left(){ //Fungsi saat user menekan tombol kiri <--
        boardLabel = rotateRight();
        for (int column = 0;column<4;column++){
            for(int row = 0;row<3;row++){
                for(int ulang = 0;ulang<3;ulang++){
                    for(int fillRow=0+row;fillRow<3;fillRow++){
                        if(boardLabel[fillRow][column] == 0 && boardLabel[fillRow+1][column] !=0){
                            boardLabel[fillRow][column] += boardLabel[fillRow+1][column];
                            boardLabel[fillRow+1][column]=0;
                        }
                    }
                }
                if(boardLabel[row][column]==boardLabel[row+1][column] && boardLabel[row][column]!=0 && boardLabel[row+1][column] != 0){
                    boardLabel[row][column]+=boardLabel[row+1][column];
                    boardLabel[row+1][column]=0;
                }
            }
         }
        boardLabel = rotateLeft();
     }


     public void printBoard(){ //secara default akan mengambil board dari class ini (boardLabel)
        for(int row = 0;row<4;row++){
            for(int column = 0; column<4;column++){
                    System.out.print(boardLabel[row][column]);
                }
                System.out.println();
            }
            System.out.print("---------\n");
     }

     public void printBoard(int Board[][]){ //hanya untuk coba coba jika ada tabel lain yang ingin di masukan
        for(int row = 0;row<4;row++){
            for(int column = 0; column<4;column++){
                    System.out.print(Board[row][column]);
                }
                System.out.println();
            }
            System.out.print("---------\n");
     }

     public int[][] rotateLeft(){ //memutar tabel 90 derajat
        int copyTable[][] = new int[4][4];
        for(int row = 0;row<4;row++){
            for(int column=0;column<4;column++){
                copyTable[3-column][row] = boardLabel[row][column];
            }
        }
        return copyTable;
     }

   // overloading ----------------------------------------------------------
     public int[][] rotateRight(){ //memutar tabel -90 derajat
        int copyTable[][] = new int[4][4];
        for(int row = 0;row<4;row++){
            for(int column=0;column<4;column++){
                copyTable[column][3-row] = boardLabel[row][column];
            }
        }
        return copyTable;
     }

     public int[][] rotateRight(int[][] table){ //memutar tabel -90 derajat (tetapi dengan tabel lain)
        int copyTable[][] = new int[4][4];
        for(int row = 0;row<4;row++){
            for(int column=0;column<4;column++){
                copyTable[column][3-row] = table[row][column];
            }
        }
        return copyTable;
     }

     public int[][] mirror(){ //memutar tabel 180 derajat
        int copyTable[][] = new int[4][4];
        for(int row = 0;row<4;row++){
            for(int column=0;column<4;column++){
                copyTable[3-row][3-column] = boardLabel[row][column];
            }
        }
        return copyTable;
     }

     public void start(){ //inisialisasi kembali tabel dengan merandom 2 nomor di koordinat random
         for(int ulang=0;ulang<2;ulang++){
             spawnNumber();
         }
     }

     public void reset(){// mereset agar kotak menjadi 0 semua
        for(int row = 0;row<4;row++){
            for(int column=0;column<4;column++){
                boardLabel[row][column] = 0;
            }
        }
     }

     public boolean possibleMove(){ //mengecek apakah masih bisa di gerakan atau tidak
        int copyTable[][] = copyTableData();
        for (int putar=0;putar<3;putar++){
            for (int column = 0;column<4;column++){
                for(int row = 0;row<3;row++){
                    for(int ulang = 0;ulang<3;ulang++){
                        for(int fillRow=0+row;fillRow<3;fillRow++){
                            if(copyTable[fillRow][column] == 0 && copyTable[fillRow+1][column] !=0){
                                copyTable[fillRow][column] += copyTable[fillRow+1][column];
                                copyTable[fillRow+1][column]=0;
                                return true; //Jika bisa di gerakan , langsung return True
                            }
                        }
                    }
                    if(copyTable[row][column]==copyTable[row+1][column] && copyTable[row][column]!=0 && copyTable[row+1][column] != 0){
                        copyTable[row][column]+=copyTable[row+1][column];
                        copyTable[row+1][column]=0;
                    }
                }
            }
            copyTable = rotateRight(copyTable); //kita putar 3x agar semua kemungkinan gerakan dapat kita periksa 
        }
        return false; //jika sampai akhir tidak bisa , ya false
     }
}