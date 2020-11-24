import { Product } from '../product/product.model';
import { Cart } from './cart.model';

export class CartProducts {
    id: number;
    cart: Cart;
    product: Product;
    amount: number;
    totalPrice: number;
}