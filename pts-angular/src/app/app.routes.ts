import { Routes } from '@angular/router';
import { WelcomeComponent } from './shared/components/welcome/welcome.component';
import { LoginComponent } from './auth/pages/login/login.component';
import { RegisterComponent } from './auth/pages/register/register.component';
import { DashboardComponent } from './personal-trainer/pages/dashboard/dashboard.component';
import { AuthGuard } from './auth/services/auth/auth.guard';
import { EditUserComponent } from './personal-trainer/pages/edit-user/edit-user.component';
import { AthletesListComponent } from './personal-trainer/pages/athletes-list/athletes-list.component';
import { EditAthleteUserComponent } from './athlete/pages/edit-athlete-user/edit-athlete-user.component';
import { AthleteDashboardComponent } from './athlete/pages/athlete-dashboard/athlete-dashboard.component';
import { CreateInicialAssessmentComponent } from './personal-trainer/pages/create-inicial-assessment/create-inicial-assessment.component';
import { CreatePerimetryAssessmentComponent } from './personal-trainer/pages/create-perimetry-assessment/create-perimetry-assessment.component';
import { CreateBioimpedanceAssessmentComponent } from './personal-trainer/pages/create-bioimpedance-assessment/create-bioimpedance-assessment.component';
import { CreateWorkoutSessionComponent } from './personal-trainer/pages/create-workout-session/create-workout-session.component';
import { CreateWorkoutProgramComponent } from './personal-trainer/pages/create-workout-program/create-workout-program.component';
import { CreateCooperTestComponent } from './personal-trainer/pages/create-cooper-test/create-cooper-test.component';

export const routes: Routes = [
  {path: '', component: WelcomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {
    path: 'personaltrainer/dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/athletes',
    component: AthletesListComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/edit',
    component: EditUserComponent,
    canActivate: [AuthGuard]},
    {
    path: 'athlete/edit-athlete-user/:athleteId',
    component: EditAthleteUserComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/athlete-dashboard/:athleteId',
    component: AthleteDashboardComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/create-inicial-assessment',
    component: CreateInicialAssessmentComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/create-perimetry-assessment',
    component: CreatePerimetryAssessmentComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/create-bioimpedance-assessment',
    component: CreateBioimpedanceAssessmentComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/create-workout-session',
    component: CreateWorkoutSessionComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/create-workout-program',
    component: CreateWorkoutProgramComponent,
    canActivate: [AuthGuard]},
    {
    path: 'personaltrainer/create-cooper-test',
    component: CreateCooperTestComponent,
    canActivate: [AuthGuard]}

];
