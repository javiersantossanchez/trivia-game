import { environment } from './../../environments/environment';
import { Score } from './../model/score';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, flatMap } from 'rxjs/operators';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-score-board',
  templateUrl: './score-board.component.html',
  styleUrls: ['./score-board.component.sass']
})
export class ScoreBoardComponent implements OnInit {

  scoreObservable: Observable<Score[]>;

  constructor(public httpClient: HttpClient) {}

  ngOnInit(): void {
    this.getScores();
  }

  getScores(): void{

    this.scoreObservable = this.httpClient.get<Score[]>('http://' + environment.backendserver + ':8080/api/score',{withCredentials: true });
  }

  clickOnBackButton(): void{

  }

}
