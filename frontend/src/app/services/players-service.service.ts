import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

import { Player } from '../model/Player';
import { BackEndResponse } from '../model/response';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
//const RANDOMIZER_API_URL = 'https://asmed59:9443/co.com.randomizer.web/rest/players/';
//const RANDOMIZER_API_URL = 'http://localhost:8080/co.com.randomizer/rest/players/';
const RANDOMIZER_API_URL = '/co.com.randomizer/rest/players/';

@Injectable()
export class PlayersService {

  constructor(private http: HttpClient) { }

  getPlayers(): Observable<BackEndResponse> {

    return this.http.get<BackEndResponse>(RANDOMIZER_API_URL, httpOptions).pipe(
      tap(response => console.log('GET RESPONSE', response))
    );
  }

  addPlayer(player: Player): Observable<BackEndResponse> {
    console.log('POST REQUEST', player);
    return this.http.post<BackEndResponse>(RANDOMIZER_API_URL + player.name, httpOptions).pipe(
      tap(players => console.log('POST RESPONSE', players))
    );
  }

  removePlayer(player: Player): Observable<BackEndResponse> {
    return this.http.delete<BackEndResponse>(RANDOMIZER_API_URL + player.name, httpOptions).pipe(
      tap(players => console.log('DELETE RESPONSE', players))
    );
  }

  updatePlayer(oldPlayer: Player, newPlayer: Player): Observable<BackEndResponse> {
    return this.http.put<BackEndResponse>(RANDOMIZER_API_URL + oldPlayer.name + '/' + newPlayer.name, httpOptions).pipe(
      tap(players => console.log('PUT RESPONSE', players))
    );
  }

  randomize(players: String[]): Observable<BackEndResponse> {
    return this.http.post<BackEndResponse>(RANDOMIZER_API_URL + 'randomize', players, httpOptions).pipe(
      tap(players => console.log('POST RANDOMIZE RESPONSE', players))
    );
  }
}
