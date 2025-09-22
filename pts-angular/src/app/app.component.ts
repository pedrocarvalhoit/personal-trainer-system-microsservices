import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'pts-angular';

  constructor(private router: Router){}

  login(){
    this.router.navigate(['login'])
  }

  register(){
    this.router.navigate(['register'])
  }
}
