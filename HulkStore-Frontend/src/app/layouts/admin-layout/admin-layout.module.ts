import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ProductComponent } from '../../product/product.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { CartComponent } from '../../cart/cart.component';
import { OrderComponent } from '../../order/order.component';

import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    ChartsModule,
    NgbModule,
    ToastrModule.forRoot()
  ],
  declarations: [
    ProductComponent,
    CartComponent,
    UserProfileComponent,
    OrderComponent
  ]
})

export class AdminLayoutModule { }
