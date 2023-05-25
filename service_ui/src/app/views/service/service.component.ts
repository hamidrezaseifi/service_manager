import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceInfoModel } from 'src/app/models/service-info.model';
import { ServicesService } from 'src/app/services/services.service';

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.css']
})
export class ServiceComponent implements OnInit {
  service: string = "not-set";
  serviceInfo: ServiceInfoModel = new ServiceInfoModel;

  autoReload: boolean = false;

  reloadIntervalId = -1;

  constructor(private route: ActivatedRoute, 
    private servicesService: 
    ServicesService, private router: Router) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.service = String(routeParams.get('service'));

    this.reloadService();
  }

  reloadService(){
    this.servicesService.getServiceInfo(this.service).subscribe( data => {
      //console.log(data);
      this.serviceInfo = data;

      const comp = this;

      console.log(this.serviceInfo);

      //setInterval(function () { comp.reloadLogs()}, 1000);

    },
    err => {
     console.log(err);
    });
  }

  reloadLogs(){
    this.servicesService.getServiceLogs(this.service).subscribe( data => {
      this.serviceInfo.logs = data;

    },
    err => {
     console.log(err);
    });
  }

  toggleReloadLogs(){
    const comp = this;
    if(this.reloadIntervalId > 0){
      clearInterval(this.reloadIntervalId);
      this.reloadIntervalId = -1;
    }
    else{
      this.reloadIntervalId = window.setInterval(function () { comp.reloadLogs()}, 1000);
    }

    this.autoReload = !this.autoReload;
  }

  stop(){
    const comp = this;
    this.servicesService.stop(this.service);
    window.setTimeout(function () { comp.reloadService()}, 1000);
    
  }

  start(){
    const comp = this;
    this.servicesService.start(this.service);
    window.setTimeout(function () { comp.reloadService()}, 1000);
  }

}
