import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, } from 'rxjs/operators';
import { Router } from '@angular/router';


@Component({
  selector: 'app-initial-form',
  templateUrl: './initial-form.component.html',
  styleUrls: ['./initial-form.component.sass']
})
export class InitialFormComponent implements OnInit {

  public levelsObservable: Observable<string[]>;

  public userName: string;

  public level: string;

  constructor(public httpClient: HttpClient, private router: Router){}

  ngOnInit(): void {
    this.sendGetRequest();
  }

  sendGetRequest(): void{
   this.levelsObservable = this.httpClient
    .get<string[]>('http://localhost:8080/api/configuration/levels', {withCredentials: true })
    .pipe(map(data => data));


  }

  clickOnStartButton(): void{
    console.log(this.userName);
    this.httpClient
    .post('http://localhost:8080/api/question/init', {userName: this.userName, level: this.level}, {withCredentials: true })
      .subscribe((response) =>  {if (response){this.router.navigate(['/app-game-board']); }});
  }


}
