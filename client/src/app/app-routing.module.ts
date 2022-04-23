import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from "./app.component";

const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: 'sistema'
    },
    {
        path: 'sistema',
        loadChildren: () => import('./sistema/sistema.module').then(m => m.SistemaModule)
    }
]

@NgModule({
    imports: [RouterModule.forRoot(routes,{useHash: true})],
    exports: [RouterModule],
})
export class AppRoutingModule{

}