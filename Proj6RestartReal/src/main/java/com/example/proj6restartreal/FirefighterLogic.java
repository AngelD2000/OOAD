package com.example.proj6restartreal;

import java.util.ArrayList;

public class FirefighterLogic {
    Game game = null;
    Company company = null;
    Integer actions = 5;

    FirefighterLogic(Game game){
        this.game = game;
        this.company = new Company(game);
    }
    public Integer getActions(){
        return actions;
    }

    /**
     * Gets the array of legal actions given the targeted location
     * @param target Array of [x, y] location on the map
     * @ return An arraylist of legal actions
     */
    ArrayList<Integer> getActions(Square target){
        ArrayList<Integer> answer = new ArrayList<Integer>(0);
        Square location = company.getActiveLocation();
        Map map = game.getMap();
        Integer adj = map.areAdjacent(target, location);
        //Do nothing if squares aren't adjacent
        if(adj != Util.notAdjacent){
            //If wall between, only action is chop
            if(adj == Util.wallBetween){
                answer.add(Util.chop);
            }
            else{
                if(target.hasFire() || target.hasSmoke()){
                    answer.add(Util.hose);
                }
                //Can be moved to
                else if (!target.hasFF()){
                    answer.add(Util.move);
                    if(location.hasVictim()){
                        answer.add(Util.drag);
                    }
                }
            }
        }
        return answer;
    }

    /**
     * Cycles though the active firefighter
     */
    public void nextTurn(){
        company.nextFirefighter(actions);
        actions = 5;
    }

    /**
     * Have the active firefighter chop the given wall
     */
    public void chop(Square square){
        Edge edge = game.getMap().getEdge(square, company.getActiveLocation());
        edge.doDamage(game);
        actions -= 1;
    }

    public void hose(Square square){
        game.getMap().updateSquare(square, Util.removeFire);
        actions -= 1;
    }

    public void move(Square square){
        company.move(square);
        actions -= 1;
    }

    public void drag(Square square){
        Map map = game.getMap();
        Square location = company.getActiveLocation();
        map.updateSquare(location, Util.removeVict);
        map.updateSquare(square, Util.addVict);
        move(square);
        actions -= 2;
    }
}
