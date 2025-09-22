import { Component, Input } from '@angular/core';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { WorkoutProgram, WorkoutProgramService } from '../../../../core/service/workout-program/workout-program.service';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EditorModule } from '@tinymce/tinymce-angular';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';


@Component({
  selector: 'app-workout-program-list',
  standalone: true,
  imports: [CommonModule, FormsModule, EditorModule],
  templateUrl: './workout-program-list.component.html',
  styleUrls: ['./workout-program-list.component.scss']
})
export class WorkoutProgramListComponent {

  @Input() athleteId!: string;

  programs: WorkoutProgram[] = [];
  filteredPrograms: WorkoutProgram[] = []; // ✅ lista filtrada
  selectedProgram?: WorkoutProgram;
  successMessage: string | null = null;

  editDialogOpen = false;
  editingProgram!: WorkoutProgram;

  showEnabled = true; // ✅ alterna entre enabled e disabled

  tinyMceConfig: any = {
    height: 300,
    menubar: false,
    plugins: [
      'advlist autolink lists link image charmap print preview anchor',
      'searchreplace visualblocks code fullscreen',
      'insertdatetime media table paste code help wordcount',
    ],
    toolbar:
      'undo redo | formatselect | bold italic backcolor | ' +
      'alignleft aligncenter alignright alignjustify | ' +
      'bullist numlist outdent indent | removeformat | help',
  };

  constructor(
    private workoutProgramService: WorkoutProgramService,
    private authService: AuthService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.loadPrograms();
  }

  private showMessage(msg: string) {
    this.successMessage = msg;
    setTimeout(() => this.successMessage = null, 3000);
  }

  loadPrograms(): void {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
      this.workoutProgramService.getAllForAthlete(headers, this.athleteId).subscribe(
        (programs) => {
          this.programs = programs.sort(
            (a, b) => new Date(a.startDate).getTime() - new Date(b.startDate).getTime()
          );
          this.applyFilter();
          if (this.filteredPrograms.length > 0) {
            this.selectedProgram = this.filteredPrograms[this.filteredPrograms.length - 1];
          }
        },
        (error) => console.error('Failed to load programs:', error)
      );
    } else {
      console.error('Token not found');
    }
  }

  // ✅ aplica filtro baseado no estado
  applyFilter(): void {
    this.filteredPrograms = this.programs.filter(p => p.enabled === this.showEnabled);
  }

  toggleFilter(): void {
    this.showEnabled = !this.showEnabled;
    this.applyFilter();
  }

  selectProgram(program: WorkoutProgram): void {
    this.selectedProgram = program;
  }

  openEditDialog(program: WorkoutProgram) {
    this.editDialogOpen = true;
    this.editingProgram = { ...program };
  }

  closeEditDialog() {
    this.editDialogOpen = false;
  }

  saveSession() {
    if (!this.editingProgram) return;
    const token = this.authService.getToken();
    if (!token) return;
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.workoutProgramService.updateProgram(headers, this.editingProgram.id, this.editingProgram)
      .subscribe(
        () => {
          this.loadPrograms();
          this.closeEditDialog();
          this.showMessage('Session updated successfully ✅');
        },
        error => console.error('Failed to update session:', error)
      );
  }

  getSafeContent(content: string): SafeHtml {
  return this.sanitizer.bypassSecurityTrustHtml(content);
}

  downloadPdf(programId: number, programTitle: string): void {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`,
      });
    this.workoutProgramService.exportToPdf(headers, programId).subscribe(
      response => {
        const url = window.URL.createObjectURL(response);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${programTitle}.pdf`;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
      },
      error => {
        console.error('Download failed', error);
        alert('Failed to download PDF. Please check the program ID and try again.');
      }
    );
  }
  }
}
