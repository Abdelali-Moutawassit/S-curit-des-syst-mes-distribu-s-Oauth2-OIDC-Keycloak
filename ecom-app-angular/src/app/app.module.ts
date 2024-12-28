import { APP_INITIALIZER,NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomersComponent } from './ui/customers/customers.component';
import { OrderDetailsComponent } from './ui/order-details/order-details.component';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { OrdersComponent } from './ui/orders/orders.component';
import { ProductsComponent } from './ui/products/products.component';


// Keycloak initialization function
function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'ecom-realm',
        clientId: 'angular-client',
      },
      initOptions: {
        onLoad: 'check-sso'
      },
    });
}


@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    OrderDetailsComponent,
    OrdersComponent,
    ProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    KeycloakAngularModule,
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService],
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
