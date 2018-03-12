/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.randomizer.model;

import java.util.List;


/**
 *
 * @author esteban.duran
 */
public interface IPlayers {

    public Response createPlayer(String name);

    public Response retrievePlayers();
    
    public Response updatePlayer(String oldName, String newName);
    
    public Response deletePlayer(String name);
    
    public Response randomizeIt(List<String> lsPlayers);
    
}
