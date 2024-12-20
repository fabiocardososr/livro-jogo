select * from livros.livro

select * from livros.secao
where codSecaoLivro=5

select se.* from livros.secaoItens se
inner join livros.secao s on (s.idSecao=se.idSecao)
where codSecaoLivro=5

select pr.* from livros.proximasecao pr
inner join livros.secao s on (s.idSecao=pr.idSecao)
where s.codSecaoLivro=5

select * from livros.tipoefeito

select * from livros.item

select l.nome, s.idSecao, s.codSecaoLivro, s.texto as 'seção', pr.textoOpcao,i.descricao as 'Item', 
	   te.descricao as 'Efeito do item' 
from livros.secao s
inner join livros.livro l on (s.idLivro=l.idLivro)
left join livros.proximasecao pr on (pr.idSecao=s.idSecao)
left join livros.secaoItens si on (si.idSecao=s.idSecao)
left join livros.item i on (i.idItem=si.idItem)
left join livros.tipoEfeito te on (te.idTipoEfeito=i.idTipoEfeito)
where s.codSecaoLivro=1


