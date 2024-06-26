import {Component, inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { Composition } from '../model/composition';

@Component({
    selector: 'confirmation-dialog',
    templateUrl: './confirmation-dialog.html',
    standalone: true,
    imports: [
      MatFormFieldModule,
      MatInputModule,
      FormsModule,
      MatButtonModule,
      MatDialogTitle,
      MatDialogContent,
      MatDialogActions,
      MatDialogClose,
    ],
})
export class ConfirmationDialog {
    readonly dialogRef = inject(MatDialogRef<ConfirmationDialog>);
    readonly composition = inject<Composition>(MAT_DIALOG_DATA);

    onNoClick(): void {
        this.dialogRef.close('No');
    }

    onYesClick(): void {
        this.dialogRef.close('Yes');
    }
}