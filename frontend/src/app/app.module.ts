// Angular Libs
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

// NgHttpLoader
import { NgHttpLoaderComponentsModule } from 'ng-http-loader/components/ng-http-loader-components.module';
import { NgHttpLoaderServicesModule } from 'ng-http-loader/services/ng-http-loader-services.module';

// NgBootstrap
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

// HTTP Client
import { HttpClientModule } from '@angular/common/http';

// APP Components
import { AppComponent } from './app.component';
import { PlayersComponent } from './players/players.component';
import { FilterPipe } from './filter.pipe';
import { AlertsModule } from 'angular-alert-module';

// APP Services
import { PlayersService } from './services/players-service.service';



@NgModule({
  declarations: [
    AppComponent,
    PlayersComponent,
    FilterPipe
  ],
  imports: [
    NgbModule.forRoot(),
    AlertsModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgHttpLoaderServicesModule,
    NgHttpLoaderComponentsModule
  ],
  providers: [PlayersService],
  bootstrap: [AppComponent]
})
export class AppModule { }
