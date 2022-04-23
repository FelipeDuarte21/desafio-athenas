import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AlertasService } from "src/app/compartilhados/alertas/alertas.service";
import { SpinnerService } from "src/app/compartilhados/spinners/spinner.service";
import { PaginaPessoa } from "src/app/modelos/pagina-pessoa.model";
import { SistemaService } from "../sistema.service";

@Component({
    selector: 'sistema-listar',
    templateUrl: './listar.component.html',
    styleUrls: ['./listar.component.css']
})
export class ListarComponent{

    public qtdOpcoes: number[] = [4,8,12];
    public quantidadeAtual: number = this.qtdOpcoes[0];

    public paginaAtual = 0;
    
    public paginaPessoas: PaginaPessoa = null;
    public totalPagina = 0;

    constructor(
        private sistemaService: SistemaService,
        private spinnerService: SpinnerService,
        private alertaService: AlertasService,
        private router: Router
    ){}

    onMudarQuantidade(quantidade: any){
        this.quantidadeAtual = quantidade;
        this.pesquisarPessoas();
    }

    onMudarPagina(pagina:any){
        this.paginaAtual = pagina;
        this.pesquisarPessoas();
    }

    public pesquisarPessoas(){

        this.spinnerService.ativarSpinner();

        this.sistemaService.pesquisar(this.paginaAtual,this.quantidadeAtual).subscribe(
            pagina => {
                this.paginaPessoas = pagina;
                this.totalPagina = pagina.numberOfElements;
                this.spinnerService.desativarSpinner();
            },
            error => {
                this.spinnerService.desativarSpinner();
                console.log(error);
            }
        );

    }

    public alterar(id:number){
        this.router.navigate(['sistema/alterar',id]);
    }

    public excluir(id: number){

        let c = confirm("Deseja realmente excluir pessoa ?");

        if(c){
            this.spinnerService.ativarSpinner();
            this.sistemaService.excluir(id).subscribe(
                resp => {
                    this.spinnerService.desativarSpinner();
                    this.alertaService.alertaSucesso("Pessoa excluída com Sucesso!");
                },
                error => {
                    this.spinnerService.desativarSpinner();
                    this.alertaService.alertaErro("Erro ao excluir pessoa");
                }
            );
        }

    }

    public calcularPeso(id: number){
        this.spinnerService.ativarSpinner();
        this.sistemaService.pesoIdeal(id).subscribe(
            peso => {
                this.spinnerService.desativarSpinner();
                this.alertaService.alertaAviso(`${peso.nome} você tem ${peso.idade} anos e seu peso ideal é ${peso.peso} kilos`,true,10000);
            },
            error => {
                this.spinnerService.desativarSpinner();
            }
        );
    }

}