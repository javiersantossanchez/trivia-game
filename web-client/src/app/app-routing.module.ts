import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ScoreBoardComponent } from './score-board/score-board.component';
import { GameBoardComponent } from './game-board/game-board.component';
import { InitialFormComponent } from './initial-form/initial-form.component';

const routes: Routes = [
  { path: '', component: InitialFormComponent},
  { path: 'app-score-board', component: ScoreBoardComponent }, // Routing for score board
  { path: 'app-game-board', component: GameBoardComponent }, // Routing for score board
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
