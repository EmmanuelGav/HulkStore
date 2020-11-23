import { Component, OnInit } from '@angular/core';
import { Cart } from './cart.model';
import { CartService } from './cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  carts: Cart[];
  showForm = false;

  initialDate: String;
  finalDate: String;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.cartService.getAll()
      .subscribe((response) => {
        this.carts = response;
      });
  }
  

}
