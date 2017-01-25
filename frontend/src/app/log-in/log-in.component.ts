import {Component, AfterViewChecked, ViewChild} from "@angular/core";
import { NgForm } from '@angular/forms';

import {Router, CanActivate} from '@angular/router';
import {LoginService}  from '../services/log-in.service';
import {LoginRequest} from '../services/login-request';

@Component({
    selector: 'log-in',
    templateUrl: './log-in.component.html'
})
export class LoginComponent implements AfterViewChecked {


  loginRequest = new LoginRequest('', '', true);
    ngAfterViewChecked(): void {
      this.formChanged();
    }

    constructor(private router:Router, private loginService:LoginService) {
    }

  formChanged() {
    if (this.currentForm === this.loginForm) { return; }
    this.loginForm = this.currentForm;
    if (this.loginForm) {
      this.loginForm.valueChanges.subscribe(data => this.onValueChanged(data));
    }
  }

  loginForm: NgForm;
  @ViewChild('loginForm') currentForm: NgForm;

  onValueChanged(data?: any) {
    if (!this.loginForm) { return; }
    const form = this.loginForm.form;

    for (const field in this.formErrors) {
      // clear previous error message (if any)
      this.formErrors[field] = '';
      const control = form.get(field);

      if (control && control.dirty && !control.valid) {
        const messages = this.validationMessages[field];
        for (const key in control.errors) {
          this.formErrors[field] += messages[key] + ' ';
        }
      }
    }
  }

  formErrors = {
    'username': '',
    'password': ''
  };

  validationMessages = {
    'username': {
      'required':      'Username is required.',
      'minlength':     'Username must be at least 4 characters long.',
    },
    'password': {
      'required':      'Password is required.',
      'minlength':     'Password must be at least 4 characters long.',
    }
  };


  handleLogin(event) {
        event.preventDefault();
        this.loginService.login(this.loginRequest)
            .subscribe(() => {
                this.router.navigate(['/tables']);
            }, this.handleError);
    }

    logout():void {
        localStorage.removeItem('jwt');
    }


    handleError(error) {
        console.log(error.status);
    }


}
