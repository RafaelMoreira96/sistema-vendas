import { Contato } from './contato';
import { Endereco } from './endereco';

export interface Cliente {
  id?: any;
  nome: string;
  cpf: string;
  endereco: Endereco;
  contato: Contato[];
  dataCadastro: Date;
}
