import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSort, Sort} from "@angular/material/sort";
import {FormControl, FormGroup} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {Transaction, TransactionService} from "../services/transaction.service";
import {tap} from "rxjs/operators";
import {AccountService} from "../services/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  displayedColumns: string[] = ['description', 'type', 'date', 'account', 'amount'];
  displayedColumnsAccount: string[] = ['description', 'calculatedBalance', 'actions'];

  @Input() dataLength: number;
  @Input() dataPageIndex: number; // Which index is the current for the data paginator
  @Input() dataPageSize: number = 5; // Size of paginator page data
  @Input() pageSizeOptions: number[] = [5, 10, 25, 100]; // Size of paginator page data

  @Output() pageChange: EventEmitter<PageEvent> = new EventEmitter();

  constructor(private transactionService: TransactionService, private accountService: AccountService, private router: Router) {
  }

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  form: FormGroup;
  transactionList: MatTableDataSource<Transaction>;
  accountList: MatTableDataSource<Account>;
  headers: string[];
  headersAccount: string[];
  pageIndex: string;
  pageSize: string;
  tableSort: string;
  filterValues: {
    description: string;
    type: string;
  };
  private userID: string;

  ngOnInit() {
    this.userID = sessionStorage.getItem("username");
    this.createFilterForm();
    this.tableSort = '';
    this.filterValues = this.form.value;
    this.getTransactionsForUser(this.tableSort, '', this.dataPageSize.toString(), this.filterValues.description, this.filterValues.type).subscribe();
    this.getAccountsForUser().subscribe();
  }

  applyFilter(form: FormGroup) {
    this.filterValues = form.value;
    return this.getTransactionsForUser(this.tableSort, '', '5', this.filterValues.description, this.filterValues.type).subscribe();
  }

  clearFilter() {
    this.createFilterForm();
    this.filterValues = this.form.value;
    this.getTransactionsForUser(this.tableSort, '', this.dataPageSize.toString(), this.filterValues.description, this.filterValues.type).subscribe();
  }

  private createFilterForm() {
    this.form = new FormGroup({
      id: new FormControl(''),
      amount: new FormControl(''),
      date: new FormControl(''),
      description: new FormControl(''),
      type: new FormControl(''),
      userName: new FormControl(''),
      accountName: new FormControl(''),
    });
  }


  sortData(event: Sort) {
    if (event.direction == "") {
      this.tableSort = '';
    } else {
      this.tableSort = event.active + ',' + event.direction;
    }
    return this.getTransactionsForUser(this.tableSort, this.pageIndex, this.pageSize, this.filterValues.description, this.filterValues.type).subscribe();
  }

  getServerData(event: PageEvent) {
    this.pageIndex = event.pageIndex.toString();
    this.pageSize = event.pageSize.toString();
    return this.getTransactionsForUser(this.tableSort, this.pageIndex, this.pageSize, this.filterValues.description, this.filterValues.type).subscribe();
  }

  private getTransactionsForUser(sort: string, page: string, size: string, description: string, type: string) {
    return this.transactionService.getAllFiltered(sort, page, size, description, type, this.userID).pipe(
      tap((response: any) => {
        const transactions: any = response.body;
        this.transactionList = new MatTableDataSource(transactions);
        const keys = response.headers.keys();
        this.headers = keys.map(key =>
          response.headers.get(key));
        this.dataLength = +this.headers[5];
      })
    );
  }

  public getaccounts(event) {
    if (event.index == 1) {
      this.getallAccounts().subscribe();
    } else if (event.index == 0) {
      this.getAccountsForUser().subscribe();
    }

  }

  private getallAccounts() {
    return this.accountService.getByUsername(null).pipe(
      tap((response: any) => {
        const accounts: any = response.body;
        this.accountList = new MatTableDataSource(accounts);
        const keys = response.headers.keys();
        this.headersAccount = keys.map(key =>
          response.headers.get(key));
        this.dataLength = +this.headersAccount[3];
      })
    );
  }

  private getAccountsForUser() {
    return this.accountService.getByUsername(this.userID).pipe(
      tap((response: any) => {
        const accounts: any = response.body;
        this.accountList = new MatTableDataSource(accounts);
        const keys = response.headers.keys();
        this.headersAccount = keys.map(key =>
          response.headers.get(key));
        this.dataLength = +this.headersAccount[3];
      })
    );
  }

  viewAccount(id, $event: MouseEvent) {
    $event.stopPropagation();
    this.router.navigate(['/viewAccount', id]);
  }
}
