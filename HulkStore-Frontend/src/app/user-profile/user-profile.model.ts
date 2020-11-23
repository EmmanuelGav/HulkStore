export class UserProfile {
    id: BigInteger;
    firstName: String;
    lastName: String;
    phoneNumber: String;
    address: String;
    city: String;
	username: String;
    email: String;
    password: String;
    roles: Array<String>;
    token?: String;
}