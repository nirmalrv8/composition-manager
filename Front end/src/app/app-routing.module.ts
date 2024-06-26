import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompositionDetailsComponent } from './composition-details/composition-details.component';
import { AddFormComponent } from './add-form/add-form.component';
import { EditFormComponent } from './edit-form/edit-form.component';

const routes: Routes = [
  { path: '', redirectTo: '/compositions', pathMatch: 'full' },
  { path: 'compositions/:id', component: CompositionDetailsComponent },
  { path: 'compositions/:id/add-form', component: AddFormComponent },
  { path: 'compositions/:id/edit-form/:formId', component: EditFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
