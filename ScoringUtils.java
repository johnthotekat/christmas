
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Once the score matrix is composed , you can render the jTable 
 * The below methods returnMatrixForTableRendering() and getColumnHeaderForScoreTable() could help you compose the data in the required format
 * Here we are doing two things :
 * 1) getting the player names into a string array columnHeader[] , and that string array is passed as column header to jTable
 * 2) composing the new matrix to render in the jTable, the first column of the composed matrix is the player names and after this the scoreMatrix is appended  
 * @author johnthotekat
 *
 */

public class ScoringUtils {

	//Check the implementation in the main method for the usage 
	public static void main(String[] args) {
		
		//Creating SAMPLE score matrix for 3 players
		int[][] scoreMatrix = new int[3][3];
		scoreMatrix[0][1] = 12;
		scoreMatrix[0][2] = 12;
		scoreMatrix[1][0] = 31;
		scoreMatrix[1][2] = 14;
		scoreMatrix[2][0] = 15;
		scoreMatrix[2][1] = 17;

		//Creating a SAMPLE list of players which you already have
		List<Players> players = new ArrayList<Players>();
		Players p0 = new Players("John",0);
		Players p1 = new Players("Thomas",1);
		Players p2 = new Players("Antony",2);

		players.add(p0);
		players.add(p1);
		players.add(p2);
		
		JFrame f= new JFrame();
		
		JTable jt=renderJTable(players,scoreMatrix);
		JScrollPane sp = new JScrollPane(jt);
		
		f.add(sp);
		f.setSize(300, 400);
		f.setVisible(true);
		
	}
	
	/**
	 * This method returns an instance of jTable if you pass the list of players and score matrix
	 * @param players
	 * @param scoreMatrix
	 * @return
	 */
	public static JTable renderJTable(List<Players> players,int[][] scoreMatrix){
				String columnHeader[] = getColumnHeaderForScoreTable(players);
				
				//returnMatrixForTableRendering() method takes two parameters , one is list of players and the second is score matrix
				String [][] jTableData=returnMatrixForTableRendering(players,scoreMatrix);
				
				printFinalTableData(jTableData,players.size());
				
				JTable jt = new JTable(jTableData, columnHeader);
				jt.setBounds(30, 40, 200, 300);
				return jt;
				
	}
	
	/**
	 * This method composes a new String matrix to render into the jTable
	 * @param players
	 * @param scoreMatrix
	 * @return
	 */
	public static String [][] returnMatrixForTableRendering(List<Players> players,int[][] scoreMatrix){
		
		String[][] tableData=new String[players.size()][players.size()+1];
		int index=0;
		
		/*setting playernames in rowlevel to render matrix for jTable
			Populating the first column of all rows with the names as per our requirement
			[0,0] John
			[1,0] Thomas
			[2,0] Antony
		 */
		for(Players p:players){
			tableData[index][0]=p.getName();
			index++;
		}
		
		/* Setting the scores into matrix
			The scores from the score matrix is set on the index starting from 1 , value of j starts with 1 in the loop because the first columns has the player names
			the loop goes like below
			[0,1] score1
			[0,2] score2
			[0,3] score3
			we have defined a matrix of size N x N+1
		 */
		for (int i=0;i<players.size();i++){
			for(int j=1;j<=players.size();j++){
				tableData[i][j]=""+scoreMatrix[i][j-1];
			}
		}
		return (tableData);
	}
	
	/**
	 * Method to print the given matrix
	 * @param jTableData
	 * @param playerCount
	 */
	public static void printFinalTableData(String[][] jTableData,int playerCount){
		for(int i=0;i<playerCount;i++){
			System.out.println();
			for(int j=0;j<playerCount+1;j++){
				System.out.println("["+i+"]"+"["+j+"]"+jTableData[i][j]);
			}
		}
	}
	
	public static String[] getColumnHeaderForScoreTable(List<Players> playerList){
		String[] columnHeader=new String[playerList.size()+1];
		columnHeader[0]="";
		int index=1;
		for(Players p:playerList){
			columnHeader[index]=p.getName();
			index++;
		}
		
		return columnHeader;
	}
}
