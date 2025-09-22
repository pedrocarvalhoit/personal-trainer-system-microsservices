import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-cooper-test-historic',
  standalone: true,
  imports: [],
  templateUrl: './cooper-test-historic.component.html',
  styleUrl: './cooper-test-historic.component.scss'
})
export class CooperTestHistoricComponent {

  @Input() athleteId!: string; 

}
