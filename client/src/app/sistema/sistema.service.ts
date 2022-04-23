import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from "src/environments/environment";
import { Observable } from "rxjs";
import { Pessoa } from "../modelos/pessoa.model";
import { PaginaPessoa } from "../modelos/pagina-pessoa.model";
import { Form } from "../modelos/form.model";
import { PesoIdeal } from "../modelos/peso.model";

@Injectable()
export class SistemaService{

    private BASE_URL = `${environment.apiURL}/desafio-athenas/api/v1/pessoas`;

    constructor(
        private http: HttpClient
    ){}

    public salvar(id:number,pessoa: Form): Observable<Pessoa>{

        if(id){ //Alterar
            let url = `${this.BASE_URL}/${id}`;
            return this.http.put<Pessoa>(url,pessoa);
        }
        
        //Incluir
        return this.http.post<Pessoa>(this.BASE_URL,pessoa);

    }

    public excluir(id:number): Observable<any>{
        let url = `${this.BASE_URL}/${id}`;
        return this.http.delete(url);
    }

    public buscarPorId(id:number): Observable<Pessoa>{
        let url = `${this.BASE_URL}/${id}`;
        return this.http.get<Pessoa>(url);
    }

    public pesquisar(pagina:number,quantidade:number):Observable<PaginaPessoa>{
        let url = `${this.BASE_URL}?page=${pagina}&size=${quantidade}`;
        return this.http.get<PaginaPessoa>(url);
    }

    public pesoIdeal(id: number):Observable<PesoIdeal>{
        let url = `${this.BASE_URL}/peso-ideal/${id}`;
        return this.http.get<PesoIdeal>(url);
    }

}