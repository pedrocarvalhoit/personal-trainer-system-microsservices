import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-back-squat',
  standalone: true,
  imports: [],
  templateUrl: './back-squat.component.html',
  styleUrl: './back-squat.component.scss'
})
export class BackSquatComponent {

  @Input() athleteId!: string; 

}
