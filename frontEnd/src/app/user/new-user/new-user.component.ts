import {Component, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {User, UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

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
      pass: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      superAdmin: new FormControl(null),
      permissions: new FormArray([])
    });

    this.userServie.createDraftUser().subscribe(response => {
      this.form.patchValue({...response});
    });

  }

  formSubmit(form: FormGroup) {
    this.user = form.value;
    this.form.markAsPristine();
    this.userServie.storeNewUser(this.user).subscribe(response => {
      this.router.navigate(['user']);
    });
  }

}
