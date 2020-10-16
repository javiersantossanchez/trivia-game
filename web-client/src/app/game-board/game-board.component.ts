import { Question } from './../model/question';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-board',
  templateUrl: './game-board.component.html',
  styleUrls: ['./game-board.component.sass']
})
export class GameBoardComponent implements OnInit {

  questionObservable: Observable<Question>;

  public selectedOption: string;

  public score: number;

  constructor(public httpClient: HttpClient,private router: Router) { }

  ngOnInit(): void {
      this.getQuestion();
  }

  getQuestion(){
    console.log("getQuestion");
    this.questionObservable = this.httpClient
    .get<Question>('http://localhost:8080/api/question',{ withCredentials: true });
  }

  sendAnswer(): void{

    this.httpClient
    .post('http://localhost:8080/api/question/levels/answer/' + this.selectedOption,{},{withCredentials: true })
      .subscribe((resp : number) =>  {this.score = resp; this.getQuestion()});
  }

  clickOnFinishButton(): void{
    this.httpClient
    .post('http://localhost:8080/api/question/finish/',{},{withCredentials: true })
      .subscribe(() =>  {console.log("sadads"); this.router.navigate(["/app-score-board"]);});
  }

}
