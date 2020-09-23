import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { FormGroupDirective } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { OAuthModule } from 'angular-oauth2-oidc'
import { RouterModule, Routes } from '@angular/router';

import { routes } from './app.routes';
import { ApiService } from './api/ApiService';
import { AppComponent } from './app.component';
import { BigButtonComponent } from './big-button/bigbutton.component';
import { CartComponent } from './cart/cart.component';
import { CartService } from './cart/cart-service';
import { CloudTitleComponent } from './cloud-title/cloudtitle.component';
import { DesignComponent } from './design/design.component';
import { FooterComponent } from './footer/footer.component';
import { GroupBoxComponent } from './group-box/groupbox.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LittleButtonComponent } from './little-button/littlebutton.component';
import { LocationsComponent } from './locations/locations.component';
import { LoginComponent } from './login/login.component';
import { NonWrapsPipe } from './recents/NonWrapsPipe';
import { RecentTacosComponent } from './recents/recents.component';
import { RecentTacosService } from './recents/RecentTacosService';
import { SpecialsComponent } from './specials/specials.component';
import { WrapsPipe } from './recents/WrapsPipe';




@NgModule({
  declarations: [
    AppComponent,
    BigButtonComponent,
    CartComponent,
    CloudTitleComponent,
    DesignComponent,
    FooterComponent,
    GroupBoxComponent,
    HeaderComponent,
    HomeComponent,
    LittleButtonComponent,
    LocationsComponent,
    LoginComponent,
    NonWrapsPipe,
    RecentTacosComponent,
    SpecialsComponent,
    WrapsPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    OAuthModule.forRoot()
  ],
  providers: [
    ApiService,
    CartService,
    RecentTacosService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
