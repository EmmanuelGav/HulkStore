import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Cart } from './cart.model';
import { Product } from '../product/product.model';
import { AuthenticationService } from '../authentication/authentication.service';
import { ToastrService } from 'ngx-toastr';
import { CartProducts } from './cart-products.model ';
import { element } from 'protractor';

const baseUrl = environment.url + "/cart";
@Injectable({
	providedIn: 'root'
})
export class CartService {

	cart: Cart = new Cart();

	constructor(
		private http: HttpClient,
		private toastr: ToastrService,
		private authenticationService: AuthenticationService) {
		this.cart.userId = this.authenticationService.currentUserProfileValue.id;
	}

	addProductInCart(product: Product, amount: number) {
		let existProductInCart = false
		this.cart.cartProducts.map(elemente => {
			if (product.id == elemente.product.id) {
				existProductInCart = true;
				return;
			}
		})
		if (!existProductInCart) {
			let cartProduct = new CartProducts();
			cartProduct.product = product
			cartProduct.amount = 1;
			this.cart.cartProducts.push(cartProduct);
			this.toastr.success('is added to cart!', product.name);
		} else {
			this.toastr.warning('already exists in the cart!', product.name);
		}
	}
	removeProductInCart(indexProduct) {
		this.cart.cartProducts.splice(indexProduct, 1);
		this.toastr.error('Product removed from cart!');
	}

	getProductsInCart() {
		return this.cart;
	}

	getAll(): Observable<Cart[]> {
		return this.http.get<Cart[]>(baseUrl + '/all')
			.pipe(
				catchError(this.handleError<Cart[]>(baseUrl + '/all', []))
			);
	}

	add(cart: Cart) {
		cart.cartProducts.map((element, i) => {
			cart.cartProducts[i].amount = element.amount
			cart.cartProducts[i].totalPrice = element.product.pricePerUnit*element.amount;
		})
		cart.status = "Ordered";
		cart.userId = this.authenticationService.currentUserProfileValue.id;
		return this.http.post<Cart>(baseUrl + '/add', cart)
			.pipe(
				catchError(this.handleError<Cart>('addCart'))
			);
	}


	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {

			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}
}
