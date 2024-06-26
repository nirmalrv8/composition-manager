import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { CompositionDetailsComponent } from '../composition-details/composition-details.component';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { Composition } from '../model/composition';
import { firstValueFrom } from 'rxjs';
import { CompositionService } from '../services/composition.service';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialog } from '../confirmation-dialog/confirmation-dialog';

@Component({
  standalone: true,
  selector: 'app-composition-list',
  imports: [CompositionDetailsComponent, MatButtonModule, MatTableModule, MatIconModule],
  templateUrl: './composition-list.component.html',
  styleUrls: ['./composition-list.component.scss']
})
export class CompositionListComponent implements OnInit {
  displayedColumns: string[] = ['Id', 'Area', 'Perimeter', 'Button'];
  dataSource: Composition[];
  @Output() tabChanged = new EventEmitter<any>();

  constructor(private compositionService: CompositionService, private dialog: MatDialog) { }

  async ngOnInit() {
    this.dataSource = await firstValueFrom(this.compositionService.getCompositions());
  }

  goToCanvas(composition: Composition) {
    this.tabChanged.emit(composition);
  }

  async deleteComposition(composition: Composition) {
    const dialogRef = this.dialog.open(ConfirmationDialog, {
      data: composition,
    });

    dialogRef.afterClosed().subscribe(async result => {
      if (result === 'Yes') {
        await firstValueFrom(this.compositionService.deleteComposition(composition.id!));
        this.dataSource = await firstValueFrom(this.compositionService.getCompositions());
      }
    });
  }
}
