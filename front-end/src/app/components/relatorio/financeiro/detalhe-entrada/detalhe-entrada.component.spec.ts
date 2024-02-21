import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalheEntradaComponent } from './detalhe-entrada.component';

describe('DetalheEntradaComponent', () => {
  let component: DetalheEntradaComponent;
  let fixture: ComponentFixture<DetalheEntradaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalheEntradaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalheEntradaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
