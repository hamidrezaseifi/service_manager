import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ServiceInfoModel, ServiceLogModel } from '../models/service-info.model';
import { Observable } from 'rxjs';
import { ApiServiceHelper } from './api.service.helper';

@Injectable({
  providedIn: 'root'
})
export class ServicesService extends ApiServiceHelper {
   serviceListUrl: string = "/services/list";
  serviceInfoUrl: string = "/service/%type/info";
  serviceLogsUrl: string = "/service/%type/logs";
  serviceStopUrl: string = "/service/%type/stop";
  serviceStartUrl: string = "/service/%type/start";

  constructor(private http: HttpClient) {
    super();
  }

  getServicesList(): Observable<ServiceInfoModel[]>{
    return this.http.get<ServiceInfoModel[]>(this.getServiceListUrl());
  }

  getServiceInfo(serviceType: string): Observable<ServiceInfoModel>{
    return this.http.get<ServiceInfoModel>(this.getServiceInfoUrl(serviceType));
  }

  getServiceLogs(serviceType: string): Observable<ServiceLogModel[]>{
    return this.http.get<ServiceLogModel[]>(this.getServiceLogsUrl(serviceType));
  }
   
  stop(serviceType: string) {
    console.log("Stop service: " + this.getServiceStopUrl(serviceType));
    this.http.get<string[]>(this.getServiceStopUrl(serviceType)).subscribe();
  }
 
  start(serviceType: string) {
    this.http.get<string[]>(this.getServiceStartUrl(serviceType)).subscribe();
  }

	getServiceListUrl(): string{
		return this.getApiUrl(this.serviceListUrl);
	}
  
	getServiceInfoUrl(serviceType: string): string{
		return this.getApiUrl(this.serviceInfoUrl.replace("%type", serviceType));
	}
 
	getServiceLogsUrl(serviceType: string): string{
		return this.getApiUrl(this.serviceLogsUrl.replace("%type", serviceType));
	}

	getServiceStopUrl(serviceType: string): string{
		return this.getApiUrl(this.serviceStopUrl.replace("%type", serviceType));
	}

	getServiceStartUrl(serviceType: string): string{
		return this.getApiUrl(this.serviceStartUrl.replace("%type", serviceType));
	}

}
