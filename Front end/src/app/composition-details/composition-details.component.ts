import { Component, Input, SimpleChanges } from '@angular/core';
import { fabric } from 'fabric';
import { Canvas } from 'fabric/fabric-impl';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CompositionService } from '../services/composition.service';
import { Composition } from '../model/composition';
import { Form } from '../model/form';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatListModule } from '@angular/material/list';

@Component({
  standalone: true,
  selector: 'app-composition-details',
  imports: [MatButtonModule, MatFormFieldModule, MatSelectModule, MatListModule],
  templateUrl: './composition-details.component.html',
  styleUrls: ['./composition-details.component.scss']
})
export class CompositionDetailsComponent {
  composition: Composition;
  canvas: Canvas;
  newCanvas = true;
  selectedShape = 'Rectangle';
  selectedForm: Form;

  @Input() selectedComposition: any;

  constructor(private compositionService: CompositionService, private _snackBar: MatSnackBar) {}

  ngOnChanges(change: SimpleChanges) {
    if (change['selectedComposition'].currentValue) {
      this.createNewCanvas();
      this.loadComposition(change['selectedComposition'].currentValue);
    }
  }

  createNewCanvas() {
    this.newCanvas = true;
    this.composition = {
      aire: 0,
      perimetre: 0,
      formes: []
    }
    if (this.canvas) {
      this.canvas.clear();
    } else {
      this.canvas = new fabric.Canvas('shapeCanvas');
      const canvasDiv = document.getElementById('canvasDiv');
      this.canvas.setDimensions({width:canvasDiv!.offsetWidth, height:canvasDiv!.offsetHeight});
      this.canvas.on('object:modified', this.objectModified.bind(this));
      this.canvas.on('object:scaling', this.objectScaling.bind(this));
      this.canvas.on('object:rotating', this.objectRotating.bind(this));
      this.canvas.on('selection:created', this.objectSelected.bind(this));
      this.canvas.on('selection:updated', this.objectSelected.bind(this));
      this.canvas.on('selection:cleared', this.objectSelected.bind(this));
    }
  }

  loadComposition(composition: any) {
    this.newCanvas = false;
    this.composition = composition;
    for (const form of this.composition.formes) {
      if (form.typeForme === 'RECTANGLE') {
        const rectangle = new fabric.Rect({
          name: form.id,
          left: form.position?.x,
          top: form.position?.y,
          angle: form.rotation,
          width: form.longueur,
          height: form.largeur,
          fill: 'yellow',
        });
        this.canvas.add(rectangle);
        form.longueur = this.roundTwoDecimals(form.longueur!);
        form.largeur = this.roundTwoDecimals(form.largeur!);
        form.rotation = this.roundTwoDecimals(form.rotation!);
      } else if (form.typeForme === 'TRIANGLE') {
        const triangle = new fabric.Triangle({
          name: form.id,
          left: form.position?.x,
          top: form.position?.y,
          angle: form.rotation,
          width: form.cote,
          height: Math.pow((Math.pow(form.cote!, 2) - Math.pow(form.cote!/2, 2)), 0.5),
          fill: 'blue',
        });
        triangle.setControlsVisibility({
          mt: false,
          mb: false,
          ml: false,
          mr: false,
        });
        this.canvas.add(triangle);
        form.cote = this.roundTwoDecimals(form.cote!);
        form.rotation = this.roundTwoDecimals(form.rotation!);
      } else if (form.typeForme === 'CERCLE') {
        const cercle = new fabric.Circle({
          name: form.id,
          left: form.position?.x,
          top: form.position?.y,
          angle: form.rotation,
          radius: form.rayon,
          fill: 'green',
        });
        cercle.setControlsVisibility({
          mt: false,
          mb: false,
          ml: false,
          mr: false,
        });
        this.canvas.add(cercle);
        form.rayon = this.roundTwoDecimals(form.rayon!);
      } else if (form.typeForme === 'CARRE') {
        const carre = new fabric.Rect({
          name: form.id,
          left: form.position?.x,
          top: form.position?.y,
          angle: form.rotation,
          width: form.cote,
          height: form.cote,
          fill: 'red',
        });
        carre.setControlsVisibility({
          mt: false,
          mb: false,
          ml: false,
          mr: false,
        });
        this.canvas.add(carre);
        form.cote = this.roundTwoDecimals(form.cote!);
        form.rotation = this.roundTwoDecimals(form.rotation!);
      }
    }
  }

  objectModified(args: any) {
    const formId = args.target.name;
    const modifiedForm = this.composition.formes.find((form: any) => form.id === formId);
    if (modifiedForm?.typeForme === 'RECTANGLE') {
      this.modifyRectangle(args, modifiedForm);
    } else if (modifiedForm?.typeForme === 'TRIANGLE') {
      this.modifyTriangle(args, modifiedForm);
    } else if (modifiedForm?.typeForme === 'CERCLE') {
      this.modifyCercle(args, modifiedForm);
    } else if (modifiedForm?.typeForme === 'CARRE') {
      this.modifyCarre(args, modifiedForm);
    }
  }

  objectScaling(args: any) {
    if (this.selectedForm.typeForme === 'RECTANGLE') {
      this.selectedForm.longueur = this.roundTwoDecimals(args.target.width * args.target.scaleX);
      this.selectedForm.largeur = this.roundTwoDecimals(args.target.height * args.target.scaleY);
      this.selectedForm.rotation = this.roundTwoDecimals(args.target.angle);
    } else if (this.selectedForm.typeForme === 'TRIANGLE') {
      this.selectedForm.cote = this.roundTwoDecimals(args.target.width * args.target.scaleX);
    } else if (this.selectedForm.typeForme === 'CERCLE') {
      this.selectedForm.rayon = this.roundTwoDecimals(args.target.radius * args.target.scaleX);
    } else if (this.selectedForm.typeForme === 'CARRE') {
      this.selectedForm.cote = this.roundTwoDecimals(args.target.width * args.target.scaleX);
    }
  }

  objectRotating(args: any) {
    this.selectedForm.rotation = this.roundTwoDecimals(args.target.angle);
  }

  objectSelected(args: any) {
    if (args.selected) {
      const formId = args.selected[0].name;
      this.selectedForm = this.composition.formes.find((form: any) => form.id === formId)!;
    } else {
      this.selectedForm = null!;
    }
  }

  modifyRectangle(args: any, modifiedForm: Form) {
    if (modifiedForm) {
      if (args.action === 'drag' && modifiedForm.position) {
        modifiedForm.position.x = args.target.left;
        modifiedForm.position.y = args.target.top;
      } else if (args.action === 'rotate') {
        modifiedForm.rotation = this.roundTwoDecimals(args.target.angle);
      } else if (args.action === 'scale') {
        modifiedForm.longueur = this.roundTwoDecimals(args.target.width * args.target.scaleX);
        modifiedForm.largeur = this.roundTwoDecimals(args.target.height * args.target.scaleY);
      } else if (args.action === 'scaleX') {
        modifiedForm.longueur = this.roundTwoDecimals(args.target.width * args.target.scaleX);
      } else if (args.action === 'scaleY') {
        modifiedForm.largeur = this.roundTwoDecimals(args.target.height * args.target.scaleY);
      }
    }
  }

  modifyTriangle(args: any, modifiedForm: Form) {
    if (modifiedForm) {
      if (args.action === 'drag' && modifiedForm.position) {
        modifiedForm.position.x = args.target.left;
        modifiedForm.position.y = args.target.top;
      } else if (args.action === 'rotate') {
        modifiedForm.rotation = this.roundTwoDecimals(args.target.angle);
      } else if (args.action === 'scale') {
        modifiedForm.cote = this.roundTwoDecimals(args.target.width * args.target.scaleX);
      }
    }
  }

  modifyCercle(args: any, modifiedForm: Form) {
    if (modifiedForm) {
      if (args.action === 'drag' && modifiedForm.position) {
        modifiedForm.position.x = args.target.left;
        modifiedForm.position.y = args.target.top;
      } else if (args.action === 'rotate') {
        modifiedForm.rotation = args.target.angle;
      } else if (args.action === 'scale') {
        modifiedForm.rayon = this.roundTwoDecimals(args.target.radius * args.target.scaleX);
      }
    }
  }

  modifyCarre(args: any, modifiedForm: Form) {
    if (modifiedForm) {
      if (args.action === 'drag' && modifiedForm.position) {
        modifiedForm.position.x = args.target.left;
        modifiedForm.position.y = args.target.top;
      } else if (args.action === 'rotate') {
        modifiedForm.rotation = this.roundTwoDecimals(args.target.angle);
      } else if (args.action === 'scale') {
        modifiedForm.cote = this.roundTwoDecimals(args.target.width * args.target.scaleX);
      }
    }
  }

  addShape() {
    let shape: any;
    let formShape!: Form;
    if (this.selectedShape === 'Rectangle') {
      shape = new fabric.Rect({
        name: this.composition.formes.length.toString(),
        left: 0,
        top: 0,
        width: 80,
        height: 40,
        angle: 0,
        fill: 'yellow',
      });
      formShape = {
        id: this.composition.formes.length.toString(),
        typeForme: 'RECTANGLE',
        rotation: shape.angle,
        position: { x: shape.left, y: shape.top },
        longueur: shape.width,
        largeur: shape.height,
      }
    } else if (this.selectedShape === 'Triangle') {
      shape = new fabric.Triangle({
        name: this.composition.formes.length.toString(),
        left: 0,
        top: 0,
        width: 162,
        height: 140,
        angle: 0,
        fill: 'blue',
      });
      shape.setControlsVisibility({
        mt: false,
        mb: false,
        ml: false,
        mr: false,
      });
      formShape = {
        id: this.composition.formes.length.toString(),
        typeForme: 'TRIANGLE',
        rotation: shape.angle,
        position: { x: shape.left, y: shape.top },
        cote: shape.width,
      }
    } else if (this.selectedShape === 'Circle') {
      shape = new fabric.Circle({
        name: this.composition.formes.length.toString(),
        left: 0,
        top: 0,
        radius: 65,
        fill: 'green',
      });
      shape.setControlsVisibility({
        mt: false,
        mb: false,
        ml: false,
        mr: false,
      });
      formShape = {
        id: this.composition.formes.length.toString(),
        typeForme: 'CERCLE',
        rotation: shape.angle,
        position: { x: shape.left, y: shape.top },
        rayon: shape.radius,
      }
    } else if (this.selectedShape === 'Carre') {
      shape = new fabric.Rect({
        name: this.composition.formes.length.toString(),
        left: 0,
        top: 0,
        width: 120,
        height: 120,
        angle: 0,
        fill: 'red',
      });
      shape.setControlsVisibility({
        mt: false,
        mb: false,
        ml: false,
        mr: false,
      });
      formShape = {
        id: this.composition.formes.length.toString(),
        typeForme: 'CARRE',
        rotation: shape.angle,
        position: { x: shape.left, y: shape.top },
        cote: shape.width,
      }
    }
    this.canvas.add(shape);
    this.composition.formes.push(formShape);
  }

  onSave() {
    if (this.newCanvas) {
      this.newCanvas = false;
      this.compositionService.addComposition(this.composition).subscribe((composition: Composition) => {
        this.composition = composition;
        this._snackBar.open('Saved successfully', 'OK', {
          duration: 3000
        });
      });
    } else {
      this.compositionService.updateComposition(this.composition).subscribe(() => {
        this._snackBar.open('Updated successfully', 'OK', {
          duration: 3000
        });
      });
    }
  }

  export() {
    let svg = this.canvas.toSVG();
    const position = svg.search("<svg") + 4;
    svg = `${svg.slice(0, position)} style="border:1px solid black" ${svg.slice(position)}`;

    const file = new Blob([svg], {type: 'svg'});
    const anchor = document.createElement("a");
    const url = URL.createObjectURL(file);
    anchor.href = url;
    anchor.download = `Composition${this.composition.id}_export.svg`;
    document.body.appendChild(anchor);
    anchor.click();
    setTimeout(() => {
      document.body.removeChild(anchor);
      window.URL.revokeObjectURL(url);  
    }, 0);
  }

  removeSelected() {
    this.composition.formes = this.composition.formes.filter(form => form.id !== this.canvas.getActiveObject()?.name);
    this.canvas.remove(this.canvas.getActiveObject()!);
  }

  roundTwoDecimals(number: number) {
    return Math.round(number * 100) / 100;
  }

  getCompositionType() {
    if (this.selectedForm) {
      return this.selectedForm.typeForme!.split(' ').map(w => w[0].toUpperCase() + w.substring(1).toLowerCase()).join(' ');
    } else {
      return '';
    }
  }
}
