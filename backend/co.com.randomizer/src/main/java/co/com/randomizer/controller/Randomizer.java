/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.randomizer.controller;

import co.com.randomizer.model.IPlayers;
import co.com.randomizer.model.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author esteban.duran
 */
@CrossOrigin(
        methods = {
            RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowCredentials = "false"
)
@RestController
@RequestMapping(value = "/players")
public class Randomizer {

    @Autowired
    private IPlayers players;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Response> retrievePlayers() {
        ResponseEntity<Response> response;
        Response lsPlayers = players.retrievePlayers();
        response = new ResponseEntity<>(lsPlayers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/randomize", method = RequestMethod.POST)
    private ResponseEntity<Response> randomize(@RequestBody List<String> lsPlayers) {
        ResponseEntity<Response> response;
        Response result = players.randomizeIt(lsPlayers);
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    private ResponseEntity<Response> createPlayer(@PathVariable String name) {
        ResponseEntity<Response> response;
        Response lsPlayers = players.createPlayer(name);
        response = new ResponseEntity<>(lsPlayers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{oldName}/{newName}", method = RequestMethod.PUT)
    private ResponseEntity<Response> updatePlayer(@PathVariable String oldName, @PathVariable String newName) {
        ResponseEntity<Response> response;
        Response lsPlayers = players.updatePlayer(oldName, newName);
        response = new ResponseEntity<>(lsPlayers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    private ResponseEntity<Response> deletePlayer(@PathVariable String name) {
        ResponseEntity<Response> response;
        Response lsPlayers = players.deletePlayer(name);
        response = new ResponseEntity<>(lsPlayers, HttpStatus.OK);
        return response;
    }
}
