import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Transaction, TransactionService} from "../services/transaction.service";
import {AccountService} from "../services/account.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSort} from "@angular/material/sort";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {tap} from "rxjs/operators";
import {User} from "../services/user.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  displayedColumns: string[] = ['description', 'type', 'date', 'userName', 'amount', 'actions'];
  displayedColumnsUser: string[] = ['fName', 'sName'];

  @Input() dataLength: number;
  @Input() dataPageIndex: number; // Which index is the current for the data paginator
  @Input() dataPageSize: number = 5; // Size of paginator page data
  @Input() pageSizeOptions: number[] = [5, 10, 25, 100]; // Size of paginator page data

  @Output() pageChange: EventEmitter<PageEvent> = new EventEmitter();

  constructor(private transactionService: TransactionService, private accountService: AccountService, private route: ActivatedRoute, private router: Router) {
  }

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  form: FormGroup;
  transactionList: MatTableDataSource<Transaction>;
  accountList: MatTableDataSource<Account>;
  userList: MatTableDataSource<User>;
  headers: string[];
  pageIndex: string;
  pageSize: string;
  tableSort: string;
  filterValues: {
    description: string;
    type: string;
  };
  paramId: string;

  ngOnInit() {
    this.paramId = this.route.snapshot.params.id;
    this.form = new FormGroup({
      id: new FormControl(null),
      calculatedBalance: new FormControl({value: '', disabled: true}),
      description: new FormControl({value: '', disabled: this.paramId}, [Validators.required, Validators.minLength(3)]),
    });


    if (this.paramId) {
      this.accountService.getAccountById(this.paramId).subscribe(response => {
        this.form.patchValue({...response});
        this.userList = new MatTableDataSource(response.users);
      });
      this.tableSort = '';
      this.filterValues = this.form.value;
      this.getTransactionsForAccount(this.tableSort, '', this.dataPageSize.toString()).subscribe();
    } else {
      this.accountService.createDraftAccount().subscribe(response => {
        this.form.patchValue({...response});
      });
    }
  }

  private getTransactionsForAccount(sort: string, page: string, size: string) {
    return this.transactionService.getAllForAccount(sort, page, size, this.paramId).pipe(
      tap((response: any) => {
        const transactions: any = response.body;
        this.transactionList = new MatTableDataSource(transactions);
        const keys = response.headers.keys();
        this.headers = keys.map(key =>
          response.headers.get(key));
        this.dataLength = +this.headers[6];
      })
    );
  }

  addTransaction() {
    this.router.navigate(['/addTransaction', this.paramId]);
  }

  editTransaction(id, $event: MouseEvent) {
    $event.stopPropagation();
    this.router.navigate(['/editTransaction', id]);
  }

  deleteTransaction(id, $event: MouseEvent) {
    $event.stopPropagation();
    this.transactionService.deleteTransaction(id).subscribe((data: any[]) => {
      this.getTransactionsForAccount(this.tableSort, '', this.dataPageSize.toString()).subscribe();
    });
  }

  formSubmit(form: FormGroup) {
    if (form.valid) {
      this.form.markAsPristine();
      this.accountService.updateAccount(form.getRawValue()).subscribe(response => {
        this.router.navigate(['']);
      });
    } else {
      form.markAsDirty();
      alert("Form is invalid")
    }
  }

  addUser() {
    alert("add user");
  }
}
