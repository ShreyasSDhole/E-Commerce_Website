import { Injectable } from '@angular/core';

const TOKEN = 'gofit-token';
const USER = 'gofit-user';

@Injectable({
  providedIn: 'root'
})

export class UserStorageService {

  constructor() { }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  public saveUser(user: string): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string{
    return localStorage.getItem(TOKEN);
  }

  static getUser(): string{
    return JSON.parse(localStorage.getItem(USER));
  }

 static getUserId(): string {
  const user= this.getUser();
  if (user == null) {
    return '';
  }
  return this.getUserId(); // Convert userId to string if it's not already a string
}

  static getUserRole(): string{
    const user = this.getUser();
    if(user == null){
      return '';
    }
    return this.getUserRole();
  }

  static isAdminLoggedIn(): boolean{
    if(this.getToken == null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == "ADMIN";
  } 

  static isCustomerLoggedIn(): boolean{
    if(this.getToken == null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == "CUSTOMER";
  } 

  static signOut(): void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}