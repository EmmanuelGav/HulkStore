
export class Product {
    id:number;
    name:String;
    availableStock:number;
    pricePerHour:number;
    image: string | ArrayBuffer;
    platforms : Set<Object>
    directors : Set<Object>
    producers : Set<Object>

}