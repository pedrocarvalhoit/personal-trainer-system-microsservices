import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-seated-low-row',
  standalone: true,
  imports: [],
  templateUrl: './seated-low-row.component.html',
  styleUrl: './seated-low-row.component.scss'
})
export class SeatedLowRowComponent {

  @Input() athleteId!: string; 

}
