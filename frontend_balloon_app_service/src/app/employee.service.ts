import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import Employee from "./employee";
import {Observable} from "rxjs";

@Injectable(
  {providedIn: 'root'}
)
export class EmployeeService{
  private apiServiceUrl='http://localhost:9191/employee-service';
  constructor(private http:HttpClient){}

  public getEmployees():Observable<Employee[]>{

    return this.http.get<Employee[]>(`${this.apiServiceUrl}/api/employees/2`)
  }
}
