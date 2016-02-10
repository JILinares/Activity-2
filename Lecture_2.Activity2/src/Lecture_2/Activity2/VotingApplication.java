//Jose Linares
package Lecture_2.Activity2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class VotingApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathCandidates= "./src/Lecture_2/Activity2/candidate.txt"; 
		String pathVoters = "src/Lecture_2/Activity2/voters.txt";
		Candidate[] candidates =null;
		Voter[] voters =null;
		try{
			candidates = getCandidates(pathCandidates);
			voters = getVoters(pathVoters);
			Candidate winner = getWinner(candidates , voters);
			JOptionPane.showMessageDialog(null, "The winner is: " + winner.toString());
		}
		catch(FileNotFoundException e){
			
		}catch(IOException e){
			
		}
	}
	
	
	public static Candidate[] getCandidates(String path) throws FileNotFoundException,
	IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line; 
		Scanner scan = null; 
		int count=0;
		while((line = br.readLine()) !=null){
			count++;
		}
		
		br = new BufferedReader(new FileReader(new File(path)));
		Candidate c; 
		String name;
		int code; 
		Candidate[] candids = new Candidate[count];
		int i=0;
		while((line = br.readLine()) !=null){
			scan =new Scanner(line);
			scan.useDelimiter(",");
			name = scan.next(); 
			code = Integer.parseInt(scan.next().trim());
			c= new Candidate(name , code);
			candids[i++]=c;
		}

		return candids;
		
	}
	
	public static Voter[] getVoters(String path) throws FileNotFoundException,
	IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line; 
		Scanner scan = null; 
		int count=0;
		while((line = br.readLine()) !=null){
			count++;
		}
		
		br = new BufferedReader(new FileReader(new File(path)));
		Voter v; 
		char gender;
		int code; 
		Voter[] voters = new Voter[count];
		int i=0;
		while((line = br.readLine()) !=null){
			scan =new Scanner(line);
			scan.useDelimiter(",");
			gender = scan.next().trim().charAt(0); 
			code = Integer.parseInt(scan.next().trim());
			v= new Voter(gender);
			v.setVote(code);
			voters[i++]=v;
		}

		return voters;
		
	}
	
	public static Candidate getWinner(Candidate[] candids, Voter[] voters){
		//TODO
            //Initialized varibles when determine the votes that appear the most in the file.
            int maxVote = 0;
            int maxVoteCount = 0;
            int tempVoteCount = 0;
            int tempVote = 0;
            //Extract the votes into a int array from the voters object.
            int[] votes = new int[voters.length];
            for(int x = 0; x < voters.length; x++){
                votes[x] = voters[x].getVote();
            }
            //Used a Insertion Sort to put the array into accending order.
            for(int i = 1; i <(votes.length-1); i++){
                int element = votes[i];
                int j = i;
                while(j > 0 && votes[j-1] > element){
                    votes[j] = votes[j-1];
                    j--;         
                }
                votes[j] = element;
            }
            //Set the initial count for votes to 1
            tempVoteCount = 1;
            //This for loop will record the number of votes a certain candidate has until a change. When a change is detected, the count resets.
            for(int z = 0; z < votes.length - 1; z++){
                if(votes[z] == votes[z+1]){
                    tempVoteCount++;
                    tempVote = votes[z];
                }else{
                    if(tempVoteCount > maxVoteCount){
                        maxVoteCount = tempVoteCount;
                        maxVote = tempVote;  
                    }
                    tempVoteCount = 1;
                }
                        
            }
            //This if statement is here incase the count continues after the array is done.
            if(tempVoteCount > maxVoteCount){
                maxVoteCount = tempVoteCount;
                maxVote = tempVote;  
            }
            //Prints the contents in the array
            System.out.println(Arrays.toString(votes));
           
            return candids[maxVote-1] ;
		
		
	}
	

}
