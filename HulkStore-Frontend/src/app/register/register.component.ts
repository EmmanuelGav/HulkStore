import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../authentication/authentication.service';
import { ToastrService } from 'ngx-toastr';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

	registerForm: FormGroup;
	loading = false;
	submitted = false;
	returnUrl: string;
	error = '';

	constructor(private formBuilder: FormBuilder,
		private route: ActivatedRoute,
		private router: Router,
		private toastr: ToastrService,
		private authenticationService: AuthenticationService) {

		if (this.authenticationService.currentUserProfileValue) {
			this.router.navigate(['/product']);
		}
	}

	ngOnInit() {
		this.registerForm = this.formBuilder.group({
			username: ['', Validators.required],
			email: ['', Validators.required],
			password: ['', Validators.required],
			confirmPassword: ['', Validators.required]
		});

		// get return url from route parameters or default to '/'
		this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/login';
	}

	// convenience getter for easy access to form fields
	get f() { return this.registerForm.controls; }

	onSubmit() {
		this.submitted = true;
		this.error = "";

		// stop here if form is invalid
		if (this.registerForm.invalid) {
			this.toastr.error("Form is invalid", "Error");
			return;
		}
		if (this.f.password.value != this.f.confirmPassword.value) {
			// this.error = "Passwords do not match";
			this.toastr.error("Passwords do not match", "Error");
			return;
		}

		this.loading = true;
		this.authenticationService.register(this.f.username.value, this.f.email.value, this.f.password.value)
			.pipe(first())
			.subscribe(
				data => {
					this.router.navigate([this.returnUrl]);
				},
				error => {
					// this.error = error;
					this.loading = false;
					this.toastr.error(error, "Error");
				});
	}
}
