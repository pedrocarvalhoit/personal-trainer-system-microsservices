import { Component, Input, OnInit } from '@angular/core';
import { WorkoutSession, WorkoutSessionService } from '../../../../core/service/workout-session/workout-session.service';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-workout-session-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './workout-session-list.component.html',
  styleUrls: ['./workout-session-list.component.scss']
})
export class WorkoutSessionListComponent implements OnInit {

  @Input() athleteId!: string;

  sessions: WorkoutSession[] = [];
  monthlySessions: WorkoutSession[] = [];
  successMessage: string | null = null;

  editDialogOpen = false;
  editingSession!: WorkoutSession;

  constructor(
    private workoutSessionService: WorkoutSessionService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadSessions();
  }

  private showMessage(msg: string) {
    this.successMessage = msg;
    setTimeout(() => this.successMessage = null, 3000);
  }

  loadSessions(): void {
    const token = this.authService.getToken();
    if (!token) return;

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.workoutSessionService.getAllForAthlete(headers, this.athleteId).subscribe(
      page => {
        this.sessions = page.content;
        this.filterMonthlySessions();
      },
      error => console.error('Failed to load sessions:', error)
    );
  }

  filterMonthlySessions(): void {
    const now = new Date();
    const currentMonth = now.getMonth();
    const currentYear = now.getFullYear();
    this.monthlySessions = this.sessions.filter(s => {
      const d = new Date(s.sessionDate);
      return d.getMonth() === currentMonth && d.getFullYear() === currentYear;
    });
  }

  executeSession(sessionId: number) {
    const token = this.authService.getToken();
    if (!token) return;
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.workoutSessionService.executeSession(headers, sessionId).subscribe(() => {
      const session = this.sessions.find(s => s.id === sessionId);
      if (session) session.executed = true;
      this.filterMonthlySessions();
      this.showMessage('Session executed successfully ✅');
    });
  }

  deleteSession(sessionId: number) {
    const token = this.authService.getToken();
    if (!token) return;
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.workoutSessionService.deleteSession(headers, sessionId).subscribe(() => {
      this.sessions = this.sessions.filter(s => s.id !== sessionId);
      this.filterMonthlySessions();
      this.showMessage('Session deleted successfully ✅');
    });
  }

  openEditDialog(session: WorkoutSession) {
    this.editDialogOpen = true;
    // Cria cópia profunda do objeto
    this.editingSession = {
      ...session,
      clientSubjectiveEffort: session.clientSubjectiveEffort ?? 0,
      ptQualityEffortIndicative: session.ptQualityEffortIndicative ?? 0
    };
  }

  closeEditDialog() {
    this.editDialogOpen = false;
  }

  saveSession() {
  if (!this.editingSession) return;
  const token = this.authService.getToken();
  if (!token) return;
  const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

  this.workoutSessionService.updateSession(headers, this.editingSession.id, this.editingSession)
    .subscribe(
      () => {
        // Recarrega a lista completa de sessões
        this.loadSessions();
        this.closeEditDialog();
        this.showMessage('Session updated successfully ✅');
      },
      error => console.error('Failed to update session:', error)
    );
}

}
