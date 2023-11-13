```classDiagram
Usuario "1" -- "1" Conta
Conta "1" *-- "*" Transacao
Conta "1" *-- "*" desconto_recorrente


class Usuario {
-idUsuario : Integer
-cpf : String
-nome : String
}

class Transacao {
-idTran : Integer
-valorTransacao : double
-dataTransacao : Date
}

class Conta {
-idUsuario : Integer
-numeroTelefone : String
+valorTotalConta()
}

class desconto_recorrente {
-idDesconto : Integer
-dataDesconto : Date
-valorDesconto : double
-restanteConta : double
+debitarConta() double
}
```