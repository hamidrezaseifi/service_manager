import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../../services/services.service';
import { ServiceInfoModel } from '../../models/service-info.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  services: ServiceInfoModel[] = [];

  constructor(private servicesService: ServicesService, private router: Router) { }

  ngOnInit(): void {
    this.servicesService.getServicesList().subscribe( data => {
                                                     //console.log(data);
                                                     this.services = data;
                                                     console.log(this.services);

                                                   },
                                                   err => {
                                                    console.log(err);
                                                   });
  }

  showService(service: ServiceInfoModel){
    this.router.navigate(["/service/" + service.type ]);
  }

}
