import java.util.Arrays;
import java.util.LinkedList;

public class DepthFirstSearch {
	int goal[][];
	int pegs[][];
	boolean check = false;
	int count;
	LinkedList<int[][]> board = new LinkedList<int[][]>();
	LinkedList<Integer> lastMove = new LinkedList<Integer>();
	
	int global_i = 0;
	int global_j = 0;
	int global_function = 0;
	
public DepthFirstSearch(int[][] goal, int[][] pegs){
	this.goal = goal;
	this.pegs = pegs;
	printBoard(pegs);
	long start = System.currentTimeMillis();
	do{
		getAvaiable(global_i,global_j,global_function);
	}while(check == false);
	long end = (System.currentTimeMillis() - start) / 1000;
	System.out.println("It took "+end+" second to run with total of "+count+" iteration.");
}

public void getAvaiable(int last_i, int last_j, int functionUsed){
	//System.gc();
	int i = 0;
	int j = 0;
	int functionNumber = 0;
	boolean localCheck = false;
	if(check == false){
		outer:
		for(i = last_i; i < pegs.length; i++){
	    	for(j = last_j; j < pegs[i].length; j++){
	    		if((pegs[i][j]) == 1){
	    			if(i+2 < pegs.length && pegs[i+2][j]==0 && functionUsed <= 1){
	    				if(pegs[i+1][j] == 1){
	    					board.add(saveBoardToLinkedList(pegs));
	    					pegs[i][j] = 0;
	    					pegs[i+1][j] = 0;
	    					pegs[i+2][j] = 1;
	    					localCheck = true;
	    					functionNumber = 1;
	    					break outer;
	    				}
	    			}
	    			if((i-2 >= 0 && pegs[i-2][j]==0) && functionUsed <= 2){
	    				if(pegs[i-1][j] == 1){
	    					board.add(saveBoardToLinkedList(pegs));
	    					pegs[i][j] = 0;
	    					pegs[i-1][j] = 0;
	    					pegs[i-2][j] = 1;
	    					localCheck = true;
	    					functionNumber = 2;
	    					break outer;
	    				}
	    			}
	    			if(j+2 < pegs.length && pegs[i][j+2] ==0 && functionUsed <= 3){
	    				if(pegs[i][j+1] == 1){
	    					board.add(saveBoardToLinkedList(pegs));
	    					pegs[i][j] = 0;
	    					pegs[i][j+1] = 0;
	    					pegs[i][j+2] = 1;
	    					localCheck = true;
	    					functionNumber = 3;
	    					break outer;
	    				}
	    			}
	    			if((j-2 >= 0 && pegs[i][j-2]==0) && functionUsed <= 4){
	    				if(pegs[i][j-1]==1){
	    					board.add(saveBoardToLinkedList(pegs));
	    					pegs[i][j] = 0;
	    					pegs[i][j-1] = 0;
	    					pegs[i][j-2] = 1;
	    					localCheck = true;
	    					functionNumber = 4;
	    					break outer;
	    				}
	    			}
	    		}
	    		functionUsed = 0;
	    		last_j = 0;
	    	}
	    }
		// make save
		if(localCheck == true){
			// Debug purpose
			//System.out.println("\nCount: "+count+"\nWork at: ("+i+", "+j+")");
			//System.out.println("Function: "+functionNumber);
			//printBoard(pegs); // TODO Save everymove into linkedlist!!!!!!!
			//
			checkGoal(pegs);
			lastMove.add(count);
			lastMove.add(functionNumber);
			lastMove.add(j);
			lastMove.add(i);
			count++;
			global_i = 0;
			global_j = 0;
			global_function = 0;
			//return;
			//getAvaiable(pegs,(int)0,(int)0,(int)0);
		}else{
			pegs = board.pollLast();
			global_i = lastMove.pollLast();
			global_j = lastMove.pollLast();
			global_function = lastMove.pollLast() + 1;
			int localCount = lastMove.pollLast();
			
			//System.out.println("!!!!!Retrieve Board!!!!!"+ "\n~~~~~ Looking from ("+global_i+", "+global_j+")"+ "\n-----Function Start From: "+global_function+ "\n*****From Count:"+localCount);
			//printBoard(pegs);
			//return;
			//getAvaiable(local_i, local_j, localfunctionNumber);
			
			
		}
	}
}

public int[][] saveBoardToLinkedList(int[][] pegs){
	int[][] result = new int[pegs.length][pegs.length];
	for(int i = 0; i < pegs.length; i++){
		for(int j = 0; j < pegs[i].length; j++){
			result[i][j] = pegs[i][j];
		}
	}
return result;
}
public void checkGoal(int[][] pegs){
	if(Arrays.deepEquals(goal, pegs)){
		check = true;
		System.out.println("Solution Found");
		printBoard(pegs);
	}
}
public void printBoard(int[][] pegs){
	for(int i = 0; i < pegs.length;i++){
		for(int j = 0; j < pegs[i].length; j++){
			if(pegs[i][j] == 2)
				System.out.print("  ");
			else if(pegs[i][j] == 1)
				System.out.print("1 ");
			else if(pegs[i][j] == 0)
				System.out.print("0 ");
		}
		System.out.println("");
	}
}
}