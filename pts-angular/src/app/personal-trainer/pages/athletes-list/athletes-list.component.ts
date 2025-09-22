import { Component, OnInit } from '@angular/core';
import { Athlete, PageResponse, UserService } from '../../../core/service/user/user.service';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from "../../../shared/components/navbar/navbar.component";
import { FooterComponent } from "../../../shared/components/footer/footer.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-athletes-list',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './athletes-list.component.html',
  styleUrls: ['./athletes-list.component.scss'] // CORRIGIDO
})
export class AthletesListComponent implements OnInit {

  athletes: Athlete[] = [];
  currentPage: number = 0;
  pageSize: number = 30;
  totalElements: number = 0;

  selectedAthleteId: string = '';
  showDisabledAthletes = false;

  // Dialog vars
  visible: boolean = false;
  currentAthlete: Athlete | null = null;
  selectedFile: File | null = null;

  successMessage: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
  ) {}

  ngOnInit(): void {
    this.loadAthletes();
    if (history.state && history.state.successMessage) {
      this.showToast(history.state.successMessage);
    }
  }

  loadAthletes(page: number = 0): void {
    const token = this.authService.getToken();
    if (!token) return;

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    const athleteObservable = this.showDisabledAthletes
      ? this.userService.getAllDisabledAthletes(headers, page, this.pageSize)
      : this.userService.getAllEnabledAthletes(headers, page, this.pageSize);

    athleteObservable.subscribe(
      (response: PageResponse<Athlete>) => {
        this.athletes = response.content;
        this.currentPage = response.pageNumber;
        this.totalElements = response.totalElements;
      },
      (error) => console.error('Failed to load clients:', error)
    );
  }

  toggleAthleteList() {
    this.showDisabledAthletes = !this.showDisabledAthletes;
    this.loadAthletes();
  }

  editAthlete(athleteId: string): void {
    this.router.navigate(['athlete/edit-athlete-user', athleteId]);
  }

  redirectAthletePage(athleteId: string): void {
    this.router.navigate(['personaltrainer/athlete-dashboard', athleteId]);
  }

  nextPage(): void {
    if ((this.currentPage + 1) * this.pageSize < this.totalElements) {
      this.loadAthletes(this.currentPage + 1);
    }
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.loadAthletes(this.currentPage - 1);
    }
  }

  changeStatus(athleteId: string): void {
    const token = this.authService.getToken();
    if (!token) return;

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    this.userService.changeStatus(headers, athleteId).subscribe(
      () => window.location.reload(),
      error => console.error('Failed to update client status:', error)
    );
  }

  showDialog(athleteId: string): void {
    this.selectedAthleteId = athleteId;
    this.currentAthlete = this.athletes.find(a => a.id === athleteId) || null;
    this.visible = true;
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadPhoto(): void {
    if (!this.selectedFile || !this.selectedAthleteId) return;

    const token = this.authService.getToken();
    if (!token) return;

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.userService.updateAthletePhoto(headers, formData, this.selectedAthleteId).subscribe(
      response => {
        const athleteIndex = this.athletes.findIndex(a => a.id === this.selectedAthleteId);
        if (athleteIndex !== -1) {
          this.athletes[athleteIndex].photo = response;
        }

        this.visible = false;
        this.showToast('Photo updated successfully!');
      },
      error => console.error('Error updating user photo:', error)
    );
  }

  hideDialog(): void { this.visible = false; }

  showToast(message: string) {
    this.successMessage = message;
    setTimeout(() => this.successMessage = '', 3000);
  }
}
