export class ServiceLogModel {
      logDateTime!: Date;
      logDateTimeStr!: string;
      level: string = "";
      message: string = "";
}


export class ServiceInfoModel {
      type: string = "";
      status: string = "";
      typeTitle: string = "";
      statusTitle: string = "";
      logs: ServiceLogModel[] = [];
}
