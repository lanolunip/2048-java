import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.util.Arrays;
import java.util.EventListener;
import java.awt.Font;
import javax.swing.SpringLayout.Constraints;

public class window extends JFrame implements KeyListener{
    private int     height  = 600;
    private int     width   = 600;
    private String  title   = "2048";
    Game    board           = new Game(); 
    JLabel  boardTile[][]   = new JLabel[4][4];
    Border  border          = BorderFactory.createLineBorder(Color.BLUE,5); //untuk border pada JLabel

    window(){ //memunculkan window (lebih tepatnya inisialisasi awal)
        setSize(getwidth(),getheight()); //setting ukuran window
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Setting tombol close untuk dapat menghentikan game
        setResizable(false); // mensetting agar tak dapat di ubah ukuran nya
        setTitle(gettitle()); // set judul window ... variabel judul ada di atas kalau mau di atur wkwk
        setLayout(new GridLayout(4,4));// mensetting bentuk agar menjadi bentuk grid [  ] [  ] 4,4 berarti 4 kolom 4 baris
        addKeyListener(this); //menambahkan window agar key keyboard dan window dapat berhubungan , harus membuat beberapa kelas yang berhubungan dengan key , key pressed , key typed , dan key released
        for(int row=0;row<4;row++){
            for(int column=0;column<4;column++){
                boardTile[row][column] = new JLabel("",SwingConstants.CENTER); //Membuat jlabel , dan teks nya berada di tengah
                boardTile[row][column].setBorder(border); // men setting agar JLabel yang baru di buat memiliki border di pinggirnya
                boardTile[row][column].setFont(new Font("",Font.PLAIN,24)); //mengeset jenis font dan ukuran teks
                add(boardTile[row][column]); //barulah di tambahkan JLabel yang sudah tersetting tadi ke dalam grid yang sudah di isi
            }
        }
        setVisible(true); //memunculkan grid nya
        board.start(); // starter 2048 yaitu memasukan 2 anggka random 2 / 4 di grid
        update(); // mengupdate tampilan grid di dalam window 
    }

    public void update(){ //Pengupdate bentuk grid ke windows 
        for(int row=0;row<4;row++){
            for(int column=0;column<4;column++){
                boardTile[row][column].setText(String.valueOf(board.getBoardLabel()[row][column])); //mengikuti isi array Game 
                // boardTile[row][column].setForeground(new Color(100,200,30)); //kemudian mengeset warna background sesuai dengan angka 
            }
        }
    }

    public int getheight(){
        return this.height;
    }
    
    public void setheight(int height){
        this.height = height;
    }
    
    public int getwidth(){
        return this.width;
    }

    public void setwidth(int width){
        this.width = width;
    }

    public String gettitle(){
        return this.title;
    }

    public void settitle(String title){
        this.title = title;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int[][] copy = board.copyTableData();
        int keyCode = e.getKeyCode();
        if( keyCode == KeyEvent.VK_UP) {  //jika tombol atas
            board.up3();                    //melakukan fungsi up3 
        }
        else if(keyCode == KeyEvent.VK_DOWN){ //jika tombol bawah
            board.down();
        }
        else if(keyCode == KeyEvent.VK_LEFT){ //jika tombol kiri
            board.left();
        }
        else if(keyCode == KeyEvent.VK_RIGHT){ //jika tombol kanan
            board.right();
        }
        if(board.boardSpawnerChecker() == true || board.possibleMove() == true){
            if(Arrays.deepEquals(board.getBoardLabel(),copy)){  // jika tidak ada perbedaan (tabel sebelum di gerakan, dan tabel sesudah di gerakan)
                //bruh do nothingggg wkwk
            }else{ //nah jika ada yang bergerak
                board.spawnNumber(); //memunculkan angka 2 / 4 di tempat yang kosong 
                update();             // update windows untuk menyesuaikan dengan board game
                // board.printBoard();  //ini cuma untuk print di versi terminal nya saja
            }
        }else{
            System.out.println("GAME OVER"); //jika tidak ada tempat dan tidak ada move yang bisa ... 
            GameOver();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { //g pakai sih wkwk , 
        
    }

    public void GameOver(){ // saat game berakhir
        JFrame resetFrame = new JFrame("GAME OVER"); //menginisialisasi reset frame
        JButton resetButton = new JButton("RESET"); // menginisialisasi reset button
        resetFrame.setSize(300,100);  // mengeset ukuran fram untuk reset 
        resetButton.addActionListener(new ActionListener(){ // memasangkan button untuk dapat menerima sebuah tindakan (kasus ini di pencet)
            
            @Override
            public void actionPerformed(ActionEvent e) { //jika ada kegiatan (jika terpencet tombol nya ...)
                board.reset();  // board kita reset jadi 000 semua 
                board.start();  // kemudian di ulang kembali mengisi 2 angka random 2 / 4 di tempat random
                update();       // kita tampilkan di window
                resetFrame.setVisible(false); // frame nya kita hilangkan (Window untuk reset yang ada tombol button nya)
            }
        });
        resetFrame.add(resetButton); //kita tambahkan button ke dalam window 
        resetFrame.setVisible(true); // menampilkan nya saat game kalah (kalau ditanya kenapa di letakan di bawah tidak apa apa sih wkwk ... tak berbeda ..)
    }
}