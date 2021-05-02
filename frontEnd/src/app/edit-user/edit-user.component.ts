import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User, UserService} from "../user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  form: FormGroup;

  constructor(private userServie: UserService, private router: Router, private route: ActivatedRoute) {
  }

  public user: User;

  ngOnInit(): void {
    this.form = new FormGroup({
      id: new FormControl(null),
      uName: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      fName: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      sName: new FormControl(null, Validators.required),
      comments: new FormControl(null),
      salt: new FormControl(null),
      pass: new FormControl(null, Validators.required)
    });

    const paramId = this.route.snapshot.params.id;

    if (paramId) {
      this.userServie.getUserById(paramId).subscribe(response => {
        this.form.patchValue({...response});
      });
    }
  }

  formSubmit(form: FormGroup) {
    this.user = form.value;
    this.form.markAsPristine();
    this.userServie.updateUser(this.user).subscribe(response => {
      this.router.navigate(['']);
    });
  }

}
