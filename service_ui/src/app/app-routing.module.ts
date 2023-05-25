import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { ServiceComponent } from './views/service/service.component';

const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'service/:service', component: ServiceComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
