import { Contato } from './contato';
import { Endereco } from './endereco';

export interface Fornecedor {
  id?:                      any;

  nome:                  string; //razao social
  nomeFantasia:          string;
  cnpj:                  string;
  inscricaoEstadual:     string;

  endereco:            Endereco;
  contatos:           Contato[];
  dataCadastro:             any;
}
