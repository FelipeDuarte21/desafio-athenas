import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AlertasModule } from "../compartilhados/alertas/alertas.module";
import { PaginacaoModule } from "../compartilhados/paginacao/paginacao.module";
import { QuantidadeRegistroModule } from "../compartilhados/quantidade-registro/quantidade-registro.module";
import { SpinnerModule } from "../compartilhados/spinners/spinner.module";
import { CpfPipe } from "./cpf.pipe";
import { ListarComponent } from "./listar/listar.component";
import { SalvarComponent } from "./salvar/salvar.component";
import { SistemaRoutingModule } from "./sistema-routing.module";
import { SistemaComponent } from "./sistema.component";
import { SistemaService } from "./sistema.service";

@NgModule({
    declarations: [
        SistemaComponent,
        ListarComponent,
        SalvarComponent,
        CpfPipe
    ],
    imports: [
        CommonModule,
        SistemaRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        AlertasModule,
        SpinnerModule,
        PaginacaoModule,
        QuantidadeRegistroModule
    ],
    exports: [],
    providers: [
        SistemaService
    ]
})
export class SistemaModule{

}