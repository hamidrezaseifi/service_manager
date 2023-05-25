import { environment } from '../../environments/environment';


export class ApiServiceHelper {
	

	getApiUrl(functionUrl: string):string{
		return environment.apiUrl + functionUrl;
	} 

	getApiErrorMessage(err: any): string{
		if(err.error && err.error.message){
			return err.error.message;
		  }
		  else{
			return err.message;
		  }
	}
}

