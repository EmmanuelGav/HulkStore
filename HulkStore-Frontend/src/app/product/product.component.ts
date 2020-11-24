import { Component, OnInit } from '@angular/core';
import { Product } from './product.model';
import { ProductService } from './product.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { AuthenticationService } from '../authentication/authentication.service';
import { CartService } from '../cart/cart.service';
import { ToastrService } from 'ngx-toastr';

@Component({
	selector: 'app-product',
	templateUrl: './product.component.html',
	styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

	products: Product[];
	product: Product = new Product();
	productForm: Product = new Product();
	showForm: Boolean;
	colSizeForm: String;
	image: string | SafeUrl;
	selectedFile: string | ArrayBuffer;
	roles: Array<String>;

	constructor(private productService: ProductService,
		private sanitizer: DomSanitizer,
		public cartService: CartService,
		private toastr: ToastrService,
		public authenticationService: AuthenticationService) {
		this.roles = this.authenticationService.currentUserProfileValue.roles;
	}

	ngOnInit(): void {
		this.showForm = false;
		this.getAll();
	}

	getAll() {
		this.roles.map(role => {
			if (role == "User") {
				this.productService.getAllAvailable()
					.subscribe((response) => {
						this.products = (response.length == 0 ? null : response);
					});
			} else {
				this.productService.getAll()
					.subscribe((response) => {
						this.products = (response.length == 0 ? null : response);
					});
			}
		})

	}

	selectProduct(productSelected: Product) {
		this.productForm = Object.create(productSelected);
		this.product = Object.create(productSelected);
		this.selectedFile = productSelected.image;
		this.showForm = true;
	}

	formNewProduct() {
		this.productForm = new Product();
		this.product = new Product();
		this.selectedFile = null;
		this.showForm = true;
	}

	onSubmit(product: Product) {
		product.image = this.selectedFile;
		this.productService.add(product)
			.subscribe(result => {
				this.getAll();
				this.product = Object.create(result);

				this.toastr.success('Product saved!');
			});
	}

	updateImage(ev) {
		this.image = this.sanitizer.bypassSecurityTrustUrl(
			window.URL.createObjectURL(ev.target.files[0])
		);
		this.handleUpload(ev);
	}
	handleUpload(ev) {
		const file = ev.target.files[0];
		const reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = () => {
			this.selectedFile = reader.result;
		};
	}

}
