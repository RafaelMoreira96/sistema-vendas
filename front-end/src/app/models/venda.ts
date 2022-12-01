import { FormaPagamento } from './formapagamento';
import { ItemVenda } from './itemvenda';

export interface Venda {
  id:                             any;
  cliente:                     number;
  nomeCliente:                 string;
  funcionario:                 number;
  nomeFuncionario:             string;

  itens:                  ItemVenda[];

  formaPagamento:      FormaPagamento;
  valorVenda:                  number;
  status:                         any;
  dataVenda:                   string;
}
