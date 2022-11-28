export interface Produto {
  id?:               any;
  descricao:      string;
  codBarras:      string;
  precoAtacado:   number;
  precoVarejo:    number;
  qteEstoque:     number;
  qteMin:         number;
  qteMax:         number;
}
