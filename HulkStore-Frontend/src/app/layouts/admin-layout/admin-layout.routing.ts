import { Routes } from '@angular/router';

import { ProductComponent } from '../../product/product.component';
import { CartComponent } from '../../cart/cart.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'cart',   component: CartComponent },
    { path: 'product',      component: ProductComponent },
    { path: 'user-profile',   component: UserProfileComponent }
];
