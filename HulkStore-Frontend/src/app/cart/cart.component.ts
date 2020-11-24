import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { element } from 'protractor';
import { Cart } from './cart.model';
import { CartService } from './cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart: Cart;
  showForm = false;

  initialDate: String;
  finalDate: String;

  constructor(public cartService: CartService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getProductsInCart();
  }

  getProductsInCart() {
    this.cart = this.cartService.getProductsInCart();
  }

  saveCart() {
    let error1, error2 = false;
    this.cart.cartProducts.map(element => {
      if (element.amount > element.product.stock) {
        error1 = true;
        return;
      }
      if (element.amount == null || element.amount == 0) {
        error2 = true;
        return;
      }
    })
    if (error1) {
      this.toastr.error('The amount cannot be greater than available stock!');
      return;
    }
    if (error2) {
      this.toastr.error('The amount cannot be 0 or empty!');
      return;
    }

    this.cartService.add(this.cart)
      .subscribe(result => {
        this.cart = new Cart();
        this.cartService.cart = new Cart();
        this.toastr.success('Cart saved!');
      });
  }

}
