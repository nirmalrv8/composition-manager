import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {CompositionListComponent} from "./composition-list/composition-list.component";
import { CompositionDetailsComponent } from './composition-details/composition-details.component';
import {MatTabsModule} from '@angular/material/tabs';
import {Title} from "@angular/platform-browser";

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, CompositionListComponent, CompositionDetailsComponent, MatTabsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  tabIndex: number;
  selectedRow: any;

  constructor(private titleService:Title) {
    this.titleService.setTitle('Composition Manager');
  }

  tabChanged(data: any) {
    this.selectedRow = data;
    this.tabIndex = 1;
  }
}


