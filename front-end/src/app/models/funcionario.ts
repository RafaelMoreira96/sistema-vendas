import { Contato } from './contato';
import { Endereco } from './endereco';

export interface Funcionario {
  id?: any;
  nome: string;
  cpf: string;
  dataAdmissao: any;
  dataDemissao: any;
  endereco: Endereco;
  contatos: Contato[];
  dataCadastro: any;
  nomeUsuario: string;
  senha: string;
}
