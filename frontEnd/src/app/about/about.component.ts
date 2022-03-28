import {Component, OnInit} from '@angular/core';
import {AboutService} from "../services/about.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  backVersion: string;
  frontVersion: string;

  constructor(private aboutService: AboutService) {
  }

  ngOnInit(): void {
    this.aboutService.getVersion().pipe(
      tap((response: any) => {
        this.backVersion = response.body.backVersion;
        this.frontVersion = response.body.frontVersion;
      })
    ).subscribe();
  }

}
