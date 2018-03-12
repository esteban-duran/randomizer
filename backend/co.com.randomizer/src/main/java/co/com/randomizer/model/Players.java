/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.randomizer.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author esteban.duran
 */
@Component
public class Players implements IPlayers {

    private Set<String> players;

    public final static String FILE_NAME = "players.txt";
    public final static String ERROR_FILE = "ERROR READING FROM FILE";
    public final static String ERROR_RANDOM = "THE LIST MUSN'T BE NULL OR EMPTY";

    public final static String SUCCESS = "SUCCESS";
    public final static String FAILURE = "FAILURE";

    @Override
    public synchronized Response createPlayer(String name) {
        Response response = new Response();
        if (players == null) {
            retrievePlayers();
        }
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
        boolean add = players.add(name);
        try {
            if (add) {
                writeToFile(name);
            }
            retrievePlayers();
            response.setMessage(SUCCESS);
        } catch (IOException ex) {
            response.setMessage(FAILURE);
        }
        response.setPlayers(players);
        return response;
    }

    @Override
    public synchronized Response deletePlayer(String name) {
        if (players == null) {
            retrievePlayers();
        }
        Response response = new Response();
        if (name != null && !name.isEmpty()) {
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
            boolean remove = players.remove(name);
            try {
                if (remove) {
                    for (String player : players) {
                        writeToFile(player);
                    }
                    retrievePlayers();
                    response.setMessage(SUCCESS);
                } else {
                    response.setMessage(FAILURE);
                }
            } catch (IOException ex) {
                response.setMessage(FAILURE);
            }
        }
        response.setPlayers(players);
        return response;
    }

    @Override
    public synchronized Response retrievePlayers() {
        Response response = new Response();
        if (players == null) {
            players = new TreeSet<>();
        }
        //players.clear();
        try {
            readFromFile();
            response.setMessage(SUCCESS);
        } catch (IOException ex) {
            response.setMessage(FAILURE);
        }
        response.setPlayers(players);
        return response;
    }

    @Override
    public Response updatePlayer(String oldName, String newName) {
        Response response = new Response();
        if (players == null) {
            retrievePlayers();
        }
        if (newName != null && !newName.isEmpty()) {
            String name = Character.toUpperCase(oldName.charAt(0)) + oldName.substring(1).toLowerCase();
            boolean remove = players.remove(name);
            if (remove) {
                response = createPlayer(newName);
            } else {
                response.setMessage(FAILURE);
                response.setPlayers(players);
            }
        }
        return response;
    }

    @Override
    public Response randomizeIt(List<String> lsPlayers) {
        Response response = new Response();
        Set<String> winner = new TreeSet<>();
        if (lsPlayers == null) {
            response.setMessage(FILE_NAME);
            response.setPlayers(winner);
            return response;
        } else if (lsPlayers.isEmpty()) {
            response.setMessage(FILE_NAME);
            response.setPlayers(winner);
            return response;
        }
        SecureRandom rnd = new SecureRandom();
        int index = rnd.nextInt(lsPlayers.size());

        int i = 0;
        for (String player : lsPlayers) {
            if (index == i) {
                winner.add(player);
                break;
            }
            i++;
        }
        response.setMessage(SUCCESS);
        response.setPlayers(winner);
        return response;
    }

    private synchronized void readFromFile() throws FileNotFoundException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                players.add(line);
                
                line = reader.readLine();
            }
        }
    }

    private synchronized void writeToFile(String newLine) throws IOException {
        if (newLine != null && !newLine.trim().isEmpty()) {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(FILE_NAME).getFile());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.newLine();
                writer.write(newLine);
                writer.flush();
                writer.close();
            }
        }
    }
}
