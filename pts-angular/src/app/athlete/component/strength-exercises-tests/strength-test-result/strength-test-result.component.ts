import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-strength-test-result',
  standalone: true,
  imports: [],
  templateUrl: './strength-test-result.component.html',
  styleUrl: './strength-test-result.component.scss'
})
export class StrengthTestResultComponent {

   @Input() athleteId!: string;

}
