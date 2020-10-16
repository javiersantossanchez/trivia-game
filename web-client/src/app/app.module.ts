import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgLetModule } from '@ngrx-utils/store';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InitialFormComponent } from './initial-form/initial-form.component';
import { ScoreBoardComponent } from './score-board/score-board.component';
import { GameBoardComponent } from './game-board/game-board.component';


@NgModule({
  declarations: [
    AppComponent,
    InitialFormComponent,
    ScoreBoardComponent,
    GameBoardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgLetModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
