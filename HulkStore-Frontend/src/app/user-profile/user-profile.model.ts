export class UserProfile {
    id: number;
    firstName: string;
    lastName: string;
    phoneNumber: string;
    address: string;
    city: string;
	username: string;
    email: string;
    password: string;
    roles: Array<string>;
    token?: string;
}