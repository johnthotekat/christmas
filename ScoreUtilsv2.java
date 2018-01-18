import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ScoreUtilsv2 {
	public static void main(String[] args) {
		
		//SAMPLE MATRIX and SAMPLE playerSize
		int playerSize=4;
		int [][] scoreMatrix= new int[playerSize][playerSize];
		
		scoreMatrix[0][1]=1;
		scoreMatrix[0][2]=9;
		scoreMatrix[0][3]=14;
		
		scoreMatrix[1][0]=5;
		scoreMatrix[1][2]=6;
		scoreMatrix[1][3]=7;
		
		scoreMatrix[2][0]=8;
		scoreMatrix[2][1]=9;
		scoreMatrix[2][3]=10;
		
		scoreMatrix[3][0]=11;
		scoreMatrix[3][1]=12;
		scoreMatrix[3][2]=13;
		
		Map<Integer,Integer> playerScores=getPlayerScores(scoreMatrix, playerSize);
		System.out.println(playerScores);
		
		List<Entry<Integer, Integer>> listOfWinners=getMaxEntry(playerScores);
		
		if(listOfWinners.size()==1){
			System.out.println("The winner is Player " + listOfWinners.get(0).getKey() + " with score "+listOfWinners.get(0).getValue());
		}
		
		else{
			System.out.println("We have a draw between ");
			for(Entry<Integer, Integer> e: listOfWinners){
				System.out.println(e.getKey()+",");
			}
		}
	}
	
	/**
	 * This method intakes the scoreMatrix , calculates the rowsum and column sum for each players
	 * and returns a Map<Integer,Integer> having the totalScore(rowsum+colsum) , key of the map is playerID and the value is total player score
	 * @param scoreMatrix
	 * @param playerSize
	 * @return
	 */
	public static Map<Integer,Integer> getPlayerScores(int[][] scoreMatrix,int playerSize){
		Map<Integer,Integer> scoreMap=new HashMap<Integer,Integer>();
		for(int i=0;i<playerSize;i++){
			int rowSum = 0;
			int columnSum = 0;
			int totalScore=0;
			for(int j=0;j<playerSize;j++){
				if(i!=j){
					rowSum+=scoreMatrix[i][j];
					columnSum+=scoreMatrix[j][i];
				}
				totalScore=columnSum+rowSum;
				scoreMap.put(i, totalScore);
			}
		}
		return scoreMap;
	}
	
	/**
	 * This method takes in a map of Players and their scores and returns a list of Player(s) having the highest score
	 * This parameter map (key:PlayerId and value:totalScore) has the total scores of the each players after the row sum and column sum is added.
	 * If there is a draw , the list will contain more than one entry which has the players and their respecitive scores  
	 * @param map
	 * @return List<Entry<Integer, Integer>>
	 */
	public static List<Entry<Integer, Integer>> getMaxEntry(Map<Integer, Integer> map){        
	    List<Entry<Integer, Integer>> listOfWinners=new  ArrayList<Entry<Integer, Integer>> ();
	    Integer max = Collections.max(map.values());

	    for(Entry<Integer, Integer> entry : map.entrySet()) {
	        Integer value = entry.getValue();
	        if(null != value && max == value) {
	            listOfWinners.add(entry);
	        }
	    }
	    return listOfWinners;
	}
}
