import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-cooper-test-result',
  standalone: true,
  imports: [],
  templateUrl: './cooper-test-result.component.html',
  styleUrl: './cooper-test-result.component.scss'
})
export class CooperTestResultComponent {

  @Input() athleteId!: string; 

}
