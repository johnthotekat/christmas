/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulatenew;

import static articulatenew.ScoringUtils.returnMatrixForTableRendering;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ashwatiteresajoseph
 */
public class GameScreen extends javax.swing.JFrame {
    Game game;
    List<String> words;
    List<Player> players;
    List<String> pairedIDList;
    int[][] scoreMatrix;
    DefaultTableModel tableModel;
    int currentCallee;
    int currentCaller;
    int round=0;
    Map<Integer,Integer> noOfRounds=new HashMap<>();
    /**
     * Creates new form GameScreen
     */
    
    public GameScreen(Game g) {
        game = g;
        initComponents();
        pairedIDList = new ArrayList<String>();
        int playerCount = game.getPlayerCount();
        
        g.setNoOfRounds(playerCount*playerCount-playerCount);
        scoreMatrix = new int[playerCount][playerCount];
        
        callers=game.getPlayerList();
        callees=game.getPlayerList();
        
        lbl_score.setVisible(false);
        lbl_word.setVisible(false);
        btn_correct.setVisible(false);
        btn_pass.setVisible(false);
        btn_nextround.setVisible(false);
        btn_startround.setVisible(false);
        
        for(Player p:game.getPlayerList()){
            noOfRounds.put(p.getPlayerId(),0);
        }
        
        allocateRoles();
        
        words = new ArrayList<String>();
        
        //The path code below wouldn't work on other computers, it only works on this one...has updated that he will be sending the right code soon
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\johnthotekat\\Desktop\\Jan-17\\ArticulateNew\\src\\articulatenew\\words.txt"));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        
        
    }
  
   List<Player> callers;
   List<Player> callees;
   
   int[][] playerCombinationMatrix;
   
    private void allocateRoles() {
        
        int playerListSize=game.getPlayerList().size();
        int sizeOfCaller=callers.size();
        int sizeOfCallee=callees.size();
        boolean playerCombinationSet=false;
        
        playerCombinationMatrix=new int[playerListSize][playerListSize];
        
        int randomCallerId=0;
        int randomCalleeId=0;
        
        //Actual logic goes here , this loop runs till it could set a combination , once all the conditions i
        while(!playerCombinationSet){
        randomCallerId= (new Random().nextInt(sizeOfCaller));
        randomCalleeId=(new Random()).nextInt(sizeOfCallee);
        
        if(randomCallerId!=randomCalleeId && (playerCombinationMatrix[randomCallerId][randomCalleeId]==0) && (noOfRounds.get(randomCallerId)<=game.getNoOfRounds()/game.getPlayerCount())){
            playerCombinationMatrix[randomCallerId][randomCalleeId]=1;
            playerCombinationSet=true;
            round++;
            System.out.println("Round 1: "+round);
            
        Player caller= game.getPlayerById(randomCallerId);
        Player callee=game.getPlayerById(randomCalleeId);
        
        if(noOfRounds.get(randomCallerId)==null){
        noOfRounds.put(randomCallerId,1);
        }
        else{
            noOfRounds.put(randomCallerId,noOfRounds.get(randomCallerId)+1);
        }
        
        currentCaller = randomCallerId;
        currentCallee = randomCalleeId;

        roleallocatermsg.setText(caller.getPlayerName() + " is calling to " + callee.getPlayerName() + ". Click the start button to start the next round.");
        roleallocatermsg.repaint();
        }
        else{
                    if(round==game.getNoOfRounds()){
                        System.out.println("Game over!");
        }
        }
        }
    }
    
    private void showTimer() {
        final long THIRTY_MINUTES = 10000;
        final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
        //final JLabel clock = new JLabel(sdf.format(new Date(THIRTY_MINUTES)),JLabel.CENTER);
        int x = 0;
        ActionListener al = new ActionListener(){
            long x = THIRTY_MINUTES - 1000;
            public void actionPerformed(ActionEvent ae){
                jLabel1.setText(sdf.format(new Date(x)));
                if (jLabel1.getText().equals("30 : 00")) {
                    // new code to set JTable visible and code to update JTable with scores from previous rounds 
                    String[][] jTableData = ScoringUtils.returnMatrixForTableRendering(game.getPlayerList(), scoreMatrix);
//                    ScoringUtils.printFinalTableData(jTableData, game.getPlayerList().size());
                    JTable jt=ScoringUtils.renderJTable(game.getPlayerList(), scoreMatrix);
                    tbl_scores.setModel(jt.getModel());
                    tbl_scores.repaint();
                    
                    btn_nextround.setVisible(true);
                    btn_correct.setVisible(false);
                    btn_pass.setVisible(false);
                    
                    return;
                    
                }
                x -= 1000;
            }
        };
        new javax.swing.Timer(1000, al).start();
        //JPanel jp = new JPanel();
        //jp.add(clock);
        //getContentPane().add(jp);
        pack();
    }
    
    private void restart() {
        showTimer();
        setRandomWord(); 
    }
    
    private void setScoreLabel(Player p1, Player p2) {
        lbl_score.setText("Score: " + p1.getPlayerScore());
    
    }
    
    
    private void setRandomWord() {
        lbl_word.setText("");
        lbl_word.setText(getRandomWord());
    
    }
    
    private String getRandomWord() {
        Random r = new Random();
        return words.get(r.nextInt(words.size()));
        
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        roleallocatermsg = new javax.swing.JLabel();
        btn_start = new javax.swing.JButton();
        lbl_word = new javax.swing.JLabel();
        btn_pass = new javax.swing.JButton();
        btn_correct = new javax.swing.JButton();
        lbl_score = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_scores = new javax.swing.JTable();
        btn_nextround = new javax.swing.JButton();
        btn_startround = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roleallocatermsg.setText("jLabel1");

        btn_start.setText("Start");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        lbl_word.setText("jLabel1");

        btn_pass.setText("Pass");
        btn_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_passActionPerformed(evt);
            }
        });

        btn_correct.setText("Correct");
        btn_correct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_correctActionPerformed(evt);
            }
        });

        lbl_score.setText("Score: ");

        jLabel1.setText("Timer");

        tbl_scores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_scores);

        btn_nextround.setText("Next Round");
        btn_nextround.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextroundActionPerformed(evt);
            }
        });

        btn_startround.setText("Start Round");
        btn_startround.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startroundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(lbl_word))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(lbl_score)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_pass)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_nextround)
                            .addComponent(btn_correct))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(btn_startround)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(btn_start))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(roleallocatermsg)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(roleallocatermsg)
                .addGap(18, 18, 18)
                .addComponent(btn_start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nextround)
                    .addComponent(btn_startround))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_score)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_word)
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pass)
                    .addComponent(btn_correct))
                .addGap(106, 106, 106))
        );

        pack();
    }// </editor-fold>                        

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        lbl_score.setVisible(true);
        lbl_word.setVisible(true);
        btn_correct.setVisible(true);
        btn_pass.setVisible(true);
        btn_start.setVisible(false);
        btn_nextround.setVisible(false);
        
        showTimer();
        setRandomWord();
    }                                         

    private void btn_correctActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        // increment scores of both players playing the current round and then set the random word 
        // keep in mind, words are currently randomly chosen from a short list, how do we make sure shown words don't appear, you might probably need a bigger word list
        // can we use the getPlayerScore() method to access and change the current players' scores here before the setRandomWord() method is shown
        Player p1 = game.getPlayerById(currentCaller);
        Player p2 = game.getPlayerById(currentCallee);

        System.out.println("Player ID: " + p1.playerId + " Player Name: " + p1.getPlayerName());
        System.out.println("Player ID: " + p2.playerId + " Player Name: " + p2.getPlayerName());
        
        for (int i=0;i<scoreMatrix.length;i++){
            for(int j=0;j<scoreMatrix[i].length;j++){
		System.out.print(scoreMatrix[i][j]+" ");
            }
            System.out.println();
	}
        
        p1.setPlayerScore(p1.getPlayerScore() + 1);
        p2.setPlayerScore(p2.getPlayerScore() + 1);  
        
        int currentScore = scoreMatrix[p1.getPlayerId()][p2.getPlayerId()];
        
        scoreMatrix[p1.getPlayerId()][p2.getPlayerId()] = currentScore + 1;
               
        setScoreLabel(p1, p2);
        
        setRandomWord();
        
        
        
    }                                           

    private void btn_passActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        setRandomWord();
       
    }                                        

    private void btn_nextroundActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        allocateRoles();
        btn_startround.setVisible(false);
        btn_correct.setVisible(true);
        btn_nextround.setVisible(false);
        btn_pass.setVisible(true);
        
    }                                             

    private void btn_startroundActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        restart();
        allocateRoles();
        btn_correct.setVisible(true);
        btn_pass.setVisible(true);
        btn_startround.setVisible(false);
        btn_nextround.setVisible(false);
    }                                              

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new GameScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btn_correct;
    private javax.swing.JButton btn_nextround;
    private javax.swing.JButton btn_pass;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_startround;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_score;
    private javax.swing.JLabel lbl_word;
    private javax.swing.JLabel roleallocatermsg;
    private javax.swing.JTable tbl_scores;
    // End of variables declaration                   
}
