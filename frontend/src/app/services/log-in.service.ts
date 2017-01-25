import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Response} from "@angular/http";
import { Headers, Http } from '@angular/http';
import "rxjs/Rx";
import {LoginRequest} from './login-request';


@Injectable()
export class LoginService {

    constructor(private http:Http) {}

    login(loginRequest: LoginRequest):Observable<Response> {
        let headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json'});

        return this.http.post('http://localhost:8080/api/authenticate', JSON.stringify(loginRequest), { headers: headers })
                        .do(resp => {
                            localStorage.setItem('jwt', resp.json().id_token);
                        });
    }

    logout():void {
        localStorage.removeItem('jwt');
    }

    private handleError(error:Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }


     isSignedIn():boolean {
        return localStorage.getItem('jwt') !== null;
    }


}
