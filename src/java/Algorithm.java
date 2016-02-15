package com.company;// <<<<<<>>>>>> Alex is writting here <<<<<<<<>>>>>>


import com.company.DeliveryMain;


public class Algorithm{
    Board board = new Board();

    public void algorithm(){
        
        boolean stop;
        
        for (int i =0; i < DeliveryMain.numOfTurns; i++ )
        {
            for(int j = 0; !stop && j < board.droneTurns.length; j++){
                for(k = 0; !stop && k < board.droneTurns[0].length; k++){
                    if(board.droneTurns[j][k].available){
                        board.droneTurns[j][k] = false;
                        stop = true;
                    }
                        board.droneTurns[]
                }
            }

        }
    }
}
7