import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { PlayersService } from '../services/players-service.service';

import { Player } from '../model/Player';
import { BackEndResponse } from '../model/response';

import { AlertsService } from 'angular-alert-module';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {

  backEndResponse: BackEndResponse;

  players: Array<Player>;

  newPlayer: Player;

  nPlayer: Player;

  winner: String;

  disabled: boolean = true;

  oldName: String = "";

  selected: boolean = false;

  searchText: String = "";

  @Input() title: String;

  @Input() pjField: String;

  constructor(private playersService: PlayersService, private alerts: AlertsService) {
    this.players = [];
    this.getPlayers();
  }

  ngOnInit() {
  }

  createPlayer(name: String) {
    this.newPlayer = new Player();
    this.newPlayer.name = name;
    this.newPlayer.enabled = true;
  }

  // RESTFUL SERVICE CALL

  getPlayers(): void {
    this.playersService.getPlayers()
      .subscribe(
        response => {
          this.backEndResponse = response;
          this.backEndResponse.players.forEach(name => {
            this.createPlayer(name);
            this.players.push(this.newPlayer);
          });
        }
      );
  }

  addPlayer(): void {
    if (this.pjField) {

      this.players = [];
      this.createPlayer(this.pjField);
      this.playersService.addPlayer(this.newPlayer)
        .subscribe(
          response => {
            this.pjField = '';
            this.backEndResponse = response;
            this.backEndResponse.players.forEach(p => {
              this.createPlayer(p);
              this.players.push(this.newPlayer);
            });
          }
        );
    }
    else {
      this.alerts.setMessage('Ingrese un valor válido', 'alert-danger');
    }
  }

  editPlayer(oldPlayer: Player) {
    oldPlayer.enabled = false;
    this.oldName = oldPlayer.name;
    this.disabled = false;
    this.buttonDisabled();
  }

  updatePlayer(newName: string, oldPlayer: Player) {
    this.players = [];
    let newPlayer: Player = new Player();
    newPlayer.name = newName;
    oldPlayer.name = this.oldName;
    if (newPlayer.name) {
      this.playersService.updatePlayer(oldPlayer, newPlayer)
        .subscribe(
          response => {
            this.backEndResponse = response;
            this.backEndResponse.players.forEach(p => {
              this.createPlayer(p);
              this.players.push(this.newPlayer);
            });
          }
        );
    }
    this.oldName = "";
    this.disabled = true;
  }

  cancelEdit(oldPlayer: Player) {
    oldPlayer.enabled = true;
    this.disabled = true;
  }

  removePlayer(player: Player) {
    this.players = [];
    this.playersService.removePlayer(player)
      .subscribe(
        response => {
          this.backEndResponse = response;
          this.backEndResponse.players.forEach(p => {
            this.createPlayer(p);
            this.players.push(this.newPlayer);
          });
        }
      );
  }

  randomize(): void {
    let selected: String[] = this.getSelected();
    this.playersService.randomize(selected)
      .subscribe(
        response => {
          this.winner = response.players[0];

          if (this.winner != undefined) {
            let ganador: string = this.winner.toString();

            for (var i = 0; i < this.players.length; i++) {
              if (this.players[i].name === ganador) {
                this.players[i].selected = false;
              }
            }

            this.alerts.setMessage(ganador, 'alert-success');
          }
          else {
            this.alerts.setMessage('Seleccione al menos un jugador', 'alert-danger');
          }
        }
      );
  }

  getSelected(): String[] {
    let selected: String[] = [];
    this.players.forEach(p => {
      if (p.selected) {
        selected.push(p.name);
      }
    });
    return selected;
  }

  buttonDisabled(): boolean {
    return !this.disabled;
  }

  selectAll() {
    for (var i = 0; i < this.players.length; i++) {
      this.players[i].selected = true;
    }
    this.selected = true;
  }

  deselectAll() {
    for (var i = 0; i < this.players.length; i++) {
      this.players[i].selected = false;
    }
  }

  isSelected(): boolean {
    for (var i = 0; i < this.players.length; i++) {
      if (this.players[i].selected) {
        return this.selected = true;
      }
    }
  }
}
