import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../authentication/authentication.service';

declare interface RouteInfo {
	path: String;
	title: String;
	icon: String;
	class: String;
	hasPermission: Array<String>;
	show: boolean;
}
export const ROUTES: RouteInfo[] = [
	{ path: '/product', title: 'Products', icon: 'tech_controller-modern', class: '', hasPermission: ['User','Admin','SuperAdmin'], show: false  },
	{ path: '/cart', title: 'Shopping Cart', icon: 'shopping_cart-simple', class: '', hasPermission: ['User'], show: false },
	{ path: '/order', title: 'Orders', icon: 'design_bullet-list-67', class: '', hasPermission: ['Admin','SuperAdmin'], show: false  },

];

@Component({
	selector: 'app-sidebar',
	templateUrl: './sidebar.component.html',
	styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
	menuItems: any[];
	roles: Array<String>; 

	constructor(private authenticationService: AuthenticationService) {
		this.roles = this.authenticationService.currentUserProfileValue.roles;
	 }

	ngOnInit() {
		this.menuItems = ROUTES.filter(menuItem => menuItem);
		this.showOptionWithPermission();
	}
	isMobileMenu() {
		if (window.innerWidth > 991) {
			return false;
		}
		return true;
	};

	showOptionWithPermission() {
		this.menuItems.forEach(menuItem => {
			menuItem.show = false;
			menuItem.hasPermission.forEach(permission => {
				if(this.roles.includes(permission)) {
					menuItem.show = true;
					return;
				}
			});
		});
	}
}
