import { Component } from '@angular/core';

// NgHttpLoader
import { Spinkit } from 'ng-http-loader/spinkits';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Randomizer';
  public spinkit = Spinkit;
}
