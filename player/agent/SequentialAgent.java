package battleships.player.agent;

import java.util.Scanner;

import battleships.Game;
import battleships.Orientation;
import battleships.Tuple;
import battleships.message.BeenHitMessage;
import battleships.message.BeenMissedMessage;
import battleships.message.BeenSunkMessage;
import battleships.message.HitMessage;
import battleships.message.IllegalMessage;
import battleships.message.LossMessage;
import battleships.message.Message;
import battleships.message.MissMessage;
import battleships.message.SunkMessage;
import battleships.message.WinMessage;
import battleships.ship.Ship;

public class SequentialAgent extends Agent {

	protected int x = 0;
	protected int y = 0;
	
	public SequentialAgent(Game g, int size) {
		super(g,size);
	}

	@Override
	public void placeAll(Game g, Scanner sc) {
		int row = 0;
		int column = 0;
		for (Ship s : fleet){
			while(true){
				try{
					place(s,column,row,Orientation.RIGHT);
					column += s.getLength();
					break;
				} catch (Exception e){
					column = 0;
					row++;
					continue;
				}
			}
		}

	}

	@Override
	public Tuple<Integer, Integer> getAttackVector(Game g, Scanner sc) {
		Tuple<Integer,Integer> outp = new Tuple<Integer,Integer>(y,x);
		x++;
		if (x >= g.getSize()){
			x = 0;
			y++;
		}
		return outp;
	}
	
	public void message(Message m){
		if (m instanceof BeenHitMessage){
		} else if (m instanceof BeenMissedMessage){
		} else if (m instanceof BeenSunkMessage){
		} else if (m instanceof HitMessage){
		} else if (m instanceof IllegalMessage){
		} else if (m instanceof LossMessage){
		} else if (m instanceof MissMessage){
		} else if (m instanceof SunkMessage){
		} else if (m instanceof WinMessage){
		}
	}

}
