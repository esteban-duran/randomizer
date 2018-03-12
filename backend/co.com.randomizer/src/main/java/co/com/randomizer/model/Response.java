/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.randomizer.model;

import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author esteban.duran
 */
@Repository
public class Response {
    
    private String message;
    private Set players;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set getPlayers() {
        return players;
    }

    public void setPlayers(Set players) {
        this.players = players;
    }
    
    
}
