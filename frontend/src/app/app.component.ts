import { Component } from '@angular/core';
@Component({
  selector: 'my-app',
  template: `
    <my-top-navbar></my-top-navbar>
    <div class="container">
      <router-outlet></router-outlet>
    </div>
`,
})
export class AppComponent {
}
