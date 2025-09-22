import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-bench-press',
  standalone: true,
  imports: [],
  templateUrl: './bench-press.component.html',
  styleUrl: './bench-press.component.scss'
})
export class BenchPressComponent {

  @Input() athleteId!: string; 

}
