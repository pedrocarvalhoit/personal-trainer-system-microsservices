import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-deadlift',
  standalone: true,
  imports: [],
  templateUrl: './deadlift.component.html',
  styleUrl: './deadlift.component.scss'
})
export class DeadliftComponent {

  @Input() athleteId!: string; 

}
