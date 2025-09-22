import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-cooper-test',
  standalone: true,
  imports: [],
  templateUrl: './cooper-test.component.html',
  styleUrl: './cooper-test.component.scss'
})
export class CooperTestComponent {

  @Input() athleteId!: string; 

}
