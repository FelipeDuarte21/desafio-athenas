import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, ActivationEnd, Router } from "@angular/router";
import { Form } from "src/app/modelos/form.model";
import { AlertasService } from "../../compartilhados/alertas/alertas.service";
import { SpinnerService } from "../../compartilhados/spinners/spinner.service";
import { SistemaService } from "../sistema.service";

@Component({
    selector: 'sistema-salvar',
    templateUrl: './salvar.component.html',
    styleUrls: ['./salvar.component.css']
})
export class SalvarComponent implements OnInit{

    public icone_incluir:string = "fa-user-plus";
    public icone_alterar:string = "fa-user-pen";

    public titulo:string = "Incluir";
    public icone:string = this.icone_incluir;

    public formPessoa: FormGroup;

    public desativaBotao:boolean = false;

    private id:number = null;

    constructor(
        private formBuilder: FormBuilder,
        private sistemaService: SistemaService,
        private alertaService: AlertasService,
        private spinnerService: SpinnerService,
        private router: Router,
        private activetedRoute: ActivatedRoute
    ){}

    ngOnInit(): void {

        this.formPessoa = this.formBuilder.group({
            nome: ['',[Validators.required,Validators.maxLength(80)]],
            cpf:['',[Validators.required,Validators.pattern('[0-9]{11}')]],
            dataNascimento: ['',[Validators.required]],
            sexo: ['',[Validators.required]],
            altura: ['',[Validators.required]],
            peso: ['',[Validators.required]]
        });

        this.activetedRoute.params.subscribe(
            params => {
                if(params.id){
                    this.id = params.id;
                    this.titulo = "Alterar";
                    this.icone = this.icone_alterar;
                    this.buscarPessoa(this.id);
                }
            }
        );

    }

    private buscarPessoa(id:number) {
        this.sistemaService.buscarPorId(id).subscribe(
            pessoa => {
                this.formPessoa.get('nome').setValue(pessoa.nome);
                this.formPessoa.get('cpf').setValue(pessoa.cpf);
                this.formPessoa.get('dataNascimento').setValue(pessoa.dataNascimento);
                this.formPessoa.get('sexo').setValue(pessoa.sexo);
                this.formPessoa.get('altura').setValue(pessoa.altura);
                this.formPessoa.get('peso').setValue(pessoa.peso);
            },
            error => {
                console.log(error);
            }
        );
    }

    public verificaCampo(campo:string): boolean{
        return this.formPessoa.get(campo).errors && this.formPessoa.get(campo).touched;
    }

    public tamanhoCampo(campo:string): number{
        let valor = this.formPessoa.get(campo).value as string;
        if(valor == null){
            return 0;
        }
        return valor.length;
    }

    public enviar(){

        this.desativaBotao = true;

        this.spinnerService.ativarSpinner();

        let pessoa = this.formPessoa.getRawValue() as Form;

        this.sistemaService.salvar(this.id,pessoa).subscribe(
            resp => {
                this.formPessoa.reset();
                this.desativaBotao = false;
                this.spinnerService.desativarSpinner();
                this.router.navigate(['/sistema']);
                this.alertaService.alertaSucesso("Pessoa Salva Com Sucesso!");
            },
            error => {
                this.desativaBotao = false;
                this.spinnerService.desativarSpinner();
                this.alertaService.alertaErro("Erro! erro ao tentar salvar pessoa");
            }
        );

    }

}