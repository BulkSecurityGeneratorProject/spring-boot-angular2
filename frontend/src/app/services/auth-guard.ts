import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate() {
    if (localStorage.getItem('jwt') !== null) {
      console.log("AuthGuard return true")
      return true;
    }
    console.log("AuthGuard navigate to login")
    this.router.navigate(['/login']);
    return false;
  }
}
