import { CartProducts } from './cart-products.model ';

export class Cart {
    id: number;
    userId: number;
    cartProducts: Array<CartProducts> = [];
    orderDate: Date;
    status: string;
}