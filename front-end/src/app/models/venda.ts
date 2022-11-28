import { FormaPagamento } from './formapagamento';
import { ItemVenda } from './itemvenda';

export interface Venda {
  id:                             any;
  numeroVenda:                 number;
  idCliente:                   number;
  nomeCliente:                 string;
  idFuncionario:               number;
  nomeFuncionario:             string;

  itens:                  ItemVenda[];

  formaPagamento:      FormaPagamento;
  status:                         any;
  dataVenda:                   string;
}
