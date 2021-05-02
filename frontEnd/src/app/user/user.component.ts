import {Component, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  users = [];

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.userService.getAll().subscribe((data: any[]) => {
      console.log(data);
      this.users = data;
    })
  }

  editUser(useId) {
    this.router.navigate(['/editUser', useId]);
  }
}
