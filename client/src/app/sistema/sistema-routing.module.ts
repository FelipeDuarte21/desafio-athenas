import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ListarComponent } from "./listar/listar.component";
import { SalvarComponent } from "./salvar/salvar.component";
import { SistemaComponent } from "./sistema.component";

const routes: Routes = [
    {
        path: '',
        component: SistemaComponent,
        children: [
            {
                path: '',
                component: ListarComponent
            },
            {
                path: 'incluir',
                component: SalvarComponent
            },
            {
                path: 'alterar/:id',
                component: SalvarComponent
            }
        ]
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SistemaRoutingModule{

}