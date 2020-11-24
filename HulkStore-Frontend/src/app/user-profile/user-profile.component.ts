import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { first } from 'rxjs/operators';

import { AuthenticationService } from '../authentication/authentication.service';
import { UserProfile } from './user-profile.model';
import { UserProfileService } from './user-profile.service';

@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

	currentUserProfile: UserProfile;
	userProfileForm: FormGroup;
	loading = false;
	submitted = false;
	error = '';

	constructor(private formBuilder: FormBuilder,
		private userProfileService: UserProfileService,
		private toastr: ToastrService,
		private authenticationService: AuthenticationService) {
	}

	ngOnInit() {
		this.loading = true;
		this.submitted = true;
		this.currentUserProfile = this.authenticationService.currentUserProfileValue;
		this.userProfileService.findById(this.currentUserProfile.id)
			.pipe(first())
			.subscribe(data => {
				this.loading = false;
				this.submitted = false;
				this.currentUserProfile = data;
				this.loadForm();
			}, error => {
				this.error = error;
				this.loading = false;
			});
	}

	loadForm() {
		this.userProfileForm = this.formBuilder.group({
			username: [this.currentUserProfile.username, Validators.required],
			email: [this.currentUserProfile.email, Validators.required],
			firstName: [this.currentUserProfile.firstName, Validators.required],
			lastName: [this.currentUserProfile.lastName, Validators.required],
			address: [this.currentUserProfile.address, Validators.required],
			phoneNumber: [this.currentUserProfile.phoneNumber, Validators.required],
			city: [this.currentUserProfile.city, Validators.required]
		});
	}
	// convenience getter for easy access to form fields
	get f() { return this.userProfileForm.controls; }

	onSubmit() {

		// stop here if form is invalid
		if (this.userProfileForm.invalid) {
			return;
		}
		let userSaved = {
			username: this.f.username.value, email: this.f.email.value,
			firstName: this.f.firstName.value, lastName: this.f.lastName.value, address: this.f.address.value,
			phoneNumber: this.f.phoneNumber.value, city: this.f.city.value
		};

		this.submitted = true;
		this.loading = true;
		this.userProfileService.save(userSaved)
			.pipe(first())
			.subscribe(
				data => {
					this.loading = false;
					this.submitted = false;
					this.authenticationService.currentUserProfileSubject.next(data);
					this.currentUserProfile = data;

					this.toastr.success('Profile updated!');
				},
				error => {
					this.error = error;
					this.loading = false;
					this.submitted = false;
				});
	}
}
