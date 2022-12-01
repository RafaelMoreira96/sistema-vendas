import { ItemCompra } from './itemcompra';

export interface Compra {
  id:                             any;
  fornecedor:                  number;
  nomeFornecedor:              string;
  funcionario:                 number;
  nomeFuncionario:             string;

  itens:                 ItemCompra[];

  status:                         any;
  dataVenda:                   string;
}
